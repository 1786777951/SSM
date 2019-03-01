package cn.black.book.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.black.book.dao.AdminDao;
import cn.black.book.entity.Admin;
import cn.black.book.util.NoteResult;
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Resource
	AdminDao adminDao;
	
	//查询所有管理员
	public NoteResult<List<Admin>> loadAdmin() {
		List<Admin> list = adminDao.findAdmin();
		//构建返回信息
		NoteResult<List<Admin>> result = new NoteResult<List<Admin>>();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("查询管理员失败!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询管理员成功!");
		result.setCount(list.size());
		//将笔记本的信息保存到Data里
		result.setData(list);
		return result;
	}

	@Override
	public NoteResult<Admin> findAdminID(Integer admin_id) {
		Admin admin = adminDao.findAdminID(admin_id);
		NoteResult<Admin> result = new NoteResult<Admin>();
		if(admin == null) {
			result.setStatus(1);
			result.setMsg("登录失败!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询管理员成功!");
		result.setData(admin);
		return result;
	}
	
	//查询单个管理员
	@Override
	public NoteResult<Admin> findAdminAccount(String admin_account, String admin_password) {
		Admin admin = adminDao.findAdminAccount(admin_account, admin_password);
		NoteResult<Admin> result = new NoteResult<Admin>();
		if(admin == null) {
			result.setStatus(1);
			result.setMsg("登录失败!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询管理员成功!");
		result.setData(admin);
		return result;
	}



	
}
