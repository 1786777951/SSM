package cn.black.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.black.book.entity.Admin;
/**
 * 管理员
 * @author Exception
 *
 */
public interface AdminDao {
	//查询
	public List<Admin> findAdmin();
	
	//查询ID
	public Admin findAdminID(Integer admin_id);
	
	//查询账号
	public Admin findAdminAccount(@Param("admin_account")String admin_account,@Param("admin_password")String admin_password);
}
