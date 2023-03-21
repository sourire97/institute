package action;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import vo.PersonVo;

/**
 * Servlet implementation class PersonXmlAction
 */
@WebServlet("/person.do")
public class PersonXmlAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			//XML Parser : SAXBuilder(jdom-2.0.6.1.jar)
			SAXBuilder builder = new SAXBuilder();
			
			//���1:url�̿��ؼ� xmlȭ�� �о����
			/*
			String str_url = "http://localhost:9090/2022_1226_XmlTest/person.xml";
			URL  url       =  new URL(str_url);
			*/
			//���2: file->����ΰ� �ƴ� �����η� �ۼ��ؾ� �Ѵ�
			String web_path = "/";
			ServletContext application = request.getServletContext();
			String abs_path            = application.getRealPath(web_path);
			//System.out.println(abs_path);
			File   file     = new File(abs_path, "person.xml");
						
			Document doc   =  builder.build(file);
			//System.out.println(doc);
			
			Element  root  = doc.getRootElement();// persons
			
			List<Element> person_list = root.getChildren("person");
			
			//XML->  PersonVo List�� ������ü
			List<PersonVo> p_list = new ArrayList<PersonVo>();
			
			for(Element p : person_list) {
				
				//Xml�κ��� ����
				String name     = p.getChildText("name");
				String nickname = p.getChild("name").getAttributeValue("nickname"); 
				int   age      =  Integer.parseInt(p.getChildText("age"));
				String tel     =  p.getChildText("tel");
				String hometel =  p.getChild("tel").getAttributeValue("hometel");
				
				//����� ���� Vo�� ����
				PersonVo  vo = new PersonVo();
				vo.setName(name);
				vo.setNickname(nickname);
				vo.setAge(age);
				vo.setTel(tel);
				vo.setHometel(hometel);
				
				p_list.add(vo);//ArrayList�� ��´�
								
			}//end-for
			
			//request binding
			request.setAttribute("p_list", p_list);		
			
			//forward
			RequestDispatcher disp = request.getRequestDispatcher("person_list.jsp");
			disp.forward(request, response);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
