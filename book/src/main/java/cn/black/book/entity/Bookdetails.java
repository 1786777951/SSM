package cn.black.book.entity;

import java.io.Serializable;

/**
 * 书籍内容
 * @author Exception
 *
 */
public class Bookdetails implements Serializable{
	private Integer book_id;//书ID
	private Integer bookdetails_id;//章节ID
	private String bookdetails_volume_title;//卷标题
	private String bookdetails_chapter_title;//章标题
	private String bookdetails_content;//章内容
	private Integer bookdetails_content_count;//文章字数
	private String bookdetails_update_time;//更新时间
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public Integer getBookdetails_id() {
		return bookdetails_id;
	}
	public void setBookdetails_id(Integer bookdetails_id) {
		this.bookdetails_id = bookdetails_id;
	}
	public String getBookdetails_volume_title() {
		return bookdetails_volume_title;
	}
	public void setBookdetails_volume_title(String bookdetails_volume_title) {
		this.bookdetails_volume_title = bookdetails_volume_title;
	}
	public String getBookdetails_chapter_title() {
		return bookdetails_chapter_title;
	}
	public void setBookdetails_chapter_title(String bookdetails_chapter_title) {
		this.bookdetails_chapter_title = bookdetails_chapter_title;
	}
	public String getBookdetails_content() {
		return bookdetails_content;
	}
	public void setBookdetails_content(String bookdetails_content) {
		this.bookdetails_content = bookdetails_content;
	}
	public Integer getBookdetails_content_count() {
		return bookdetails_content_count;
	}
	public void setBookdetails_content_count(Integer bookdetails_content_count) {
		this.bookdetails_content_count = bookdetails_content_count;
	}
	public String getBookdetails_update_time() {
		return bookdetails_update_time;
	}
	public void setBookdetails_update_time(String bookdetails_update_time) {
		this.bookdetails_update_time = bookdetails_update_time;
	}
	@Override
	public String toString() {
		return "Bookdetails [book_id=" + book_id + ", bookdetails_id=" + bookdetails_id + ", bookdetails_volume_title="
				+ bookdetails_volume_title + ", bookdetails_chapter_title=" + bookdetails_chapter_title
				+ ", bookdetails_content=" + bookdetails_content + ", bookdetails_content_count="
				+ bookdetails_content_count + ", bookdetails_update_time=" + bookdetails_update_time + "]";
	}
	
	
}
