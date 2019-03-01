package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.black.book.dao.BookDao;
import cn.black.book.entity.Book;
import test.TestBase;

public class BookDaoTest extends TestBase{
	private BookDao bookDao;
	
	@Before
	public void init() {
		bookDao = getContext().getBean("bookDao",BookDao.class);
	}
	
	//查询所有书
	@Test
	public void findBooks() {
		List<Book> list = bookDao.findBooks();
		for(Book book:list) {
			System.out.println(book);
		}
	}
	
	//分页
	@Test
	public void findBook() {
		int page = 1;
		int pageSize = 10;
		int start = (page-1)*pageSize;
		int end = start + pageSize;
		List<Book> list = bookDao.findBook(start, end);
		for(Book book:list) {
			System.out.println(book);
		}
	}
	
	//根据书类型查询
	@Test
	public void findBookByType() {
		int page = 1;
		int limit = 10;
		int start = (page-1)*limit;
		List<Book> list = bookDao.findBookByType("玄幻", start, limit);
		for(Book book:list) {
			System.out.println(book);
		}
	}
	
	//根据书标题查询
		@Test
		public void findBookByTitle() {
			List<Book> list = bookDao.findBookByTitle("",0,50,0,10000000,3);
				for(Book i:list) {
					System.out.println(i.getBook_id());
				}
		}
		
	//查询分类
		@Test
		public void findGROUPBY() {
			List<Map<String,Integer>> map = bookDao.findGROUPBY();
				System.out.println(map);
		}
}
