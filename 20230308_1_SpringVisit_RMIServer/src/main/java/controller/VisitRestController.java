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
import org.springframework.web.bind.annotation.RestController;

import dao.VisitDao;
import vo.VisitVo;

/*
   @RestController  =  @Controller + @ResponseBody 

               METHOD       URI 
   ��ü��ȸ:   GET          /visits
               GET          /visits/{search}/{search_text}    /visits/name/�浿
   1�� ��ȸ:   GET          /visit/{idx}                      /visit/2
   �����ȸ:   GET          /visit/check-pwd/{idx}/{c_pwd}
   �߰�    :   POST         /visit
   
      
   ����    :   PUT          /visit
   ����    :   DELETE       /visit/{idx}                       /visit/2  
   
*/


@RestController
public class VisitRestController {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ServletContext application;
	
	
	VisitDao visit_dao;
	
	public void setVisit_dao(VisitDao visit_dao) {
		this.visit_dao = visit_dao;
	}
	
	@RequestMapping(value={"/visits" ,  "/visits/{search}/{search_text}" } , method=RequestMethod.GET)
	public Map visits( @PathVariable(value="search",    required=false) String search,
			           @PathVariable(value="search_text",required=false) String search_text) {

		if(search==null) search="all";
		
		//�˻������� ���� map
		Map<String,String> map = new HashMap<String,String>();
		
		if(search.equals("name_content")) {
			//�̸�+����
			map.put("name", search_text);
			map.put("content", search_text);
		}else if(search.equals("name")) {
			//�̸�
			map.put("name", search_text);
		}else if(search.equals("content")) {
			//����
			map.put("content", search_text);
			
		}else if(search.equals("modifydate")) {
			//��¥
			map.put("modifydate", search_text);
		}
		
		
				
		//��� ��������
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
	
	//// /visit?name=�ϱ浿&content=����&pwd=1234
	//// /visit {"name":"�ϱ浿","content":"����","pwd":"1234"}
	
	/*
	 
	 $.ajax({
	     type: 'POST',
	     url : '/visit',
	     data: {"name":"�ϱ浿","content":"����","pwd":"1234"},
	     dataType:'json',
	     success:function(res_data){
	         //res_data = {"result":"success"}
	         if(res_data.result =='success'){}
	     }
	 });
	  
	*/
	
	
	//�߰�
	@RequestMapping(value="/visit", method=RequestMethod.POST)
	public Map insert(@RequestBody VisitVo vo) {

		//1.����(�ۼ���)�� ������
		String ip		= request.getRemoteAddr();
		//2.ip�� vo���߰�
		vo.setIp(ip);
		
		//5.DB insert
		int res = visit_dao.insert(vo);
		
		Map map = new HashMap();
		map.put("result", (res==1)? "success" : "fail");
		// {"result":"success"} or {"result":"fail"}		
		return map;
	}
	
	
	//��й�ȣüũ
	@RequestMapping(value="/visit/check-pwd/{idx}/{c_pwd}", method=RequestMethod.GET)
	public Map check_pwd(@PathVariable int idx,@PathVariable String c_pwd) {
		//1.idx�� �ش�Ǵ� �Խù� 1�� ���´�
		VisitVo vo = visit_dao.selectOne(idx);
		
		//�������==�Է��Ѻ��
		boolean bResult = vo.getPwd().equals(c_pwd);
	
	
		Map map = new HashMap();
		map.put("result", bResult);
		
		return map;
		
	}
	
	
	//����
	/*
	 $.ajax({
	     type: 'PUT',
	     url : '/visit',
	     data: {"idx":66, "name":"�ϱ浿","content":"����","pwd":"1234"},
	     dataType:'json',
	     success:function(res_data){
	         //res_data = {"result":"success"}
	         if(res_data.result =='success'){}
	     }
	 });
	*/
	@RequestMapping(value="/visit", method=RequestMethod.PUT)
	public Map update(@RequestBody VisitVo vo) {

		//1.����(�ۼ���)�� ������
		String ip		= request.getRemoteAddr();
		//2.ip�� vo���߰�
		vo.setIp(ip);
		
		//5.DB update
		int res = visit_dao.update(vo);
		
		Map map = new HashMap();
		map.put("result", (res==1)? "success" : "fail");
		// {"result":"success"} or {"result":"fail"}		
		return map;
	}
		
	//����
	/*
	 $.ajax({
	     type: 'DELETE',
	     url : '/visit',
	     data: {"idx":66},
	     dataType:'json',
	     success:function(res_data){
	         //res_data = {"result":"success"}
	         if(res_data.result =='success'){}
	     }
	 });
	 */
	@RequestMapping(value="/visit/{idx}", method=RequestMethod.DELETE)
	public Map delete(@PathVariable int idx) {

		//1-1.�Խù� ���� 1�� ���´�
		VisitVo  vo = visit_dao.selectOne(idx);
		
		//ȭ���� ������ ���ϱ�
		String save_path = application.getRealPath("/resources/upload/");
		System.out.println(save_path);
		
		System.out.println(vo.getContent());
		//JSOUP : Java Html Parser(jsoup-1.12.1.jar)
		
		
		//������ HTML������ �о���δ�
		Document doc   = Jsoup.parse(vo.getContent());
		
		//HTML�� <img>����� �˻��Ѵ�=>����� ArrayList<Element>�� ����
		Elements  imgs = doc.select("img");
		for(Element img : imgs) {
			
			//<img src="http://localhost:9090....../a.jpg">  src ���� �о�´�
			String img_path = img.attr("src");
			// "/" �� ���ʺ��� �˻��Ѵ�(�˻���ġ: index)
			int    index    = img_path.lastIndexOf("/");
			//ȭ�ϸ� �߶�´�
			String filename = img_path.substring(index+1);		
			
			System.out.println(filename);
			//ȭ�� ���� ����
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
