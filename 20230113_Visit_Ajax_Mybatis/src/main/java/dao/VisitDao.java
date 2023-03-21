package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.VisitVo;

public class VisitDao {
	
	SqlSessionFactory factory;

	//single-ton pattern
	static VisitDao single = null;

	public static VisitDao getInstance() {
		//생성되지 않았으면 생성해라
		if (single == null)
			single = new VisitDao();

		return single;
	}

	//왜? : 외부에서 new VisitDao()생성하지 못하도록
	private VisitDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//방명록 조회
	public List<VisitVo> selectList() {

		List<VisitVo> list = null;
		
		//1. MYbatis 작업객체 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2. 작업수행
		list = sqlSession.selectList("visit.visit_list");
		
		//3. 닫기
		sqlSession.close();
		
		return list;
	}
	
	public List<VisitVo> selectList(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<VisitVo> list = null;
		//1.Mybatis작업객체 얻어오기
		SqlSession  sqlSession = factory.openSession();
		//2.작업수행
		list = sqlSession.selectList("visit.visit_list_condition", map);		
		//3.닫기
		sqlSession.close();
		
		return list;
	}
	
	
	//idx에 해당되는 게시물 1건 조회
	public VisitVo selectOne(int idx) {

		VisitVo vo = null;
		//1.Mybatis작업객체 얻어오기
		SqlSession  sqlSession = factory.openSession();
		//2.작업수행                 mapper_id        parameter  
		vo = sqlSession.selectOne("visit.visit_one" , idx);		
		//3.닫기
		sqlSession.close();
		
		return vo;
	}

	//추가하기
	public int insert(VisitVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//방법1. 트랜잭션 처리 방법
		/*
		//1.Mybatis작업객체 얻어오기
		SqlSession  sqlSession = factory.openSession();
		//2.작업수행                 mapper id       , parameter     
		res = sqlSession.insert("visit.visit_insert" ,   vo  );	
		
		//2-1.commit(적용)
		sqlSession.commit();
		
		//3.닫기
		sqlSession.close();
		*/
		
		//방법2. auto commit
		
		//1.Mybatis작업객체 얻어오기
		SqlSession  sqlSession = factory.openSession(true);
		//2.작업수행                 mapper id       , parameter     
		res = sqlSession.insert( "visit.visit_insert" , vo );	
		//3.닫기
		sqlSession.close();
		
		return res;
	}
	
	//삭제
	public int delete(int idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.Mybatis작업객체 얻어오기
		SqlSession  sqlSession = factory.openSession(true);
		//2.작업수행                 mapper id       , parameter     
		res = sqlSession.delete( "visit.visit_delete" , idx );	
		//3.닫기
		sqlSession.close();
		
		return res;
	}
	
	public int update(VisitVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.Mybatis작업객체 얻어오기
		SqlSession  sqlSession = factory.openSession(true);
		//2.작업수행                 mapper id       , parameter     
		res = sqlSession.delete( "visit.visit_update" , vo );	
		//3.닫기
		sqlSession.close();
		
		return res;
	}
}
