package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.VisitVo;

public class VisitDaoImpl implements VisitDao {
	
	//Setter Injection
	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<VisitVo> selectList() {

		/*
		List<VisitVo> list = null;
		list = sqlSession.selectList("visit.visit_list");
		return list;
		*/
		
		return sqlSession.selectList("visit.visit_list");
	}
	
	public List<VisitVo> selectList(Map<String, String> map) {
		
		return sqlSession.selectList("visit.visit_list_condition", map);
	}
	
	
	public VisitVo selectOne(int idx) {

		return sqlSession.selectOne("visit.visit_one" , idx);
	}

	public int insert(VisitVo vo) {

		return sqlSession.insert( "visit.visit_insert" , vo );
	}
	
	public int delete(int idx) {

		return sqlSession.delete( "visit.visit_delete" , idx );	
	}
	
	public int update(VisitVo vo) {

		return sqlSession.delete( "visit.visit_update" , vo );	
	}
}
