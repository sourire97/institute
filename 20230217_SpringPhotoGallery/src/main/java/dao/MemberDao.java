package dao;

import java.util.List;

import vo.MemberVo;

public interface MemberDao {

	public List<MemberVo> selectList();	
	
	public MemberVo selectOne(int mem_idx);	
	public MemberVo selectOne(String mem_id);
	
	public int insert(MemberVo vo);
	public int update(MemberVo vo);
	public int delete(int mem_idx);	

}