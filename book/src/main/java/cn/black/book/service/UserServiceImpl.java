package cn.black.book.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.black.book.dao.UserDao;
import cn.black.book.entity.Bookdetails;
import cn.black.book.entity.User;
import cn.black.book.util.NoteResult;
import net.sf.json.JSONObject;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	UserDao userDao;
	
	//��ȡ���еĶ�����Ϣ
	public NoteResult<List<User>> loadUser(Integer user_type) {
		//���ߵ�type����Ϊ0
		List<User> list = userDao.findByUserId(user_type);
		//����������Ϣ
		NoteResult<List<User>> result = new NoteResult<List<User>>();
		//���뷵����Ϣ
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯ����ʧ��!");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("��ѯ���߳ɹ�!");
		result.setCount(list.size());
		//�����ߵ���Ϣ���浽Data��
		result.setData(list);
		return result;
	}
	//��ȡ��ҳ�Ķ�����Ϣ
	public NoteResult<List<User>> loadUsers(Integer user_type, Integer page, Integer pageSize) {
		int start = (page-1)*pageSize;
		int end = start + pageSize;
		//���ߵ�type����Ϊ0
		List<User> list = userDao.findByUsersId(user_type,start,end);
		//����������Ϣ
		NoteResult<List<User>> result = new NoteResult<List<User>>();
		//���뷵����Ϣ
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ѯ��ҳ����ʧ��!");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("��ѯ��ҳ���߳ɹ�!");
		result.setCount(list.size());
		//�����ߵ���Ϣ���浽Data��
		result.setData(list);
		return result;
	}
	
	//ɾ���û�
	public NoteResult<List> removeUsers(ArrayList<Integer> list) {
		int length = 0;
		for(int i=0;i<list.size();i++) {
			length += userDao.removeUser(list.get(i));
		}
		
		//����������Ϣ
		NoteResult<List> result = new NoteResult<List>();
		if(length == list.size()) {
			result.setStatus(0);
			result.setMsg("ɾ���û��ɹ�");
			result.setCount(length);
		}else {
			result.setStatus(1);
			result.setMsg("ɾ���û�ʧ��");
			result.setCount(length);
		}
		return result;
	}

	//�����û�
	public NoteResult<List> updateUser(Integer user_id,String user_phone,String user_name,String user_account,Integer user_sex,String user_email,Integer user_type,String user_city,String user_like,String user_Introduce) {
		//����User
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_phone(user_phone);
		user.setUser_name(user_name);
		user.setUser_account(user_account);
		user.setUser_sex(user_sex);
		user.setUser_email(user_email);
		user.setUser_type(user_type);
		user.setUser_Introduce(user_Introduce);
		user.setUser_city(user_city);
		user.setUser_like(user_like);
		//����������Ϣ
		NoteResult<List> result = new NoteResult<List>();
		int length = userDao.updateUser(user);
		if(length == 1) {
			result.setStatus(0);
			result.setMsg("�����û��ɹ�");
			result.setCount(length);
		}else {
			result.setStatus(1);
			result.setMsg("�����û�ʧ��");
			result.setCount(length);
		}
		return result;
	}
	public NoteResult registerUser(String user_phone,String user_name,String user_account,Integer user_sex,String user_password,String user_email,Integer user_type,String user_city,String user_like) {
		User user = new User();
		user.setUser_phone(user_phone);
		user.setUser_name(user_name);
		user.setUser_account(user_account);
		user.setUser_sex(user_sex);
		user.setUser_password(user_password);
		user.setUser_email(user_email);
		user.setUser_type(user_type);
		user.setUser_city(user_city);
		user.setUser_like(user_like);
		
		//����������Ϣ
		NoteResult result = new NoteResult();
		int length = userDao.registerUser(user);
		if(length == 1) {
			result.setStatus(0);
			result.setMsg("ע���û��ɹ�");
			result.setCount(length);
		}else {
			result.setStatus(1);
			result.setMsg("ע���û�ʧ��");
			result.setCount(length);
		}
		return result;
	}
	
	public NoteResult<User> loadUserById(Integer user_id) {
		NoteResult<User> result = new NoteResult<User>();
		User user = userDao.loginUserById(user_id);
		if(user == null) {
			result.setStatus(1);
			result.setMsg("û����Ϣ");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ѯ��¼��Ϣ�ɹ�");
		result.setData(user);
		return result;
	}
	
	//�û���¼
	public NoteResult<List> loginUserByPhone(String user_phone,String user_password) {
		NoteResult<List> result = new NoteResult<List>();
		User user = new User();
		user.setUser_phone(user_phone);
		user.setUser_password(user_password);
		List<User> list = userDao.loginUserByPhone(user);
		if(list.size() != 1) {
			result.setStatus(1);
			result.setMsg("�ֻ����������");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��¼�ɹ�!");
		result.setData(list);
		return result;
	}
	public NoteResult<List> loginUserByEmail(String user_email,String user_password) {
		NoteResult<List> result = new NoteResult<List>();
		User user = new User();
		user.setUser_email(user_email);
		user.setUser_password(user_password);
		List<User> list = userDao.loginUserByEmail(user);
		if(list.size() != 1) {
			result.setStatus(1);
			result.setMsg("�����������");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��¼�ɹ�!");
		result.setData(list);
		return result;
	}
	public NoteResult<List> loginUserByAccount(String user_account,String user_password) {
		NoteResult<List> result = new NoteResult<List>();
		User user = new User();
		user.setUser_account(user_account);
		user.setUser_password(user_password);
		List<User> list = userDao.loginUserByAccount(user);
		if(list.size() != 1) {
			result.setStatus(1);
			result.setMsg("�˺��������");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��¼�ɹ�!");
		result.setData(list);
		return result;
	}
	
	//�ղ���
	@Override
	public NoteResult<List> collectionBooks(Integer user_id, String user_collection) {
		NoteResult<List>  result = new NoteResult<List>();
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_collection(user_collection);
		int length = userDao.collectionBooks(user);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("ʧ��");
			return result;
		}
		result.setStatus(0);
		result.setMsg("�ɹ�");
		return result;
	}
	@Override
	public NoteResult<List> updateUserimg(Integer user_id, String user_img) {
		NoteResult<List>  result = new NoteResult<List>();
		int length = userDao.updateUserimg(user_id, user_img);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("����ͷ��ʧ��!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("����ͷ��ɹ�!");
		return result;
	}
	//��ѯ�����û�
	@Override
	public NoteResult<List<User>> loadUserAll() {
		NoteResult<List<User>>  result = new NoteResult<List<User>>();
		List<User> list = userDao.findUser();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("��ȡȫ���û�ʧ��!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ȡȫ���û��ɹ�!");
		result.setData(list);
		return result;
	}
	@Override
	public NoteResult<List> findGROUPBYUser() {
		NoteResult<List> result = new NoteResult<List>();
		List<Map<Integer,Integer>> map = userDao.findGROUPBYUser();
		if(map == null) {
			result.setStatus(1);
			result.setMsg("��ѯʧ��!");
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�!");
		result.setData(map);
		return result;
	}
	
	

	

}
