package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.CategoryVo;

public class CategoryDao {

	SqlSessionFactory factory;
	
	//single-ton pattern
	static CategoryDao single = null;

	public static CategoryDao getInstance() {
		//�������� �ʾ����� �����ض�
		if (single == null)
			single = new CategoryDao();

		return single;
	}

	//��? : �ܺο��� new MemberDao()�������� ���ϵ���
	private CategoryDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//��ü��ȸ
	public List<CategoryVo> selectList() {

		List<CategoryVo> list = null;
		
		//1.SqlSession���
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		list = sqlSession.selectList("category.category_list");
		
		//3.�ݱ�
		sqlSession.close();
		
		return list;
	}
	
}
