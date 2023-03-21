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

	//��? : �ܺο��� new ProductDao()�������� ���ϵ���
	private ProductDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//ī�װ��� ��ȸ
	public List<ProductVo> selectList(String category){
		
		List<ProductVo> list = null;
		//1.SqlSession ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		list = sqlSession.selectList("product.product_list", category);
		
		//3.�ݱ�
		sqlSession.close();
		
		return list;
	}

	public ProductVo selectOne(int p_idx) {
		// TODO Auto-generated method stub
		ProductVo vo = null;
		
		//1.SqlSession ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		vo = sqlSession.selectOne("product.product_one_idx", p_idx);
		
		//3.�ݱ�
		sqlSession.close();
		
		return vo;
	}
	
	public ProductVo selectOne(String p_num) {
		// TODO Auto-generated method stub
		ProductVo vo = null;
		
		//1.SqlSession ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		vo = sqlSession.selectOne("product.product_one_num", p_num);
		
		//3.�ݱ�
		sqlSession.close();
		
		return vo;
	}
	
	public int insert(ProductVo vo) {
		
		int res = 0;
		
		SqlSession sqlSession = factory.openSession(true);
		
		res = sqlSession.insert("product.product_insert", vo);
		
		sqlSession.close();
		
		return res;
		
	}
	
	
	
	
	
	
	
	
}
