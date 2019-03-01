package cn.black.book.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.black.book.entity.Bookdetails;
import cn.black.book.service.BookdetailsService;
import cn.black.book.util.NoteResult;

@Controller
@RequestMapping("Bookdetails")
public class LoadBookdetailsController {
	@Resource
	public BookdetailsService bookdetailsService;
	
	//���������е��½�
	@ResponseBody
	@RequestMapping("/loadBookdetails")
	public NoteResult<List<Bookdetails>> backBookdetails(Integer book_id) throws IOException{
		NoteResult<List<Bookdetails>> result = bookdetailsService.loadBookdetails(book_id);
		return result;
	}
	
	//�����½�����
	@ResponseBody
	@RequestMapping("/loadBookdetails_content")
	public NoteResult<List<Bookdetails>> Bookdetails_content(Integer book_id, Integer bookdetails_id) throws IOException{
		NoteResult<List<Bookdetails>> result = bookdetailsService.findbookdetails_content(book_id, bookdetails_id);
		return result;
	}
	
	//�����½�
	@ResponseBody
	@RequestMapping("/addBookdetails")
	public NoteResult<List<Bookdetails>> addBookdetails(String Bookdetails) throws IOException{
		NoteResult<List<Bookdetails>> result = bookdetailsService.addBookdetails(Bookdetails);
		return result;
	}
	
	//ɾ���½�
	@ResponseBody
	@RequestMapping("/removeBookdetails")
	public NoteResult<List<Bookdetails>> removeBookdetails(Integer book_id, Integer bookdetails_id) throws IOException{
		NoteResult<List<Bookdetails>> result = bookdetailsService.removeBookdetails(book_id, bookdetails_id);
		return result;
	}
	
	//�޸��½�
	@ResponseBody
	@RequestMapping("/updateBookdetails")
	public NoteResult<List<Bookdetails>> updateBookdetails(String Bookdetails) throws IOException{
		NoteResult<List<Bookdetails>> result = bookdetailsService.updateBookdetails(Bookdetails);
		return result;
	}
	
}
