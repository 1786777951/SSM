package cn.black.book.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.black.book.entity.Book;
import cn.black.book.entity.User;
import cn.black.book.service.BookService;
import cn.black.book.util.NoteResult;
/**
 * ��
 * @author Exception
 *
 */
@Controller
@RequestMapping("Book")
public class LoadBookController {
	@Resource
	public BookService bookService;
	
	@ResponseBody
	@RequestMapping("/loadBook")
	public NoteResult<List<Book>> backBook(){
		//��ѯ���е���
		NoteResult<List<Book>> results = bookService.loadBook();
        //���ظ�ǰ��
        return results;
	}
	
	//��ѯ���ո��µ���
	@ResponseBody
	@RequestMapping("/loadBookDay")
	public NoteResult<List<Book>> loadBookDay(){
		NoteResult<List<Book>> results = bookService.updateBookDay();
        //���ظ�ǰ��
        return results;
	}
	
	@ResponseBody
	@RequestMapping("/loadBookById")
	public NoteResult<List<Book>> backBookById(Integer book_id) throws IOException{
		//��ѯID��
		NoteResult<List<Book>> result = bookService.loadBookById(book_id);
        //���ظ�ǰ��
        return result;
	}
	
	//�����
	@ResponseBody
	@RequestMapping("/loadBookByIds")
	public NoteResult<List<Book>> backBookByIds(@RequestBody ArrayList<Integer> list){
		NoteResult<List<Book>> result = bookService.loadBookByIdList(list);
        //���ظ�ǰ��
        return result;
	}
	
	//��ѯ��ҳ����
	@ResponseBody
	@RequestMapping("/loadBooks")
	public NoteResult<List<Book>> backBooks(Integer page, Integer limit) throws IOException{
		//��ѯ���е���
		NoteResult<List<Book>> results = bookService.loadBook();
		//��ѯ��ҳ����
		NoteResult<List<Book>> result = bookService.loadBooks(page, limit);
		NoteResult<List<Book>> table = new NoteResult<List<Book>>();
		if(results.getStatus() == 0 && result.getStatus() == 0) {
			//����layuiҪ�󷵻ص�json���ݸ�ʽ
			table.setStatus(0);
			table.setMsg("�ɹ�");
	        //��ȫ�����ݵ�������Ϊcount����ǰ̨��һ����������
			table.setCount(results.getCount());
	        //����ҳ������ݷ��أ�ÿҳҪ��ʾ�����ݣ�
	        table.setData(result.getData());
		}
        //���ظ�ǰ��
        return table;
	}
	
	//���Ͳ�ѯ��ҳ����
	@ResponseBody
	@RequestMapping("/loadBooksByType")
	public NoteResult<List<Book>> loadBooksByType(String book_type,Integer page, Integer limit) throws IOException{
		//��ѯ���е���
		NoteResult<List<Book>> results = bookService.loadBook();
		//���Ͳ�ѯ��ҳ����
		NoteResult<List<Book>> result = bookService.loadBookByType(book_type, page, limit);
		NoteResult<List<Book>> table = new NoteResult<List<Book>>();
		if(results.getStatus() == 0 && result.getStatus() == 0) {
			//����layuiҪ�󷵻ص�json���ݸ�ʽ
			table.setStatus(0);
			table.setMsg("�ɹ�");
	        //��ȫ�����ݵ�������Ϊcount����ǰ̨��һ����������
			table.setCount(results.getCount());
	        //����ҳ������ݷ��أ�ÿҳҪ��ʾ�����ݣ�
	        table.setData(result.getData());
		}
        //���ظ�ǰ��
        return table;
	}
	
	
	
	//������ѯ��ҳ����
	@ResponseBody
	@RequestMapping("/loadBooksByTitle")
	public NoteResult<List<Book>> loadBooksByTitle(String book_title,Integer page, Integer limit,Integer wordCount,Integer wordCounts,Integer record){
		//��ѯ
		NoteResult<List<Book>> results = bookService.loadBookByTitles(book_title);
		NoteResult<List<Book>> result = bookService.loadBookByTitle(book_title, page, limit,wordCount,wordCounts,record);
		result.setCount(results.getCount());
        //���ظ�ǰ��
        return result;
	}
	
	//������
	@ResponseBody
	@RequestMapping("/addBook")
	public NoteResult<List<Book>> addBook(String books){
		NoteResult<List<Book>> result = bookService.addBook(books);
		return result;
	}
	
	//�޸���
	@ResponseBody
	@RequestMapping("/updateBook")
	public NoteResult<List<Book>> updateBook(String books){
		NoteResult<List<Book>> result = bookService.updateBook(books);
		return result;
	}
	
	//��ѯ�����
	@ResponseBody
	@RequestMapping("/findGROUPBY")
	public NoteResult<List> findGROUPBY(){
		NoteResult<List> result = bookService.findGROUPBY();
		return result;
	}
}
