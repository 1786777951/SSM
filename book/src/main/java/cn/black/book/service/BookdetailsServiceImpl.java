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
	
	//��ѯ����½�
	public NoteResult<List<Bookdetails>> loadBookdetails(Integer book_id) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		List<Bookdetails> list = bookdetailsDao.findBookdetails(book_id);
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯС˵�½�ʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯС˵�½ڳɹ�!");
		result.setData(list);
		return result;
	}
	
	//��ѯ�½�����
	public NoteResult<List<Bookdetails>> findbookdetails_content(Integer book_id, Integer bookdetails_id) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		List<Bookdetails> list = bookdetailsDao.findbookdetails_content(book_id, bookdetails_id);
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯС˵�½�����ʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯС˵�½����ݳɹ�!");
		result.setData(list);
		return result;
	}
	
	//�����½�
	public NoteResult<List<Bookdetails>> addBookdetails(String Bookdetails) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		Bookdetails bookdetails = new Bookdetails();
		JSONObject json = JSONObject.fromObject(Bookdetails);
		//��ȡ��Ϣ
		Integer book_id = json.getInt("book_id");
		String bookdetails_volume_title = json.getString("bookdetails_volume_title");
		String bookdetails_chapter_title = json.getString("bookdetails_chapter_title");
		String bookdetails_content = json.getString("bookdetails_content");
		Integer bookdetails_content_count = json.getInt("bookdetails_content_count");
		Long bookdetails_update_time = json.getLong("bookdetails_update_time");
		//������Ϣ
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
			result.setMsg("����С˵�½�"+bookdetails_chapter_title+"ʧ��");
			return result;
		}
		result.setStatus(0);
		result.setMsg("����С˵�½�"+bookdetails_chapter_title+"�ɹ�");
		return result;
	}

	//ɾ���½�
	public NoteResult<List<Bookdetails>> removeBookdetails(Integer book_id, Integer bookdetails_id) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		int length = bookdetailsDao.removeBookdetails(book_id, bookdetails_id);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("ɾ��С˵�½�"+bookdetails_id+"ʧ��");
			return result;
		}
		result.setStatus(1);
		result.setMsg("ɾ��С˵�½�"+bookdetails_id+"�ɹ�");
		return result;
	}

	//�޸��½�
	public NoteResult<List<Bookdetails>> updateBookdetails(String Bookdetails) {
		NoteResult<List<Bookdetails>> result = new NoteResult<List<Bookdetails>>();
		Bookdetails bookdetails = new Bookdetails();
		JSONObject json = JSONObject.fromObject(Bookdetails);
		if (json == null || json.isNullObject())
		{
			result.setStatus(3);
			result.setMsg("δ�ҵ�С˵����");
			return result;
		}else {
			//��ȡ��Ϣ
			Integer book_id = json.getInt("book_id");
			Integer bookdetails_id = json.getInt("bookdetails_id");
			String bookdetails_volume_title = json.getString("bookdetails_volume_title");
			String bookdetails_chapter_title = json.getString("bookdetails_chapter_title");
			String bookdetails_content = json.getString("bookdetails_content");
			Integer bookdetails_content_count = json.getInt("bookdetails_content_count");
			Long bookdetails_update_time = json.getLong("bookdetails_update_time");
			//������Ϣ
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
				result.setMsg("�޸�С˵�½�"+bookdetails_chapter_title+"ʧ��");
				return result;
			}
			result.setStatus(0);
			result.setMsg("�޸�С˵�½�"+bookdetails_chapter_title+"�ɹ�");
			return result;
		}
	}

}
