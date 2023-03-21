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
		//생성되지 않았으면 생성해라
		if (single == null)
			single = new ProductDao();

		return single;
	}

	//왜? : 외부에서 new ProductDao()생성하지 못하도록
	private ProductDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//카테고리별 조회
	public List<ProductVo> selectList(String category){
		
		List<ProductVo> list = null;
		//1.SqlSession 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		list = sqlSession.selectList("product.product_list", category);
		
		//3.닫기
		sqlSession.close();
		
		return list;
	}

	public ProductVo selectOne(int p_idx) {
		// TODO Auto-generated method stub
		ProductVo vo = null;
		
		//1.SqlSession 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		vo = sqlSession.selectOne("product.product_one_idx", p_idx);
		
		//3.닫기
		sqlSession.close();
		
		return vo;
	}
	
	public ProductVo selectOne(String p_num) {
		// TODO Auto-generated method stub
		ProductVo vo = null;
		
		//1.SqlSession 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		vo = sqlSession.selectOne("product.product_one_num", p_num);
		
		//3.닫기
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
