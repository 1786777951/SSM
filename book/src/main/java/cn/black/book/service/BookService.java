package cn.black.book.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.AdviceName;

import cn.black.book.entity.Book;
import cn.black.book.util.NoteResult;

public interface BookService {
	//查询所有的书
	public NoteResult<List<Book>> loadBook();
	//查询今天更新
	public NoteResult<List<Book>> updateBookDay();
	//查询一本书
	public NoteResult<List<Book>> loadBookById(Integer book_id);
	//根据ID查询一些书
	public NoteResult<List<Book>> loadBookByIdList(ArrayList<Integer> list);
	//获取书分页
	public NoteResult<List<Book>> loadBooks(Integer page, Integer pageSize);
	//查询类型书分页
	public NoteResult<List<Book>> loadBookByType(String book_type,Integer page, Integer limit);
	//根据书名查询
	public NoteResult<List<Book>> loadBookByTitle(String book_title,Integer page, Integer limit,Integer wordCount,Integer wordCounts,Integer record);
	public NoteResult<List<Book>> loadBookByTitles(String book_title);
	//增加书
	public NoteResult<List<Book>> addBook(String books);
	//删除书
	public NoteResult<List<Book>> removeBook(Integer book_id);
	//修改书
	public NoteResult<List<Book>> updateBook(String books);
	//查询书分类
	public NoteResult<List> findGROUPBY();
}
