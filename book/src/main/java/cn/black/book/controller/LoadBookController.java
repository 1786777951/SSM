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
 * 书
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
		//查询所有的书
		NoteResult<List<Book>> results = bookService.loadBook();
        //返回给前端
        return results;
	}
	
	//查询今日更新的书
	@ResponseBody
	@RequestMapping("/loadBookDay")
	public NoteResult<List<Book>> loadBookDay(){
		NoteResult<List<Book>> results = bookService.updateBookDay();
        //返回给前端
        return results;
	}
	
	@ResponseBody
	@RequestMapping("/loadBookById")
	public NoteResult<List<Book>> backBookById(Integer book_id) throws IOException{
		//查询ID书
		NoteResult<List<Book>> result = bookService.loadBookById(book_id);
        //返回给前端
        return result;
	}
	
	//查书架
	@ResponseBody
	@RequestMapping("/loadBookByIds")
	public NoteResult<List<Book>> backBookByIds(@RequestBody ArrayList<Integer> list){
		NoteResult<List<Book>> result = bookService.loadBookByIdList(list);
        //返回给前端
        return result;
	}
	
	//查询分页的书
	@ResponseBody
	@RequestMapping("/loadBooks")
	public NoteResult<List<Book>> backBooks(Integer page, Integer limit) throws IOException{
		//查询所有的书
		NoteResult<List<Book>> results = bookService.loadBook();
		//查询分页的书
		NoteResult<List<Book>> result = bookService.loadBooks(page, limit);
		NoteResult<List<Book>> table = new NoteResult<List<Book>>();
		if(results.getStatus() == 0 && result.getStatus() == 0) {
			//这是layui要求返回的json数据格式
			table.setStatus(0);
			table.setMsg("成功");
	        //将全部数据的条数作为count传给前台（一共多少条）
			table.setCount(results.getCount());
	        //将分页后的数据返回（每页要显示的数据）
	        table.setData(result.getData());
		}
        //返回给前端
        return table;
	}
	
	//类型查询分页的书
	@ResponseBody
	@RequestMapping("/loadBooksByType")
	public NoteResult<List<Book>> loadBooksByType(String book_type,Integer page, Integer limit) throws IOException{
		//查询所有的书
		NoteResult<List<Book>> results = bookService.loadBook();
		//类型查询分页的书
		NoteResult<List<Book>> result = bookService.loadBookByType(book_type, page, limit);
		NoteResult<List<Book>> table = new NoteResult<List<Book>>();
		if(results.getStatus() == 0 && result.getStatus() == 0) {
			//这是layui要求返回的json数据格式
			table.setStatus(0);
			table.setMsg("成功");
	        //将全部数据的条数作为count传给前台（一共多少条）
			table.setCount(results.getCount());
	        //将分页后的数据返回（每页要显示的数据）
	        table.setData(result.getData());
		}
        //返回给前端
        return table;
	}
	
	
	
	//书名查询分页的书
	@ResponseBody
	@RequestMapping("/loadBooksByTitle")
	public NoteResult<List<Book>> loadBooksByTitle(String book_title,Integer page, Integer limit,Integer wordCount,Integer wordCounts,Integer record){
		//查询
		NoteResult<List<Book>> results = bookService.loadBookByTitles(book_title);
		NoteResult<List<Book>> result = bookService.loadBookByTitle(book_title, page, limit,wordCount,wordCounts,record);
		result.setCount(results.getCount());
        //返回给前端
        return result;
	}
	
	//增加书
	@ResponseBody
	@RequestMapping("/addBook")
	public NoteResult<List<Book>> addBook(String books){
		NoteResult<List<Book>> result = bookService.addBook(books);
		return result;
	}
	
	//修改书
	@ResponseBody
	@RequestMapping("/updateBook")
	public NoteResult<List<Book>> updateBook(String books){
		NoteResult<List<Book>> result = bookService.updateBook(books);
		return result;
	}
	
	//查询书分类
	@ResponseBody
	@RequestMapping("/findGROUPBY")
	public NoteResult<List> findGROUPBY(){
		NoteResult<List> result = bookService.findGROUPBY();
		return result;
	}
}
