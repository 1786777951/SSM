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
	
	//��ѯ���й���Ա
	public NoteResult<List<Admin>> loadAdmin() {
		List<Admin> list = adminDao.findAdmin();
		//����������Ϣ
		NoteResult<List<Admin>> result = new NoteResult<List<Admin>>();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯ����Աʧ��!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ѯ����Ա�ɹ�!");
		result.setCount(list.size());
		//���ʼǱ�����Ϣ���浽Data��
		result.setData(list);
		return result;
	}

	@Override
	public NoteResult<Admin> findAdminID(Integer admin_id) {
		Admin admin = adminDao.findAdminID(admin_id);
		NoteResult<Admin> result = new NoteResult<Admin>();
		if(admin == null) {
			result.setStatus(1);
			result.setMsg("��¼ʧ��!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ѯ����Ա�ɹ�!");
		result.setData(admin);
		return result;
	}
	
	//��ѯ��������Ա
	@Override
	public NoteResult<Admin> findAdminAccount(String admin_account, String admin_password) {
		Admin admin = adminDao.findAdminAccount(admin_account, admin_password);
		NoteResult<Admin> result = new NoteResult<Admin>();
		if(admin == null) {
			result.setStatus(1);
			result.setMsg("��¼ʧ��!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ѯ����Ա�ɹ�!");
		result.setData(admin);
		return result;
	}



	
}
