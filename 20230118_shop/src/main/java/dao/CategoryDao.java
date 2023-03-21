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
		//생성되지 않았으면 생성해라
		if (single == null)
			single = new CategoryDao();

		return single;
	}

	//왜? : 외부에서 new MemberDao()생성하지 못하도록
	private CategoryDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//전체조회
	public List<CategoryVo> selectList() {

		List<CategoryVo> list = null;
		
		//1.SqlSession얻기
		SqlSession sqlSession = factory.openSession();
		
		//2.작업수행
		list = sqlSession.selectList("category.category_list");
		
		//3.닫기
		sqlSession.close();
		
		return list;
	}
	
}
