package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BoardDao;
import vo.BoardVo;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	BoardDao board_dao;

	public void setBoard_dao(BoardDao board_dao) {
		this.board_dao = board_dao;
	}
	
	@RequestMapping("list.do")
	public String list(Model model) {
		
		//게시판 목록 읽어오기
		List<BoardVo> list = board_dao.selectList();
			
		//model를 통해서 request binding
		model.addAttribute("list", list);
		
		return "borad/board_list"; //viewname
	}
	
	// /board/view.do?b_idx=3
	@RequestMapping("view.do")
	public String view(int b_idx,Model model) {

		BoardVo vo = board_dao.selectOne(b_idx);
		
		
		//세션에서 게시물을 조회여부체크
		if(session.getAttribute("show")==null) {
			//조회수 증가 호출
			board_dao.update_readhit(b_idx);
			
			//세션에 봤다는 정보 기록
			session.setAttribute("show", true);
		}
		
		//request binding
		model.addAttribute("vo", vo);
		
		return "board/board_view";
	}
	
	//글쓰기 폼
	@RequestMapping("insert_form.do")
	public String insert_form() {

		return "board/board_insert_form";
	}
	
	//글쓰기
	// /board/insert.do?mem_idx=3&mem_name=홍길동&b_subject=제목&b_content=내용
	@RequestMapping("insert.do")
	public String insert(BoardVo vo,Model model) {

		//세션만료시 처리
		if(session.getAttribute("user")==null) {
			
			model.addAttribute("reason", "logout");
			
			return "redirect:../member/login_form.do";
		}
		
		//CKEditor사용하면 이부분 삭제할것
		String b_content = vo.getB_content().replaceAll("\n", "<br>");
		vo.setB_content(b_content);
		
		
		//ip구하기
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
		
		//DB Insert
		int res = board_dao.insert(vo);
		
		return "redirect:list.do";
	}
	
	//삭제
	@RequestMapping("delete.do")
	public String delete(int b_idx) {

		int res = board_dao.delete(b_idx);
				
		return "redirect:list.do";
	}
	
	//답글폼 띄우기
	@RequestMapping("reply_form.do")
	public String reply_form() {

		return "board/board_reply_form";
	}

	
	@RequestMapping("reply.do")
	public String reply(BoardVo vo) {
		
		//기준글번호의 게시물정보 얻어오기
		BoardVo baseVo = board_dao.selectOne(vo.getB_idx());
		
		//기준글 아래글들의 step을 1씩 증가
		int res = board_dao.update(baseVo);
		
		//답글의 상태정보(b_ref,b_step,b_depth)설정
		vo.setB_ref(baseVo.getB_ref());
		vo.setB_step(baseVo.getB_step()+1);
		vo.setB_depth(baseVo.getB_depth()+1);
		
		//ip구하기
		String b_ip = request.getRemoteAddr();
		vo.setB_ip(b_ip);
		
		//DB reply
		res = board_dao.reply(vo);
		
		return "redirect:list.do";
	}
	
	
}
