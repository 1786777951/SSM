package cn.black.book.util;
//自定义异常
public class NoteException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public NoteException() {
		super();
	}
	public NoteException(String message) {
		super(message);
	}
	
}