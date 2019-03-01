package cn.black.book.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * �û���
 * @author Exception
 *
 */
public class User implements Serializable{
	private Integer user_id;//�û�ID
	private String user_account;//�û��˺�
	private String user_phone;//�ֻ���
	private String user_name;//�û���
	private Integer user_sex;//�û��Ա�
	private String user_like;//ϲ���鼮����
	private String user_password;//�û�����
	private String user_email;//�û�����
	private Integer user_type;//�û����
	private String user_introduce;//���ҽ���
	private String user_img;//�û�ͷ��
	private Date user_create_time;//����ʱ��
	private String user_city;//�û����ڵĳ���
	private String user_collection;//�û��ղ���
	
	public String getUser_like() {
		return user_like;
	}
	public void setUser_like(String user_like) {
		this.user_like = user_like;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_introduce() {
		return user_introduce;
	}
	public void setUser_introduce(String user_introduce) {
		this.user_introduce = user_introduce;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	public String getUser_Introduce() {
		return user_introduce;
	}
	public void setUser_Introduce(String user_Introduce) {
		this.user_introduce = user_Introduce;
	}
	public String getUser_img_id() {
		return user_img;
	}
	public void setUser_img_id(String user_img_id) {
		this.user_img = user_img;
	}
	public Date getUser_create_time() {
		return user_create_time;
	}
	public void setUser_create_time(Date user_create_time) {
		this.user_create_time = user_create_time;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public String getUser_collection() {
		return user_collection;
	}
	public void setUser_collection(String user_collection) {
		this.user_collection = user_collection;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_account=" + user_account + ", user_phone=" + user_phone
				+ ", user_name=" + user_name + ", user_sex=" + user_sex + ", user_like=" + user_like
				+ ", user_password=" + user_password + ", user_email=" + user_email + ", user_type=" + user_type
				+ ", user_introduce=" + user_introduce + ", user_img=" + user_img + ", user_create_time="
				+ user_create_time + ", user_city=" + user_city + ", user_collection=" + user_collection + "]";
	}
	
	
	
	
	
	
}
