package cn.black.book.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.AdviceName;

import cn.black.book.entity.Book;
import cn.black.book.util.NoteResult;

public interface BookService {
	//��ѯ���е���
	public NoteResult<List<Book>> loadBook();
	//��ѯ�������
	public NoteResult<List<Book>> updateBookDay();
	//��ѯһ����
	public NoteResult<List<Book>> loadBookById(Integer book_id);
	//����ID��ѯһЩ��
	public NoteResult<List<Book>> loadBookByIdList(ArrayList<Integer> list);
	//��ȡ���ҳ
	public NoteResult<List<Book>> loadBooks(Integer page, Integer pageSize);
	//��ѯ�������ҳ
	public NoteResult<List<Book>> loadBookByType(String book_type,Integer page, Integer limit);
	//����������ѯ
	public NoteResult<List<Book>> loadBookByTitle(String book_title,Integer page, Integer limit,Integer wordCount,Integer wordCounts,Integer record);
	public NoteResult<List<Book>> loadBookByTitles(String book_title);
	//������
	public NoteResult<List<Book>> addBook(String books);
	//ɾ����
	public NoteResult<List<Book>> removeBook(Integer book_id);
	//�޸���
	public NoteResult<List<Book>> updateBook(String books);
	//��ѯ�����
	public NoteResult<List> findGROUPBY();
}
