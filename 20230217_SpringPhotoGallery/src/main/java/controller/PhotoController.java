package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dao.PhotoDao;
import vo.MemberVo;
import vo.PhotoVo;

@Controller
@RequestMapping("/photo/")
public class PhotoController {

	@Autowired
	HttpSession  session;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ServletContext application;
	
	PhotoDao photo_dao;

	public void setPhoto_dao(PhotoDao photo_dao) {
		this.photo_dao = photo_dao;
	}
	
	//사진 목록
	@RequestMapping("list.do")
	public String list(Model model) {

		List<PhotoVo> list = photo_dao.selectList();
		
		model.addAttribute("list", list);
		
		return "photo/photo_list";
	}
	
	//사진 입력페이지 이동
	@RequestMapping("insert_form.do")
	public String insertForm() {

		return "photo/photo_insert_form";
	}
	
	//사진 입력하기
	@RequestMapping("insert.do")
	public String insert(PhotoVo vo, @RequestParam MultipartFile photo, Model model) throws Exception {
		
		//세션에 보관되어있는 로그인정보 얻어온다
		MemberVo user = (MemberVo) session.getAttribute("user");
			
		//세션이 만료가 된 경우
		if(user==null) {
			//response.sendRedirect("../member/login_form.do?reason=logout");
			model.addAttribute("reason", "logout");			
			return "redirect:../member/login_form.do";
		}
		
		//회원번호
		int mem_idx = user.getMem_idx();
		
		//파일저장위치(절대경로)
		String abs_path = application.getRealPath("/resources/upload/");
		
		//업로드된 파일정보 구하기
		String p_filename= "no_file";
		if(photo.isEmpty()==false) {//업로드된 파일이 존재하면
			
			p_filename = photo.getOriginalFilename();
			
			File f = new File(abs_path, p_filename);
			if(f.exists()) {//동일파일명 있으면
				
				long tm =System.currentTimeMillis();
				p_filename = String.format("%d_%s", tm,p_filename);
				
				f = new File(abs_path, p_filename);
			}
			photo.transferTo(f);
		}
		
		//IP구하기(request사용)
		String p_ip      = request.getRemoteAddr();  
		
		// \n -> <br>
		String p_content = vo.getP_content().replaceAll("\n", "<br>");
		vo.setP_content(p_content);
		
		vo.setP_filename(p_filename);
		vo.setP_ip(p_ip);
		vo.setMem_idx(mem_idx);
		
		//DB insert
		int res = photo_dao.insert(vo);
		
		return "redirect:list.do";
		
	}

	//디테일 입력하기
	@RequestMapping(value="photo_detail.do",produces="text/json;charset=utf-8;")
	@ResponseBody
	public String insert(int p_idx) {

		PhotoVo vo = photo_dao.selectOne(p_idx);
		
		//응답할 데이터를 JSON생성
		JSONObject json = new JSONObject();
		json.put("p_idx",   vo.getP_idx());
		json.put("p_title", vo.getP_title());
		json.put("p_content", vo.getP_content());
		json.put("p_filename", vo.getP_filename());
		json.put("p_ip", vo.getP_ip());
		json.put("p_regdate", vo.getP_regdate());
		json.put("p_modifydate", vo.getP_modifydate());
		json.put("mem_idx", vo.getMem_idx());
		
		return json.toString();
	
	}
	
	//사진 수정페이지 이동
	@RequestMapping("modify_form.do")
	public String modifyForm(int p_idx, Model model) {
		// /photo/modify_form.do?p_idx=9
		
		PhotoVo vo = photo_dao.selectOne(p_idx);
		
		//p_content : <br> -> /n
		String p_content = vo.getP_content().replace("<br>", "/n");
		vo.setP_content(p_content);

		//request binding
		request.setAttribute("vo", vo);
		
		return "photo/photo_modify_form";
	}
	
	//사진 수정하기
	@RequestMapping("modify.do")
	public String modify(PhotoVo vo) {
		// /photo/modify.do?p_idx=2&p_title=��3���η�&p_content=��������
		
		//1.ip구하기
		String ip = request.getRemoteAddr();
		vo.setP_ip(ip);
		
		String p_content = vo.getP_content().replaceAll("\n", "<br>");
		vo.setP_content(p_content);
		
		int res = photo_dao.update(vo);
		
		return "redirect:list.do";
	}
	
	//사진 업로드
	@RequestMapping(value="photo_upload.do",produces="text/json;charset=utf-8;")
	@ResponseBody
	public String photo_upload(int p_idx,@RequestParam MultipartFile photo) throws Exception, IOException {

		String abs_path = application.getRealPath("/resources/upload/");
		
		//업로드된 화일정보 구하기
		String p_filename= "no_file";
		if(photo.isEmpty()==false) {//업로드된 화일이 존재하면
			
			p_filename = photo.getOriginalFilename();
			
			File f = new File(abs_path, p_filename);
			if(f.exists()) {//동일화일명이 있냐?
				
				long tm =System.currentTimeMillis();
				p_filename = String.format("%d_%s", tm,p_filename);
				
				f = new File(abs_path, p_filename);
			}
			photo.transferTo(f);
		}
		
		PhotoVo  vo = photo_dao.selectOne(p_idx);
		//기존에 있던 화일 삭제
		File del_f = new File(abs_path, vo.getP_filename());
		del_f.delete();
		
		//수정할 화일정보 포장
		vo.setP_filename(p_filename);//새로업로드된 화일명으로 수정
		
		//DB수정
		int res = photo_dao.update_filename(vo);
		
		//전송데이터 포장
		JSONObject  json = new JSONObject();
		json.put("p_filename", p_filename);
		
		return json.toString();
	}
	
	//사진 삭제하기
	@RequestMapping("delete.do")
	public String delete(int p_idx) {
		// /photo/delete.do?p_idx=3
		
		String abs_path = application.getRealPath("/resources/upload/");
		
		PhotoVo  vo =  photo_dao.selectOne(p_idx);

		File  f = new File(abs_path, vo.getP_filename());
		f.delete();
		
		//DB delete
	    int res = photo_dao.delete(p_idx);
		
		return "redirect:list.do";
	}

}
