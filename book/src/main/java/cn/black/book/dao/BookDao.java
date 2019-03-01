package cn.black.book.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.black.book.entity.Book;

/**
 * �鱾��Ϣ
 */
public interface BookDao {
	//��ѯ���е���
	public List<Book> findBooks();
	
	//�鿴�������
	public List<Book> updateDay();
	
	//��ѯһ����
	public List<Book> findBookById(Integer book_id);
	public Book findBookByIds(Integer book_id);
	
	//Page������ʾ��ʾ�ڼ�ҳ�����ݣ�pageSize��ʾÿҳ��ʾ����������
	//��ѯ�������ҳ
	public List<Book> findBook(@Param("start")Integer start,@Param("end")Integer end);
	
	//��ѯ�������ҳ
	public List<Book> findBookByType(@Param("book_type")String book_type,@Param("start")Integer start,@Param("limit")Integer limit);

	//����������ѯ
	public List<Book> findBookByTitle(@Param("book_title")String book_title,@Param("start")Integer start,@Param("limit")Integer limit,@Param("wordCount")Integer wordCount,@Param("wordCounts")Integer wordCounts,@Param("record")Integer record);
	public List<Book> findBookByTitles(@Param("book_title")String book_title);
	
	//����
	public int addBook(Book book);
	//ɾ��
	public int removeBook(Integer book_id);
	//����
	public int updateBook(Book book);
	
	//��ѯ������Ϣ
	public List<Map<String,Integer>> findGROUPBY();
}
