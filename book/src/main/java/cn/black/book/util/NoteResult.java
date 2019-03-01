package cn.black.book.util;

import java.io.Serializable;
/*
 * 工具类，实现提示功能
 */
public class NoteResult<T> implements Serializable{
	private int status;
	private String msg;
	private T data;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data + ", count=" + count + "]";
	}

	
}
