package service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import dao.ProductDao;
import vo.ProductVo;

public class ProductServiceImpl implements ProductService {

	@Autowired
	HttpServletResponse response;

	ProductDao product_in_dao;
	ProductDao product_out_dao;
	ProductDao product_remain_dao;
	
	public ProductServiceImpl(ProductDao product_in_dao, ProductDao product_out_dao, ProductDao product_remain_dao) {
		super();
		this.product_in_dao = product_in_dao;
		this.product_out_dao = product_out_dao;
		this.product_remain_dao = product_remain_dao;
	}
	
	@Override
	public Map selectTotalList() {
		// TODO Auto-generated method stub
		
		List<ProductVo> in_list = product_in_dao.selectList();
		List<ProductVo> out_list = product_out_dao.selectList();
		List<ProductVo> remain_list = product_remain_dao.selectList();
		
		Map map = new HashMap();
		map.put("in_list", in_list);
		map.put("out_list", out_list);
		map.put("remain_list", remain_list);
		
		return map;
	}

	@Override
	public int insert_in(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		
		//입고등록
		int res = product_in_dao.insert(vo);
		
		//재고테이블에 상품이름으로 조회
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());
		
		//상품이 없으면
		if(remainVo == null) {
			
			//재고테이블에 새로 등록
			product_remain_dao.insert(vo);
			
		}else {//상품이 이미 등록되어 있으면 수정
			
			// 재고수량 	= 기존재고수량      	+ 입력수량
			int cnt     = remainVo.getCnt() + vo.getCnt();
			remainVo.setCnt(cnt);
			
			res = product_remain_dao.update(remainVo);
			
		}
		
		return 0;
	}

	@Override
	public int insert_out(ProductVo vo) throws Exception {
		// TODO Auto-generated method stub
		
		//출고등록
		int res = product_out_dao.insert(vo);
		
		//재고테이블에 상품이름으로 조회
		ProductVo remainVo = product_remain_dao.selectOne(vo.getName());
			
		//출고할 상품이 없는경우
		if(remainVo==null) {
			//예외를 발생
			throw new Exception("remain_not");
			/*
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 상품이 없습니다'); location.href='list.do';</script>");
			out.flush();
			*/
		}
		
		//출고수량이 더 큰경우
		if(vo.getCnt() > remainVo.getCnt()) {
			//예외를 발생
			throw new Exception("remain_lack");
		}
			
		//상품이 이미 등록되어 있으면 수정
		// 재고수량 	= 기존재고수량      	- 입력수량
		int cnt     = remainVo.getCnt() - vo.getCnt();
		remainVo.setCnt(cnt);
		
		res = product_remain_dao.update(remainVo);
		
		return res;
	}
	
	//입고취소
	@Override
	public int delete_in(int idx) throws Exception {
		// TODO Auto-generated method stub
		
		//1.idx에 해당되는 입고상품정보 얻어온다
		ProductVo inVo = product_in_dao.selectOne(idx);
				
		//2.입고정보 삭제
		int res = product_in_dao.delete(idx);
		
		//3.상품명을 이용한 재고상품정보 얻어온다
		ProductVo remainVo = product_remain_dao.selectOne(inVo.getName());
				
		//4.(입고취소수량 > 재고수량) 이면 취소처리 : in_remain_lack
		if(inVo.getCnt() > remainVo.getCnt()) {
			//예외를 발생
			throw new Exception("in_remain_lack");
		}
		
		//입고 취소수량이 재고수량과 같을 경우
		else if(inVo.getCnt() == remainVo.getCnt()) {
			
			//재고정보에서 삭제
			res = product_remain_dao.delete(idx);
		}
		
		//5.재고수량 수정
		int cnt     = remainVo.getCnt() - inVo.getCnt();
		remainVo.setCnt(cnt);
		
		res = product_remain_dao.update(remainVo);
		
		return res;
	}

	//출고취소
	@Override
	public int delete_out(int idx) throws Exception {
		// TODO Auto-generated method stub
		
		//1.idx에 해당되는 출고상품정보 얻어온다
		ProductVo outVo = product_out_dao.selectOne(idx);
				
		//2.출고정보 삭제
		int res = product_out_dao.delete(idx);
		
		//3.상품명을 이용한 재고상품정보 얻어온다
		ProductVo remainVo = product_remain_dao.selectOne(outVo.getName());
				
		//4.(출고취소수량 > 재고수량) 이면 취소처리 : out_remain_lack
		if(outVo.getCnt() > remainVo.getCnt()) {
			//예외를 발생
			throw new Exception("out_remain_lack");
		}
		
		//5.재고수량 수정
		int cnt     = remainVo.getCnt() + outVo.getCnt();
		remainVo.setCnt(cnt);
		
		res = product_remain_dao.update(remainVo);
		
		return res;
		
	}

	
}
