package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.ProductVo;

public class ProductDao {

	SqlSessionFactory factory;
	//single-ton pattern
	static ProductDao single = null;

	public static ProductDao getInstance() {
		//�������� �ʾ����� �����ض�
		if (single == null)
			single = new ProductDao();

		return single;
	}

	//��?: �ܺο��� new ProductDao() �������� ���ϵ���
	private ProductDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//ī�װ��� ��ȸ
	public List<ProductVo> selectList(String category){
		
		List<ProductVo> list = null;
		
		//1. SqlSession ������
		SqlSession sqlSession = factory.openSession();
		
		//2. �۾� ����
		list = sqlSession.selectList("product.product_list", category);
		
		//3. �ݱ�
		sqlSession.close();
		
		return list;
	}

	public ProductVo selectOne(int p_idx) {
		// TODO Auto-generated method stub
		
		ProductVo vo = null;
		
		//1. SqlSession ������
		SqlSession sqlSession = factory.openSession();
		
		//2. �۾� ����
		vo = sqlSession.selectOne("product.product_one", p_idx);
		
		//3. �ݱ�
		sqlSession.close();
		
		return vo;
	}
	
	public int insert(ProductVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		//1. Mybatis �۾� ��ü ������
		SqlSession sqlSession = factory.openSession(true);
		
		//2. �۾� ����
		res = sqlSession.insert("product.product_insert", vo);

		//3. �ݱ�
		sqlSession.close();
		
		return res;
	}
}
