package cn.black.book.service;

import java.util.List;

import cn.black.book.entity.Bookdetails;
import cn.black.book.util.NoteResult;

public interface BookdetailsService {
	//查询书里所有的章节
	public NoteResult<List<Bookdetails>> loadBookdetails(Integer book_id);
	//获取章节里所有的内容
	public NoteResult<List<Bookdetails>> findbookdetails_content(Integer book_id, Integer bookdetails_id);
	//增加章节
	public NoteResult<List<Bookdetails>> addBookdetails(String Bookdetails);
	//删除章节
	public NoteResult<List<Bookdetails>> removeBookdetails(Integer book_id, Integer bookdetails_id);
	//修改章节
	public NoteResult<List<Bookdetails>> updateBookdetails(String Bookdetails);
}
