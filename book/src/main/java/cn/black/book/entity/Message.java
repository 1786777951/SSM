package cn.black.book.entity;

import java.io.Serializable;

/**
 * ����
 * @author Exception
 *
 */
public class Message implements Serializable{
	private Integer message_id;//����ID
	private Integer book_id;//�鱾ID
	private Integer user_id;//�û�ID
	private String message_content;//��������
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	@Override
	public String toString() {
		return "message [message_id=" + message_id + ", book_id=" + book_id + ", user_id=" + user_id
				+ ", message_content=" + message_content + "]";
	}
	
	
}
