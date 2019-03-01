package cn.black.book.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 书籍
 * @author Exception
 *
 */
public class Book implements Serializable{
	private Integer book_id;//书本ID
	private String book_title;//书名
	private String book_img;//书本封面
	private String book_author;//作者
	private String book_explain;//书本简介
	private String book_type;//书本类型
	private Integer book_count;//字数
	private Timestamp book_create_time;//创建时间
	private String book_update_time;//更新时间
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getBook_img() {
		return book_img;
	}
	public void setBook_img(String book_img) {
		this.book_img = book_img;
	}
	
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getBook_explain() {
		return book_explain;
	}
	public void setBook_explain(String book_explain) {
		this.book_explain = book_explain;
	}
	public String getBook_type() {
		return book_type;
	}
	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}
	
	public Integer getBook_count() {
		return book_count;
	}
	public void setBook_count(Integer book_count) {
		this.book_count = book_count;
	}
	public Timestamp getBook_create_time() {
		return book_create_time;
	}
	public void setBook_create_time(Timestamp book_create_time) {
		this.book_create_time = book_create_time;
	}
	public String getBook_update_time() {
		return book_update_time;
	}
	public void setBook_update_time(String book_update_time) {
		this.book_update_time = book_update_time;
	}
	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", book_title=" + book_title + ", book_img=" + book_img + ", book_author="
				+ book_author + ", book_explain=" + book_explain + ", book_type=" + book_type + ", book_count="
				+ book_count + ", book_create_time=" + book_create_time + ", book_update_time=" + book_update_time
				+ "]";
	}	

}
