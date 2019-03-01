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
	
	//��ѯ������
	@Test
	public void findBooks() {
		List<Book> list = bookDao.findBooks();
		for(Book book:list) {
			System.out.println(book);
		}
	}
	
	//��ҳ
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
	
	//���������Ͳ�ѯ
	@Test
	public void findBookByType() {
		int page = 1;
		int limit = 10;
		int start = (page-1)*limit;
		List<Book> list = bookDao.findBookByType("����", start, limit);
		for(Book book:list) {
			System.out.println(book);
		}
	}
	
	//����������ѯ
		@Test
		public void findBookByTitle() {
			List<Book> list = bookDao.findBookByTitle("",0,50,0,10000000,3);
				for(Book i:list) {
					System.out.println(i.getBook_id());
				}
		}
		
	//��ѯ����
		@Test
		public void findGROUPBY() {
			List<Map<String,Integer>> map = bookDao.findGROUPBY();
				System.out.println(map);
		}
}
