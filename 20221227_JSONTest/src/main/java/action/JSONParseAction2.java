package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Servlet implementation class JSONParseAction
 */
@WebServlet("/JSONParseAction2")
public class JSONParseAction2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			String str_url = "http://localhost:9090/2022_1227_JSONTest/json_data.jsp";
			
			URL url = new URL(str_url);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			//����� �������� ����
			
			InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "utf-8");
			BufferedReader    br  = new BufferedReader(isr);
			StringBuffer      sb  = new StringBuffer();
			while(true) {
				
				String data = br.readLine();
				if(data==null)break;
				sb.append(data);
			}
			
			JSONObject json =  new JSONObject(sb.toString());
			
			System.out.println(sb.toString());
			
			//{"name":"ȫ�浿", "age":"30", "tel":"010-111-1234","hobby": ["����","�","��ȭ"] } 
			
			String name = json.getString("name");
			
			JSONObject jsonName = json.getJSONObject("name2");
			String familyName   = jsonName.getString("familyName");
			String givenName    = jsonName.getString("givenName");
			
			
			int    age  = json.getInt("age");
			String tel  = json.getString("tel");
			
			JSONArray h_array = json.getJSONArray("hobby");
			System.out.println(h_array.length());
			
			JSONArray f_array = json.getJSONArray("fruit");
			
			JSONObject spring_fruit = f_array.getJSONObject(0);
			JSONObject summer_fruit = f_array.getJSONObject(1);
			JSONObject fall_fruit   = f_array.getJSONObject(2);
			JSONObject winter_fruit = f_array.getJSONObject(3);
			
			
			JSONArray summer_array = summer_fruit.getJSONArray("����");
			System.out.print("��������:");
			for(int i=0;i< summer_array.length();i++) {
				System.out.print(summer_array.getString(i) + " ");
			}
			System.out.println();
			
			
			System.out.printf("��:%s(��)\n",familyName);
			System.out.printf("�̸�:%s\n",givenName);
			
			System.out.printf("��ü�̸�:%s\n����:%d\n��ȭ:%s\n", name,age,tel);
			System.out.print("���:");
			for(int i=0;i< h_array.length();i++) {
				System.out.print(h_array.getString(i) + " ");
			}
			
			System.out.println();
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
