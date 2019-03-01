package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.black.book.dao.BookDao;
import cn.black.book.dao.BookdetailsDao;
import cn.black.book.entity.Bookdetails;
import test.TestBase;

public class BookdetailsTest extends TestBase{
	private BookdetailsDao bookdetailsDao;
	
	@Before
	public void init() {
		bookdetailsDao = getContext().getBean("bookdetailsDao",BookdetailsDao.class);
	}
	
	@Test
	public void findbookdetails_content() {
		List<Bookdetails> list= bookdetailsDao.findbookdetails_content(1, 1);
		System.out.println(list);
	}
}
