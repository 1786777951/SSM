package cn.black.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.black.book.entity.Admin;
/**
 * ����Ա
 * @author Exception
 *
 */
public interface AdminDao {
	//��ѯ
	public List<Admin> findAdmin();
	
	//��ѯID
	public Admin findAdminID(Integer admin_id);
	
	//��ѯ�˺�
	public Admin findAdminAccount(@Param("admin_account")String admin_account,@Param("admin_password")String admin_password);
}
