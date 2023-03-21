package dao;

import java.util.List;
import java.util.Map;

import vo.VisitVo;

public interface VisitDao {
	
	public List<VisitVo> selectList();

	public List<VisitVo> selectList(Map<String, String> map);

	public VisitVo selectOne(int idx);

	public int insert(VisitVo vo);

	public int delete(int idx);
		
	public int update(VisitVo vo);

}
