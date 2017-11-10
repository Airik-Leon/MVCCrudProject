package tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import data.BlogDAOImpl;
import data.PostDAO;
import data.User;

public class BlogDAOTests {
	
	PostDAO dao; 
	@Before
	public void setUp() {
		dao = new BlogDAOImpl(); 
	}
	@After 
	public void tearDown() {
		dao = null; 
	}
	@Test
	public void test_Load_OF_USERS() {
		System.out.println(dao.getUsers().size());
	}

}
