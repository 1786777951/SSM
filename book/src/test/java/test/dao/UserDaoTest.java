package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.black.book.dao.UserDao;
import cn.black.book.entity.User;
import test.TestBase;

public class UserDaoTest extends TestBase{
	private UserDao userDao;
	@Before
	public void init() {
		userDao = getContext().getBean("userDao",UserDao.class);
	}
	
	@Test
	public void updateUser() {
		User user = new User();
		user.setUser_id(10060);
		user.setUser_phone("145465465456");
		user.setUser_name("Ñó´Ð");
		user.setUser_account("123456789");
		user.setUser_sex(0);
		user.setUser_password("123456");
		user.setUser_email("123456@qq.com");
		user.setUser_type(0);
		user.setUser_img_id("0");
		user.setUser_city("¶«Ý¸");
		user.setUser_Introduce("À²À²À²À²À²");
		int length = userDao.updateUser(user);
		System.out.println(length);
	}
	
	@Test
	public void UserDao() {
		User user = new User();
		user.setUser_name("Ñó´Ð");
		user.setUser_account("123456789");
		user.setUser_sex(0);
		user.setUser_password("123456");
		user.setUser_email("123456@qq.com");
		user.setUser_type(0);
		user.setUser_img_id("0");
		user.setUser_city("¶«Ý¸");
		user.setUser_Introduce("À²À²À²À²À²");
		int length = userDao.registerUser(user);
		System.out.println(length);
	}
	
	@Test
	public void loginDao() {
		List<User> user = userDao.findUser();
		System.out.println(user);
	}
}
