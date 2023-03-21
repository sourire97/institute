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
		//�������� �ʾ����� �����ض�
		if (single == null)
			single = new MemberDao();

		return single;
	}

	//��? : �ܺο��� new MemberDao()�������� ���ϵ���
	private MemberDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//��ü��ȸ
	public List<MemberVo> selectList() {

		List<MemberVo> list = null;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession();
		//2.�۾�����
		list = sqlSession.selectList("member.member_list");
		
		//3.�ݱ�
		sqlSession.close();
		
		return list;
	}
	
	
	//mem_idx�� �ش�Ǵ� 1��(��)�� ����
	public MemberVo selectOne(int mem_idx) {

		MemberVo vo = null;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession();
		//2.�۾�����
		vo = sqlSession.selectOne("member.member_one_idx", mem_idx);
		
		//3.�ݱ�
		sqlSession.close();
		return vo;
	}
	
	
	//mem_id�� �ش�Ǵ� 1��(��)�� ����
	public MemberVo selectOne(String mem_id) {
		
		MemberVo vo = null;
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession();
		//2.�۾�����
		vo = sqlSession.selectOne("member.member_one_id", mem_id);
		
		//3.�ݱ�
		sqlSession.close();
		return vo;
	}

	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession(true);
		//2.�۾�����
		res = sqlSession.insert("member.member_insert", vo);
		//3.�ݱ�
		sqlSession.close();
		
		return res;
	}

	public int update(MemberVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession(true);
		//2.�۾�����
		res = sqlSession.insert("member.member_update", vo);
		//3.�ݱ�
		sqlSession.close();
		
		return res;
	}

	public int delete(int mem_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession(true);
		//2.�۾�����
		res = sqlSession.delete("member.member_delete", mem_idx);
		//3.�ݱ�
		sqlSession.close();
				
		return res;
	}
	
	
	
}
