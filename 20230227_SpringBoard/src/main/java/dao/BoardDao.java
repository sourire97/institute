package dao;

import java.util.List;
import java.util.Map;

import vo.BoardVo;

public interface BoardDao {

	List<BoardVo>  selectList();
	List<BoardVo>  selectList(Map map);
	BoardVo        selectOne(int b_idx);
	
	//새글쓰기
	int insert(BoardVo vo); 
	
	//답글쓰기
	int reply(BoardVo vo);
	int update_step(BoardVo vo);
	
	//조회수증가
	int update_readhit(int b_idx);
	
	int update(BoardVo vo);
	int delete(int b_idx);
	
	
}
