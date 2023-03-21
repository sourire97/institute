package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.PhotoVo;

public class PhotoDao {
	
	SqlSessionFactory factory;

	//single-ton pattern
	static PhotoDao single = null;

	public static PhotoDao getInstance() {
		//�������� �ʾ����� �����ض�
		if (single == null)
			single = new PhotoDao();

		return single;
	}

	//��? : �ܺο��� new PhotoDao()�������� ���ϵ���
	private PhotoDao() {
		// TODO Auto-generated constructor stub
		
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	
	}
	
	public List<PhotoVo> selectList() {

		List<PhotoVo> list = null;
		
		//1. MYbatis �۾���ü ������
		SqlSession sqlSession = factory.openSession();
		
		//2. �۾�����
		list = sqlSession.selectList("photogallery.photogallery_list");
		
		//3. �ݱ�
		sqlSession.close();
		
		return list;
	}
	
	public PhotoVo selectOne(int p_idx) {

		PhotoVo vo = null;
		//1. MYbatis �۾���ü ������
		SqlSession sqlSession = factory.openSession();
		
		//2. �۾�����
		vo = sqlSession.selectOne("photogallery.photogallery_one", p_idx);
		
		//3. �ݱ�
		sqlSession.close();
		
		return vo;
	}
	
	public int insert(PhotoVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1. MYbatis �۾���ü ������
		SqlSession sqlSession = factory.openSession(true);
		
		//2. �۾�����
		res = sqlSession.insert("photogallery.photogallery_insert", vo);
		
		//3. �ݱ�
		sqlSession.close();
		
		return res;
	}

	public int update(PhotoVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1. MYbatis �۾���ü ������
		SqlSession sqlSession = factory.openSession(true);
		
		//2. �۾�����
		res = sqlSession.update("photogallery.photogallery_update", vo);
		
		//3. �ݱ�
		sqlSession.close();
		
		return res;
	}
	
	public int delete(int p_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1. MYbatis �۾���ü ������
		SqlSession sqlSession = factory.openSession(true);
		
		//2. �۾�����
		res = sqlSession.delete("photogallery.photogallery_delete", p_idx);
		
		//3. �ݱ�
		sqlSession.close();
		
		return res;
	}
	
	public int update_filename(PhotoVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1. MYbatis �۾���ü ������
		SqlSession sqlSession = factory.openSession(true);
		
		//2. �۾�����
		res = sqlSession.update("photogallery.photogallery_update_filename", vo);
		
		//3. �ݱ�
		sqlSession.close();
		
		return res;
	}
	
}
