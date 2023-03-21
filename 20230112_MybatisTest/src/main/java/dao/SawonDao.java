package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.SawonVo;

public class SawonDao {
	//single-ton : 객체1개만 생성 서비스
	static SawonDao single = null;

	//SessionFactory생성하는 객체
	SqlSessionFactory factory;
	
	public SawonDao() {
		super();
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	public static SawonDao getInstance() {

		if (single == null)
			single = new SawonDao();

		return single;
	}
	
	//전체조회
	public List<SawonVo> selectList(){
		
		List<SawonVo> list = null;
		
		//1.Mybastis작업객체 얻어온다
		SqlSession sqlSession = factory.openSession();
		
		//2. 작업 수행
		list = sqlSession.selectList("sawon.sawon_list");
		
		//3.작업 후 종료작업(닫기) : conn.close()와 동일
		sqlSession.close();
		
		return list;
		
	}
	
	
}
