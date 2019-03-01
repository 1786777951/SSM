package cn.black.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.black.book.entity.Book;
import cn.black.book.entity.Bookdetails;

/**
 * 书本详情
 * @author Exception
 *
 */
public interface BookdetailsDao {
	//根据书查询所有章节
	public List<Bookdetails> findBookdetails(Integer book_id);
	
	//查询章节内容
	public List<Bookdetails> findbookdetails_content(@Param("book_id")Integer book_id,@Param("bookdetails_id")Integer bookdetails_id);
		
	//新增
	public int addBookdetails(Bookdetails bookdetails);
	
	//删除
	public int removeBookdetails(@Param("book_id")Integer book_id,@Param("bookdetails_id")Integer bookdetails_id);
	
	//更新
	public int updateBookdetails(Bookdetails bookdetails);
	
}
