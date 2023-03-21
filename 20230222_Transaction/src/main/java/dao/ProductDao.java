package dao;

import java.util.List;

import vo.ProductVo;

public interface ProductDao {

	//조회명령
	List<ProductVo> selectList();
	ProductVo selectOne(int idx);
	default ProductVo selectOne(String name) {return null;}
	
	//DML 명령
	int insert(ProductVo vo);
	int delete(int idx);
	int update(ProductVo vo);

}
