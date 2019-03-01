package cn.black.book.service;

import java.util.List;

import cn.black.book.entity.Admin;
import cn.black.book.util.NoteResult;

public interface AdminService {
	//��ѯ���еĹ���Ա��Ϣ
	public NoteResult<List<Admin>> loadAdmin();
	
	//��ѯID����Ա
	public NoteResult<Admin> findAdminID(Integer admin_id);
	
	//��ѯ��������Ա
	public NoteResult<Admin> findAdminAccount(String admin_account,String admin_password);
}
