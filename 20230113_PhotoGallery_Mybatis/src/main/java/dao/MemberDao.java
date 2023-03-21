package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.MemberVo;

public class MemberDao {
	
	SqlSessionFactory factory;

	//single-ton pattern
	static MemberDao single = null;

	public static MemberDao getInstance() {
		//생성되지 않았으면 생성해라
		if (single == null)
			single = new MemberDao();

		return single;
	}

	//왜? : 외부에서 new MemberDao()생성하지 못하도록
	private MemberDao() {
		// TODO Auto-generated constructor stub
		
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//전체조회
	public List<MemberVo> selectList() {

		List<MemberVo> list = null;
		
		//1. 작업객체 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.
		list = sqlSession.selectList("member.member_list");
		
		//3. 닫기
		sqlSession.close();

		return list;
	}
	
	
	//mem_idx에 해당되는 1명(건)의 정보
	public MemberVo selectOne(int mem_idx) {

		MemberVo vo = null;
		
		SqlSession sqlSession = factory.openSession();
		
		vo = sqlSession.selectOne("member.member_one", mem_idx);
		
		sqlSession.close();

		return vo;
	}
	
	
	//mem_id에 해당되는 1명(건)의 정보
	public MemberVo selectOne(String mem_id) {
		
		MemberVo vo = null;

		SqlSession sqlSession = factory.openSession();
		
		vo = sqlSession.selectOne("member.member_one_id", mem_id);
		
		sqlSession.close();
		
		return vo;
	}

	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		int res = 0;

		SqlSession sqlSession = factory.openSession();
		res = sqlSession.insert("member.member_insert", vo);
		sqlSession.close();
		return res;
	}

	public int update(MemberVo vo) {
		// TODO Auto-generated method stub
		int res = 0;

		SqlSession sqlSession = factory.openSession();
		res = sqlSession.update("member.member_update", vo);
		sqlSession.close();
		return res;
	}

	public int delete(int mem_idx) {
		// TODO Auto-generated method stub
		int res = 0;

		SqlSession sqlSession = factory.openSession();
		res = sqlSession.delete("member.member_delete", mem_idx);
		sqlSession.close();
		return res;
	}
	
	
	
}
