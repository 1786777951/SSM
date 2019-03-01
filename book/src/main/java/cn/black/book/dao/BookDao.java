package cn.black.book.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.black.book.entity.Book;

/**
 * 书本信息
 */
public interface BookDao {
	//查询所有的书
	public List<Book> findBooks();
	
	//查看今天更新
	public List<Book> updateDay();
	
	//查询一本书
	public List<Book> findBookById(Integer book_id);
	public Book findBookByIds(Integer book_id);
	
	//Page参数表示显示第几页的数据，pageSize表示每页显示的数据条数
	//查询所有书分页
	public List<Book> findBook(@Param("start")Integer start,@Param("end")Integer end);
	
	//查询类型书分页
	public List<Book> findBookByType(@Param("book_type")String book_type,@Param("start")Integer start,@Param("limit")Integer limit);

	//根据书名查询
	public List<Book> findBookByTitle(@Param("book_title")String book_title,@Param("start")Integer start,@Param("limit")Integer limit,@Param("wordCount")Integer wordCount,@Param("wordCounts")Integer wordCounts,@Param("record")Integer record);
	public List<Book> findBookByTitles(@Param("book_title")String book_title);
	
	//新增
	public int addBook(Book book);
	//删除
	public int removeBook(Integer book_id);
	//更新
	public int updateBook(Book book);
	
	//查询分组信息
	public List<Map<String,Integer>> findGROUPBY();
}
