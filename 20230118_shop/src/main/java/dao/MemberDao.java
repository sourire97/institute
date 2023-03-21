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
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		//2.작업수행
		list = sqlSession.selectList("member.member_list");
		
		//3.닫기
		sqlSession.close();
		
		return list;
	}
	
	
	//mem_idx에 해당되는 1명(건)의 정보
	public MemberVo selectOne(int mem_idx) {

		MemberVo vo = null;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		//2.작업수행
		vo = sqlSession.selectOne("member.member_one_idx", mem_idx);
		
		//3.닫기
		sqlSession.close();
		return vo;
	}
	
	
	//mem_id에 해당되는 1명(건)의 정보
	public MemberVo selectOne(String mem_id) {
		
		MemberVo vo = null;
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		//2.작업수행
		vo = sqlSession.selectOne("member.member_one_id", mem_id);
		
		//3.닫기
		sqlSession.close();
		return vo;
	}

	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession(true);
		//2.작업수행
		res = sqlSession.insert("member.member_insert", vo);
		//3.닫기
		sqlSession.close();
		
		return res;
	}

	public int update(MemberVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession(true);
		//2.작업수행
		res = sqlSession.insert("member.member_update", vo);
		//3.닫기
		sqlSession.close();
		
		return res;
	}

	public int delete(int mem_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession(true);
		//2.작업수행
		res = sqlSession.delete("member.member_delete", mem_idx);
		//3.닫기
		sqlSession.close();
				
		return res;
	}
	
	
	
}
