package cn.black.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.black.book.entity.Book;
import cn.black.book.entity.Bookdetails;

/**
 * �鱾����
 * @author Exception
 *
 */
public interface BookdetailsDao {
	//�������ѯ�����½�
	public List<Bookdetails> findBookdetails(Integer book_id);
	
	//��ѯ�½�����
	public List<Bookdetails> findbookdetails_content(@Param("book_id")Integer book_id,@Param("bookdetails_id")Integer bookdetails_id);
		
	//����
	public int addBookdetails(Bookdetails bookdetails);
	
	//ɾ��
	public int removeBookdetails(@Param("book_id")Integer book_id,@Param("bookdetails_id")Integer bookdetails_id);
	
	//����
	public int updateBookdetails(Bookdetails bookdetails);
	
}
