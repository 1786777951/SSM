package test.dao;

import org.junit.Before;
import org.junit.Test;

import cn.black.book.dao.AdminDao;
import cn.black.book.entity.Admin;
import test.TestBase;

public class AdminDaoTest extends TestBase{
	private AdminDao adminDao;
	@Before
	public void init() {
		adminDao = getContext().getBean("adminDao",AdminDao.class);
	}
	
	@Test
	public void loginTest() {
		Admin admin = adminDao.findAdminAccount("admin", "123456");
		System.out.println(admin);
	}
}

