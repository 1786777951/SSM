package test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.black.book.dao.AdminDao;
import cn.black.book.entity.Admin;
public class DataBaseTest {
	String [] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
	ApplicationContext ctx =new ClassPathXmlApplicationContext(conf);

	//测试连接数据库
	@Test
	public void testDao() {
		AdminDao dao = ctx.getBean("adminDao",AdminDao.class);
		List<Admin> list = dao.findAdmin();
		for(Admin admin : list) {
			System.out.println(admin);
		}
	}
}
