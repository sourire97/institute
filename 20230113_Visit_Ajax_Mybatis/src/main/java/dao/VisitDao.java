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
		//�������� �ʾ����� �����ض�
		if (single == null)
			single = new VisitDao();

		return single;
	}

	//��? : �ܺο��� new VisitDao()�������� ���ϵ���
	private VisitDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//���� ��ȸ
	public List<VisitVo> selectList() {

		List<VisitVo> list = null;
		
		//1. MYbatis �۾���ü ������
		SqlSession sqlSession = factory.openSession();
		
		//2. �۾�����
		list = sqlSession.selectList("visit.visit_list");
		
		//3. �ݱ�
		sqlSession.close();
		
		return list;
	}
	
	public List<VisitVo> selectList(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<VisitVo> list = null;
		//1.Mybatis�۾���ü ������
		SqlSession  sqlSession = factory.openSession();
		//2.�۾�����
		list = sqlSession.selectList("visit.visit_list_condition", map);		
		//3.�ݱ�
		sqlSession.close();
		
		return list;
	}
	
	
	//idx�� �ش�Ǵ� �Խù� 1�� ��ȸ
	public VisitVo selectOne(int idx) {

		VisitVo vo = null;
		//1.Mybatis�۾���ü ������
		SqlSession  sqlSession = factory.openSession();
		//2.�۾�����                 mapper_id        parameter  
		vo = sqlSession.selectOne("visit.visit_one" , idx);		
		//3.�ݱ�
		sqlSession.close();
		
		return vo;
	}

	//�߰��ϱ�
	public int insert(VisitVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//���1. Ʈ����� ó�� ���
		/*
		//1.Mybatis�۾���ü ������
		SqlSession  sqlSession = factory.openSession();
		//2.�۾�����                 mapper id       , parameter     
		res = sqlSession.insert("visit.visit_insert" ,   vo  );	
		
		//2-1.commit(����)
		sqlSession.commit();
		
		//3.�ݱ�
		sqlSession.close();
		*/
		
		//���2. auto commit
		
		//1.Mybatis�۾���ü ������
		SqlSession  sqlSession = factory.openSession(true);
		//2.�۾�����                 mapper id       , parameter     
		res = sqlSession.insert( "visit.visit_insert" , vo );	
		//3.�ݱ�
		sqlSession.close();
		
		return res;
	}
	
	//����
	public int delete(int idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.Mybatis�۾���ü ������
		SqlSession  sqlSession = factory.openSession(true);
		//2.�۾�����                 mapper id       , parameter     
		res = sqlSession.delete( "visit.visit_delete" , idx );	
		//3.�ݱ�
		sqlSession.close();
		
		return res;
	}
	
	public int update(VisitVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.Mybatis�۾���ü ������
		SqlSession  sqlSession = factory.openSession(true);
		//2.�۾�����                 mapper id       , parameter     
		res = sqlSession.delete( "visit.visit_update" , vo );	
		//3.�ݱ�
		sqlSession.close();
		
		return res;
	}
}
