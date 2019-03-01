package cn.black.book.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.black.book.dao.BookdetailsDao;
import cn.black.book.entity.Bookdetails;
import cn.black.book.util.NoteResult;
import net.sf.json.JSONObject;

@Service("bookdetailsService")
public class BookdetailsServiceImpl implements BookdetailsService{
	@Autowired
	@Qualifier("bookdetailsDao")
	BookdetailsDao bookdetailsDao;
	
	//查询书的章节
	public NoteResult<List<Bookdetails>> loadBookdetails(Integer book_id) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		List<Bookdetails> list = bookdetailsDao.findBookdetails(book_id);
		if(list == null) {
			result.setStatus(1);
			result.setMsg("查询小说章节失败!");
		}
		result.setStatus(0);
		result.setMsg("查询小说章节成功!");
		result.setData(list);
		return result;
	}
	
	//查询章节内容
	public NoteResult<List<Bookdetails>> findbookdetails_content(Integer book_id, Integer bookdetails_id) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		List<Bookdetails> list = bookdetailsDao.findbookdetails_content(book_id, bookdetails_id);
		if(list == null) {
			result.setStatus(1);
			result.setMsg("查询小说章节内容失败!");
		}
		result.setStatus(0);
		result.setMsg("查询小说章节内容成功!");
		result.setData(list);
		return result;
	}
	
	//增加章节
	public NoteResult<List<Bookdetails>> addBookdetails(String Bookdetails) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		Bookdetails bookdetails = new Bookdetails();
		JSONObject json = JSONObject.fromObject(Bookdetails);
		//获取信息
		Integer book_id = json.getInt("book_id");
		String bookdetails_volume_title = json.getString("bookdetails_volume_title");
		String bookdetails_chapter_title = json.getString("bookdetails_chapter_title");
		String bookdetails_content = json.getString("bookdetails_content");
		Integer bookdetails_content_count = json.getInt("bookdetails_content_count");
		Long bookdetails_update_time = json.getLong("bookdetails_update_time");
		//插入信息
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bookdetails.setBook_id(book_id);
		bookdetails.setBookdetails_volume_title(bookdetails_volume_title);
		bookdetails.setBookdetails_chapter_title(bookdetails_chapter_title);
		bookdetails.setBookdetails_content(bookdetails_content);
		bookdetails.setBookdetails_content_count(bookdetails_content_count);
		bookdetails.setBookdetails_update_time(simpleDateFormat.format(new Date(bookdetails_update_time)));
		int length = bookdetailsDao.addBookdetails(bookdetails);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("新增小说章节"+bookdetails_chapter_title+"失败");
			return result;
		}
		result.setStatus(0);
		result.setMsg("新增小说章节"+bookdetails_chapter_title+"成功");
		return result;
	}

	//删除章节
	public NoteResult<List<Bookdetails>> removeBookdetails(Integer book_id, Integer bookdetails_id) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		int length = bookdetailsDao.removeBookdetails(book_id, bookdetails_id);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("删除小说章节"+bookdetails_id+"失败");
			return result;
		}
		result.setStatus(1);
		result.setMsg("删除小说章节"+bookdetails_id+"成功");
		return result;
	}

	//修改章节
	public NoteResult<List<Bookdetails>> updateBookdetails(String Bookdetails) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		Bookdetails bookdetails = new Bookdetails();
		JSONObject json = JSONObject.fromObject(Bookdetails);
		if (json == null || json.isNullObject())
		{
			result.setStatus(3);
			result.setMsg("未找到小说内容");
			return result;
		}else {
			//获取信息
			Integer book_id = json.getInt("book_id");
			Integer bookdetails_id = json.getInt("bookdetails_id");
			String bookdetails_volume_title = json.getString("bookdetails_volume_title");
			String bookdetails_chapter_title = json.getString("bookdetails_chapter_title");
			String bookdetails_content = json.getString("bookdetails_content");
			Integer bookdetails_content_count = json.getInt("bookdetails_content_count");
			Long bookdetails_update_time = json.getLong("bookdetails_update_time");
			//插入信息
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			bookdetails.setBook_id(book_id);
			bookdetails.setBookdetails_id(bookdetails_id);
			bookdetails.setBookdetails_volume_title(bookdetails_volume_title);
			bookdetails.setBookdetails_chapter_title(bookdetails_chapter_title);
			bookdetails.setBookdetails_content(bookdetails_content);
			bookdetails.setBookdetails_content_count(bookdetails_content_count);
			bookdetails.setBookdetails_update_time(simpleDateFormat.format(new Date(bookdetails_update_time)));
			
			int length = bookdetailsDao.updateBookdetails(bookdetails);
			if(length != 1) {
				result.setStatus(1);
				result.setMsg("修改小说章节"+bookdetails_chapter_title+"失败");
				return result;
			}
			result.setStatus(0);
			result.setMsg("修改小说章节"+bookdetails_chapter_title+"成功");
			return result;
		}
	}

}
