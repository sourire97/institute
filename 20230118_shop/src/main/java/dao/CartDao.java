package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.CartVo;
import vo.MemberVo;

public class CartDao {

	SqlSessionFactory factory;
	
	//single-ton pattern
	static CartDao single = null;

	public static CartDao getInstance() {
		//생성되지 않았으면 생성해라
		if (single == null)
			single = new CartDao();

		return single;
	}

	//왜? : 외부에서 new MemberDao()생성하지 못하도록
	private CartDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//전체조회
	public List<CartVo> selectList(int mem_idx) {

		List<CartVo> list = null;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		//2.작업수행
		list = sqlSession.selectList("cart.cart_list", mem_idx);
		
		//3.닫기
		sqlSession.close();
		
		return list;
	}
	
	//전체조회
	public int selectTotalAmount(int mem_idx) {

		int total = 0;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		total = sqlSession.selectOne("cart.cart_total_amount", mem_idx);
		
		//3.닫기
		sqlSession.close();
		
		return total;
	}

	public CartVo selectOne(CartVo vo) {
		// TODO Auto-generated method stub
		CartVo resVo = null;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		resVo = sqlSession.selectOne("cart.cart_insert", vo);
		
		//3.닫기
		sqlSession.close();
		
		return resVo;
	}
	
	public int update(CartVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession(true);
		//2.작업수행
		res = sqlSession.insert("cart.cart_update", vo);
		//3.닫기
		sqlSession.close();
		
		return res;
	}

	public int delete(int c_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession(true);
		//2.작업수행
		res = sqlSession.delete("cart.cart_delete", c_idx);
		//3.닫기
		sqlSession.close();
				
		return res;
	}
	
	public int delete_all(Map map) {
		// TODO Auto-generated method stub
		int res = 0;
		
		//1.SqlSession얻어오기
		SqlSession sqlSession = factory.openSession(true); //true : auto commit
		
		//2.작업수행
		res = sqlSession.delete("cart.cart_delete_all", map);
		
		//3.닫기
		sqlSession.close();
		
		
		return res;
	}

	public int insert(CartVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		SqlSession sqlSession = factory.openSession(true);
		res = sqlSession.insert("cart.cart_insert", vo);
		sqlSession.close();

		return res;
	}
		
}
