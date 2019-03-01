package cn.black.book.service;

import java.util.List;

import cn.black.book.entity.Admin;
import cn.black.book.util.NoteResult;

public interface AdminService {
	//查询所有的管理员信息
	public NoteResult<List<Admin>> loadAdmin();
	
	//查询ID管理员
	public NoteResult<Admin> findAdminID(Integer admin_id);
	
	//查询单个管理员
	public NoteResult<Admin> findAdminAccount(String admin_account,String admin_password);
}
