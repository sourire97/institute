package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.GogekVo;
import vo.SawonVo;

public class GogekDao {
	//single-ton : ��ü1���� ���� ����
	static GogekDao single = null;

	//SessionFactory�����ϴ� ��ü
	SqlSessionFactory factory;
	
	public GogekDao() {
		super();
		// TODO Auto-generated constructor stub
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	public static GogekDao getInstance() {

		if (single == null)
			single = new GogekDao();

		return single;
	}
	
	//��ü��ȸ
	public List<GogekVo> selectList(){
		
		List<GogekVo> list = null;
		
		//1.Mybastis�۾���ü ���´�
		SqlSession sqlSession = factory.openSession();
		
		//2. �۾� ����
		list = sqlSession.selectList("gogek.gogek_list");
		
		//3.�۾� �� �����۾�(�ݱ�) : conn.close()�� ����
		sqlSession.close();
		
		return list;
		
	}
	
	
}
