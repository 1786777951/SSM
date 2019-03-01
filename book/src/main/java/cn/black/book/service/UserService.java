package cn.black.book.service;

import java.util.ArrayList;
import java.util.List;

import cn.black.book.entity.User;
import cn.black.book.util.NoteResult;

public interface UserService {
	//��ѯ�����û�
	public NoteResult<List<User>> loadUserAll();
	//��ѯ���еĶ�����Ϣ
	public NoteResult<List<User>> loadUser(Integer user_type);
	//��ȡ���߷�ҳ
	public NoteResult<List<User>> loadUsers(Integer user_type,Integer page, Integer pageSize);
	
	//����ID��ѯ�û�
	public NoteResult<User> loadUserById(Integer user_id);
	
	//ɾ���û�
	public NoteResult<List> removeUsers(ArrayList<Integer> list);
	
	//�����û�
	public NoteResult<List> updateUser(Integer user_id,String user_phone,String user_name,String user_account,Integer user_sex,String user_email,Integer user_type,String user_city,String user_like,String user_Introduce);

	//�����û�ͷ��
	public NoteResult<List> updateUserimg(Integer user_id,String user_img);
	
	//�ղ���
	public NoteResult<List> collectionBooks(Integer user_id,String user_collection);
			
	//ע���û�
	public NoteResult registerUser(String user_phone,String user_name,String user_account,Integer user_sex,String user_password,String user_email,Integer user_type,String user_city,String user_like);

	//�û���¼
	public NoteResult<List> loginUserByPhone(String user_phone,String user_password);
	public NoteResult<List> loginUserByEmail(String user_email,String user_password);
	public NoteResult<List> loginUserByAccount(String user_account,String user_password);

	//��ѯ�û�����
	public NoteResult<List> findGROUPBYUser();
}
