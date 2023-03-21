package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.DeptVo;

public class DeptDao {

	SqlSessionFactory factory;
	
	//single-ton pattern
	static DeptDao single = null;

	public static DeptDao getInstance() {
		//생성되지 않았으면 생성해라
		if (single == null)
			single = new DeptDao();

		return single;
	}

	//왜? : 외부에서 new DeptDao()생성하지 못하도록
	private DeptDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	public List<DeptVo> selectList(){
		
		List<DeptVo> list = null;
		
		//1.SqlSession 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		list = sqlSession.selectList("dept.dept_list");
		
		//3.닫기
		sqlSession.close();
		
		return list;
		
	}
	
}
