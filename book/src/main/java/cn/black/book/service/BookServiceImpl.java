package cn.black.book.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.black.book.dao.BookDao;
import cn.black.book.entity.Book;
import cn.black.book.util.NoteResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("bookService")
public class BookServiceImpl implements BookService{

	@Autowired
	@Qualifier("bookDao")
	BookDao bookDao;
	
	//��ȡ������
	public NoteResult<List<Book>> loadBook() {
		List<Book> list = bookDao.findBooks();
		NoteResult<List<Book>>  result = new NoteResult<List<Book>>();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�!");
		result.setData(list);
		result.setCount(list.size());
		return result;
	}

	//��ȡһ����
	public NoteResult<List<Book>> loadBookById(Integer book_id) {
		List<Book> list = bookDao.findBookById(book_id);
		NoteResult<List<Book>>  result = new NoteResult<List<Book>>();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("δ�ҵ���¼");
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�!");
		result.setData(list);
		return result;
	}
	
	//��ȡ�������ҳ
	public NoteResult<List<Book>> loadBooks(Integer page, Integer pageSize) {
		int start = (page-1)*pageSize;
		int end = start + pageSize;
		List<Book> list = bookDao.findBook(start, end);
		NoteResult<List<Book>>  result = new NoteResult<List<Book>>();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�!");
		result.setData(list);
		result.setCount(list.size());
		return result;
	}

	//���������Ͳ�ѯ
	public NoteResult<List<Book>> loadBookByType(String book_type, Integer page, Integer limit) {
		int start = (page-1)*limit;
		List<Book> list = bookDao.findBookByType(book_type, start, limit);
		NoteResult<List<Book>>  result = new NoteResult<List<Book>>();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�!");
		result.setData(list);
		result.setCount(list.size());
		return result;
	}

	//����������ѯ
	public NoteResult<List<Book>> loadBookByTitle(String book_title, Integer page, Integer limit,Integer wordCount,Integer wordCounts,Integer record) {
		int start = (page-1)*limit;
		List<Book> list = bookDao.findBookByTitle(book_title, start, limit,wordCount,wordCounts,record);
		NoteResult<List<Book>>  result = new NoteResult<List<Book>>();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�!");
		result.setData(list);
		result.setCount(list.size());
		return result;
	}
	@Override
	public NoteResult<List<Book>> loadBookByTitles(String book_title) {
		List<Book> list = bookDao.findBookByTitles(book_title);
		NoteResult<List<Book>>  result = new NoteResult<List<Book>>();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�!");
		result.setData(list);
		result.setCount(list.size());
		return result;
	}

	
	//������
	public NoteResult<List<Book>> addBook(String books) {
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		Book book = new Book();
		JSONObject json = JSONObject.fromObject(books);
		//��ȡ��Ϣ
		String book_title = json.getString("book_title");
		String book_author = json.getString("book_author");
		String book_explain = json.getString("book_explain");
		String book_img = json.getString("book_img");
		String book_type = json.getString("book_type");
		Integer book_count = json.getInt("book_count");
		Long book_update_time = json.getLong("book_update_time");
		//������Ϣ
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		book.setBook_title(book_title);
		book.setBook_author(book_author);
		book.setBook_explain(book_explain);
		book.setBook_img(book_img);
		book.setBook_type(book_type);
		book.setBook_count(book_count);
		book.setBook_update_time(simpleDateFormat.format(new Date(book_update_time)));
		
		System.out.println(book);
		int length = bookDao.addBook(book);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("����С˵"+book_title+"ʧ��");
			return result;
		}
		result.setStatus(0);
		result.setMsg("����С˵"+book_title+"�ɹ�");
		return result;
	}
	//ɾ����

	public NoteResult<List<Book>> removeBook(Integer book_id) {
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		int length = bookDao.removeBook(book_id);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("ɾ��С˵"+book_id+"ʧ��");
			return result;
		}
		result.setStatus(1);
		result.setMsg("ɾ��С˵"+book_id+"�ɹ�");
		return result;
	}
	//�޸���
	public NoteResult<List<Book>> updateBook(String books) {
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		Book book = new Book();
		JSONObject json = JSONObject.fromObject(books);
		if (json == null || json.isNullObject())
		{
			result.setStatus(3);
			result.setMsg("δ�ҵ�����");
			return result;
		}else {
			//��ȡ��Ϣ
			Integer book_id =(Integer)json.get("book_id");
			String book_title = json.getString("book_title");
			String book_author = json.getString("book_author");
			String book_explain = json.getString("book_explain").replace(" ", "<br>");;
			String book_img = json.getString("book_img");
			String book_type = json.getString("book_type");
			Integer book_count = (Integer)json.get("book_count");
			Long book_update_time = json.getLong("book_update_time");
			//������Ϣ
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			book.setBook_id(book_id);
			book.setBook_title(book_title);
			book.setBook_author(book_author);
			book.setBook_explain(book_explain);
			book.setBook_img(book_img);
			book.setBook_type(book_type);
			book.setBook_count(book_count);
			book.setBook_update_time(simpleDateFormat.format(new Date(book_update_time)));
			
			int length = bookDao.updateBook(book);
			if(length != 1) {
				result.setStatus(1);
				result.setMsg("�޸�С˵"+book_title+"ʧ��");
				return result;
			}
			result.setStatus(0);
			result.setMsg("�޸�С˵"+book_title+"�ɹ�");
			return result;
		}
	}

	//��ѯ��ܵ���
	@Override
	public NoteResult<List<Book>> loadBookByIdList(ArrayList<Integer> list) {
		int length = 0;
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		//��
		List<Book> booklist = new ArrayList<Book>();
		//����ǰ�˻�ȡ�����ȡ�������в�ѯ
		for(int i=0;i<list.size();i++) {
			Integer book_id = list.get(i);
			
			booklist.add(bookDao.findBookByIds(book_id));
			length++;
		}
		if(length != list.size()) {
			result.setStatus(1);
			result.setMsg("��ѯ���ʧ��!");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("��ѯ��ܳɹ�!");
		result.setData(booklist);
		return result;
	}

	//��ȡ���������
	@Override
	public NoteResult<List<Book>> updateBookDay() {
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		List<Book> list = bookDao.updateDay();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯ���ո���ʧ��!");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("��ѯ���ո��³ɹ�!");
		result.setData(list);
		return result;
	}

	//���������
	@Override
	public NoteResult<List> findGROUPBY() {
		NoteResult<List> result = new NoteResult<List>();
		List<Map<String,Integer>> map = bookDao.findGROUPBY();
		if(map == null) {
			result.setStatus(1);
			result.setMsg("��ѯʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�!");
		result.setData(map);
		return result;
	}

	

	

}
