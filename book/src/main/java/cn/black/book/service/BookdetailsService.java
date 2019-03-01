package cn.black.book.service;

import java.util.List;

import cn.black.book.entity.Bookdetails;
import cn.black.book.util.NoteResult;

public interface BookdetailsService {
	//��ѯ�������е��½�
	public NoteResult<List<Bookdetails>> loadBookdetails(Integer book_id);
	//��ȡ�½������е�����
	public NoteResult<List<Bookdetails>> findbookdetails_content(Integer book_id, Integer bookdetails_id);
	//�����½�
	public NoteResult<List<Bookdetails>> addBookdetails(String Bookdetails);
	//ɾ���½�
	public NoteResult<List<Bookdetails>> removeBookdetails(Integer book_id, Integer bookdetails_id);
	//�޸��½�
	public NoteResult<List<Bookdetails>> updateBookdetails(String Bookdetails);
}
