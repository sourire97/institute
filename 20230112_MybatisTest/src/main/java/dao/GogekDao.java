package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.GogekVo;
import vo.SawonVo;

public class GogekDao {
	//single-ton : 객체1개만 생성 서비스
	static GogekDao single = null;

	//SessionFactory생성하는 객체
	SqlSessionFactory factory;
	
	public GogekDao() {
		super();
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	public static GogekDao getInstance() {

		if (single == null)
			single = new GogekDao();

		return single;
	}
	
	//전체조회
	public List<GogekVo> selectList(){
		
		List<GogekVo> list = null;
		
		//1.Mybastis작업객체 얻어온다
		SqlSession sqlSession = factory.openSession();
		
		//2. 작업 수행
		list = sqlSession.selectList("gogek.gogek_list");
		
		//3.작업 후 종료작업(닫기) : conn.close()와 동일
		sqlSession.close();
		
		return list;
		
	}
	
	
}
