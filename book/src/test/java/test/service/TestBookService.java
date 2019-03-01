package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.black.book.entity.Book;
import cn.black.book.service.BookService;
import cn.black.book.util.NoteResult;
import test.TestBase;

public class TestBookService extends TestBase{
	private BookService bookService;
	
	@Before
	public void init() {
		bookService = getContext().getBean("bookService",BookService.class);
	}
	
	//查询所有书
	@Test
	public void loadBook() {
		NoteResult<List<Book>> result = bookService.loadBook();
		System.out.println(result);
	}
	
	//查询一本书
	@Test
	public void loadBookById() {
		NoteResult<List<Book>> result = bookService.loadBookById(1);
		System.out.println(result);
	}
	
	//查询书分页
	@Test
	public void loadBooks() {
		NoteResult<List<Book>> result = bookService.loadBooks(2, 10);
		System.out.println(result);
	}
	
	//查询书类型分页
	@Test
	public void loadBookByType() {
		NoteResult<List<Book>> result = bookService.loadBookByType("1", 1, 10);
		System.out.println(result);
	}
	
	//查询书标题分页
	@Test
	public void loadBookByTitle() {
		NoteResult<List<Book>> result = bookService.loadBookByTitle("",1,10,500000,1000000,3);
		System.out.println(result);
	}
	
	//增加书
	@Test
	public void addBook() {
		String books = "{book_id:\"45\","
					 + "book_title:\"测试\","
					 + "book_img:\"img/0.jpg\","
					 + "user_id:\"10000\","
					 + "book_explain:\"这是一本测试\","
					 + "book_type:\"23333\","
					 + "book_count:\"0\","
					 + "book_update_time:\"0\"}";
		NoteResult<List<Book>> result = bookService.addBook(books);
		System.out.println(result);
	}
}
