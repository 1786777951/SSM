package cn.black.book.entity;

import java.io.Serializable;

/**
 * ��Ѷ�
 * @author Exception
 *
 */
public class Information implements Serializable{
	private Integer information_id;//��ѶID
	private Integer information_type;//����
	private String information_title;//����
	private String information_content;//����
	public Integer getInformation_id() {
		return information_id;
	}
	public void setInformation_id(Integer information_id) {
		this.information_id = information_id;
	}
	public Integer getInformation_type() {
		return information_type;
	}
	public void setInformation_type(Integer information_type) {
		this.information_type = information_type;
	}
	public String getInformation_title() {
		return information_title;
	}
	public void setInformation_title(String information_title) {
		this.information_title = information_title;
	}
	public String getInformation_content() {
		return information_content;
	}
	public void setInformation_content(String information_content) {
		this.information_content = information_content;
	}
	@Override
	public String toString() {
		return "information [information_id=" + information_id + ", information_type=" + information_type
				+ ", information_title=" + information_title + ", information_content=" + information_content + "]";
	}
	
	
}
