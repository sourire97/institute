package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TestDao;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestDao  test_dao;
	
	public TestServiceImpl() {
		System.out.println("--2.TestServiceImpl()--");
		System.out.println(test_dao);
	}

	@Override
	public List selectTotalList() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
