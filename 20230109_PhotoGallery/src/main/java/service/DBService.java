package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {

	
	DataSource ds;
	
	//single-ton pattern
	static DBService single = null;

	public static DBService getInstance() {
		//생성되지 않았으면 생성해라
		if (single == null)
			single = new DBService();

		return single;
	}

	//왜? : 외부에서 new DBService()생성하지 못하도록
	private DBService() {
		// TODO Auto-generated constructor stub
		//JNDI를 이용해서 ds정보를 얻어오기
		try {
			InitialContext ic = new InitialContext();
			Context context   = (Context) ic.lookup("java:comp/env");
			ds                = (DataSource) context.lookup("jdbc/oracle_test");  
			
			//ds                = (DataSource) ic.lookup("java:comp/env/jdbc/oracle_test");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Connection  getConnection() throws SQLException {
		
		Connection conn = null;
		conn = ds.getConnection();
		return conn;
	}
	
	
}






