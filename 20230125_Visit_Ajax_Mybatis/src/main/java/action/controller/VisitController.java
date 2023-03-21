package action.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import annotation.RequestMapping;
import annotation.ResponseBody;
import dao.VisitDao;
import vo.VisitVo;

public class VisitController {

	@RequestMapping("/visit/list.do")
	public String list(HttpServletRequest request,HttpServletResponse response) {
		
		//2.parameter받기
		String search      = request.getParameter("search");
		String search_text = request.getParameter("search_text");
		
		if(search==null) search = "all";
		
		//검색조건을 담을 map
		Map<String,String> map = new HashMap<String,String>();
		
		
		if(search.equals("name_content")) {
			//이름+내용
			map.put("name", search_text);
			map.put("content", search_text);
		}else if(search.equals("name")) {
			//이름
			map.put("name", search_text);
		}else if(search.equals("content")) {
			//내용
			map.put("content", search_text);
			
		}else if(search.equals("modifydate")) {
			//날짜
			map.put("modifydate", search_text);
		}
		
		
		//목록 가져오기
		List<VisitVo> list = VisitDao.getInstance().selectList(map);
		
		//request binding
		request.setAttribute("list", list);
		
		
		return "visit_list.jsp";// forward view page
	}
	
	//등록폼 띄우기
	@RequestMapping("/visit/insert_form.do")
	public String insert_form(HttpServletRequest request,HttpServletResponse response) {
		
		return "visit_insert_form.jsp";
	}
	
	//등록하기
	@RequestMapping("/visit/insert.do")
	public String insert(HttpServletRequest request,HttpServletResponse response) {
	
		//2. parameter받기
		/* visit/insert.do?name=d&content=d&pwd=d */
		String name = request.getParameter("name");
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		//content = "동해물과\r\n백두산이\r\n..."
		String pwd = request.getParameter("pwd");
		
		//3. 작성자측 아이피
		String ip = request.getRemoteAddr();
		
		//4. VisitDao 포장
		VisitVo vo = new VisitVo(name, content, pwd, ip);
		
		//5. DB insert
		int res = VisitDao.getInstance().insert(vo);
		
		return "redirect:list.do"; //FrontController에게 redirect정보 전달
	}
	
	//비밀번호 체크하기
	@RequestMapping(value="/visit/check_pwd.do",produces="text/json;charset=utf-8;")
	@ResponseBody
	public String check_pwd(HttpServletRequest request,HttpServletResponse response) {
	
		//2.parameter받기
		int    idx 		= Integer.parseInt(request.getParameter("idx"));
		String c_pwd 	= request.getParameter("c_pwd");

		//3.idx에 해당되는 게시물 1건 얻어온다
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//원래비번==입력한비번
		boolean bResult = vo.getPwd().equals(c_pwd);
		
		//json data생성
		JSONObject json = new JSONObject();
		json.put("result", bResult);
		
		return json.toString();
	}
	
	//삭제하기
	@RequestMapping("/visit/delete.do")
	public String delete(HttpServletRequest request,HttpServletResponse response) {
		
		//1.parameter받기
		int idx = Integer.parseInt(request.getParameter("idx"));

		//3.idx에 해당되는 게시물 1건 얻어온다
		VisitVo vo = VisitDao.getInstance().selectOne(idx);

		//2.DB delete
		//int res = VisitDao.getInstance().delete(idx);
	
		//파일 저장경로 구하기
		String file_path = request.getServletContext().getRealPath("/upload/");
		
	    // 파일의 경로
	    File file = new File(file_path);
	    
	    /*
	    File[] fileList =  file.listFiles();
	    
	    for(File fileName : fileList){
	    	
	    		
	    	String name = fileName.getName();
	        
	        String a = name.substring(name.lastIndexOf(".")+1).toLowerCase();
	        /*
	        if(a.contains("jpg")) {
	        	//fileName.delete();
	        	System.out.println(a);
	         }
	        
        	System.out.println(fileName);
        	System.out.println(name);
        	System.out.println(a);
	    	 
	    	}
		*/
		//JSOUP : Java Html Parser(jsoup-1.12.1.jar)
		Document doc = Jsoup.parse(vo.getContent());
		
		Elements  imgs = doc.select("img");
		for(Element img : imgs) {
			
			String img_path = img.attr("src");
			int    index    = img_path.lastIndexOf("/");
			String filename = img_path.substring(index+1);		
			
			System.out.println(filename);
			//화일 삭제 과정
			File  f = new File(file_path, filename);
			f.delete();
			
		}
		
		//2.DB delete
		int res = VisitDao.getInstance().delete(idx);
		
		
		return "redirect:list.do";
	}
	
	//수정폼 띄우기
	@RequestMapping("/visit/modify_form.do")
	public String modify_form(HttpServletRequest request,HttpServletResponse response) {
		
		//1.parameter받기
		int idx = Integer.parseInt(request.getParameter("idx"));

		//2.idx에 해당되는 객체 1개 얻어오기
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//3.request binding
		request.setAttribute("vo", vo);
		
		return "visit_modify_form.jsp";
	}
	
	//수정하기
	@RequestMapping("/visit/modify.do")
	public String modify(HttpServletRequest request,HttpServletResponse response) {
		
		//2.parameter받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		String content 	= request.getParameter("content").replaceAll("\n", "<br>");
		//content = "동해물과\r\n백두산이\r\n..."
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		//3.SungVo포장
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		//4.DB insert
		int res = VisitDao.getInstance().update(vo);
		
		return "redirect:list.do";
	}
}
