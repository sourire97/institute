package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.DeptVo;

public class DeptDao {

	SqlSessionFactory factory;
	
	//single-ton pattern
	static DeptDao single = null;

	public static DeptDao getInstance() {
		//�������� �ʾ����� �����ض�
		if (single == null)
			single = new DeptDao();

		return single;
	}

	//��? : �ܺο��� new DeptDao()�������� ���ϵ���
	private DeptDao() {
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	public List<DeptVo> selectList(){
		
		List<DeptVo> list = null;
		
		//1.SqlSession ������
		SqlSession sqlSession = factory.openSession();
		
		//2.�۾�����
		list = sqlSession.selectList("dept.dept_list");
		
		//3.�ݱ�
		sqlSession.close();
		
		return list;
		
	}
	
}
