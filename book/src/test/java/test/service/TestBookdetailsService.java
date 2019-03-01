package test.service;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.black.book.entity.Bookdetails;
import cn.black.book.service.BookdetailsService;
import cn.black.book.util.NoteResult;
import test.TestBase;



public class TestBookdetailsService extends TestBase{
	private BookdetailsService bookdetailsService;
	
	@Before
	public void init() {
		bookdetailsService = getContext().getBean("bookdetailsService",BookdetailsService.class);
	}
	
	//查询所有章节
	@Test
	public void loadBookdetails() {
		NoteResult<List<Bookdetails>> result = bookdetailsService.loadBookdetails(1);
		System.out.println(result);
	}
	//查询所有内容
	@Test
	public void findbookdetails_content() {
		NoteResult<List<Bookdetails>> result = bookdetailsService.findbookdetails_content(1, 1);
		System.out.println(result);
	}
	//增加
	@Test
	public void addBookdetails() {
		String Bookdetails = "{book_id:\"1\","
				 + "bookdetails_volume_title:\"测试\","
				 + "bookdetails_chapter_title:\"测试小说标题\","
				 + "bookdetails_content:\"测试小说内容\","
				 + "bookdetails_content_count:\"100\","
				 + "bookdetails_update_time:\""
				 + System.currentTimeMillis()
				 + "\"}";
		NoteResult<List<Bookdetails>> result = bookdetailsService.addBookdetails(Bookdetails);
		System.out.println(result);
	}
	//删除
	@Test
	public void removeBookdetails() {
		NoteResult<List<Bookdetails>> result = bookdetailsService.removeBookdetails(1, 363);
		System.out.println(result);
	}
	//修改
	@Test
	public void updateBookdetails() {
		String Bookdetails = "{book_id:\"1\","
				 + "bookdetails_id:\"364\","
				 + "bookdetails_volume_title:\"测试\","
				 + "bookdetails_chapter_title:\"测试小说标题\","
				 + "bookdetails_content:\"测试小说内容2333333\","
				 + "bookdetails_content_count:\"100\","
				 + "bookdetails_update_time:\""
				 + System.currentTimeMillis()
				 + "\"}";
		NoteResult<List<Bookdetails>> result = bookdetailsService.updateBookdetails(Bookdetails);
		System.out.println(result);
	}
}
