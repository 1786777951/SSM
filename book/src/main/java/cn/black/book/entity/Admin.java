package cn.black.book.entity;

import java.io.Serializable;

/**
 * ���ݿ�
 * ����Ա��
 * @author Exception
 *
 */
public class Admin implements Serializable {
	private Integer admin_id;	//����ԱID
	private String admin_account;//����Ա�˺�
	private String admin_name;	//����Ա����
	private String admin_password;//����Ա����
	private Integer admin_level;//����Ա�ȼ�
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public Integer getAdmin_level() {
		return admin_level;
	}
	public void setAdmin_level(Integer admin_level) {
		this.admin_level = admin_level;
	}
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_account=" + admin_account + ", admin_name=" + admin_name
				+ ", admin_password=" + admin_password + ", admin_level=" + admin_level + "]";
	}
	
}
