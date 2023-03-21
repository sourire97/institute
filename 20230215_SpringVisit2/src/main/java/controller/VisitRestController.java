package controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.VisitDao;
import vo.VisitVo;

/*
   @RestController  =  @Controller + @ResponseBody 

			METHOD		URI 
   전체조회 :	GET			/visits
   			GET			/visits/{search}/{search_text}    /visits/name/길동
 비밀번호조회:	GET         /visit/check-pwd/{idx}/{c_pwd}              
   1건조회 :	GET         /visit/{idx}                      /visit/2
	추가	 :	POST        /visit
	
	수정	 :	PUT         /visit
	삭제	 :	DELETE      /visit/{idx}                      /visit/2  
   
*/


@RestController
public class VisitRestController {

	@Autowired
	ServletContext application;
	
	@Autowired
	HttpServletRequest request;
	
	VisitDao visit_dao;
	
	public void setVisit_dao(VisitDao visit_dao) {
		this.visit_dao = visit_dao;
	}
	
	@RequestMapping(value={"/visits" ,  "/visits/{search}/{search_text}" } , method=RequestMethod.GET)
	public Map visits( @PathVariable(required=false) String search,
			           @PathVariable(required=false) String search_text) {

		if(search==null) search="all";
		
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
		
		
		
		
		List<VisitVo> list = visit_dao.selectList(map);
			
		Map resultMap = new HashMap();
		resultMap.put("list", list);
		return resultMap;
	}
	
	
	@RequestMapping(value="/visit/{idx}", method=RequestMethod.GET)
	public VisitVo visit_one(@PathVariable int idx) {

		VisitVo  vo = visit_dao.selectOne(idx);
		
		if(vo==null) {
			vo = new VisitVo();
			vo.setIdx(0);
		}
		
		return vo;
	}
	
	//// /visit?name=일길동&content=내용&pwd=1234
	//// /visit {"name":"일길동","content":"내용","pwd":"1234"}
	/*
	 
	 $.ajax({
	     type: 'POST',
	     uri : '/visit',
	     data: {"name":"일길동","content":"내용","pwd":"1234"},
	     dataType:'json',
	     success:function(res_data){
	         //res_data = {"result":"success"}
	         if(res_data.result =='success'){}
	     }
	 });
	*/
	
	//추가
	@RequestMapping(value = "/visits", method = RequestMethod.POST)
	public Map insert(@RequestBody VisitVo vo) {
		
		//작성자 ip
		String ip = request.getRemoteAddr();
		
		vo.setIp(ip);
		
		int res = visit_dao.insert(vo);
		
		Map map = new HashMap();
		map.put("result", (res==1)? "success" : "fail");
		// {"result":"success"} or {"result":"fail"}		
		return map;
	}
	
	//비밀번호체크
	@RequestMapping(value = "/visits/checkpwd/{idx}/{c_pwd}", method = RequestMethod.GET)
	public Map checkpwd(@PathVariable int idx, @PathVariable String c_pwd) {
		
		VisitVo vo = visit_dao.selectOne(idx);
		
		//원래비번==입력한비번
		boolean bResult = vo.getPwd().equals(c_pwd);
	
		Map map = new HashMap();
		map.put("result", bResult);
		
		return map;
		
	}
	
	//수정
	@RequestMapping(value = "/visits", method = RequestMethod.PUT)
	public Map modify(@RequestBody VisitVo vo) {
		
		//작성자 ip
		String ip = request.getRemoteAddr();
		
		vo.setIp(ip);
		
		int res = visit_dao.update(vo);
		
		Map map = new HashMap();
		map.put("result", (res==1)? "success" : "fail");
		// {"result":"success"} or {"result":"fail"}		
		return map;
	}
	
	//삭제
	@RequestMapping(value = "/visits/{idx}", method = RequestMethod.DELETE)
	public Map delete(@PathVariable int idx) {
		
		//1-1.게시물 내용 1건 얻어온다
		VisitVo  vo = visit_dao.selectOne(idx);
		
		//화일의 저장경로 구하기
		String save_path = application.getRealPath("/resources/upload/");
		System.out.println(save_path);
		
		System.out.println(vo.getContent());
		//JSOUP : Java Html Parser(jsoup-1.12.1.jar)
		
		
		//내용의 HTML문서를 읽어들인다
		Document doc   = Jsoup.parse(vo.getContent());
		
		//HTML내 <img>목록을 검색한다=>결과는 ArrayList<Element>로 저장
		Elements  imgs = doc.select("img");
		for(Element img : imgs) {
			
			//<img src="http://localhost:9090....../a.jpg">  src 값을 읽어온다
			String img_path = img.attr("src");
			// "/" 를 뒷쪽부터 검색한다(검색위치: index)
			int    index    = img_path.lastIndexOf("/");
			//화일명만 잘라온다
			String filename = img_path.substring(index+1);		
			
			System.out.println(filename);
			//화일 삭제 과정
			File  f = new File(save_path, filename);
			f.delete();
			
		}
		
		//2.DB delete
		int res = visit_dao.delete(idx);
		
		Map map = new HashMap();
		map.put("result", (res==1)? "success" : "fail");
		// {"result":"success"} or {"result":"fail"}		
		return map;
	}
	
}
