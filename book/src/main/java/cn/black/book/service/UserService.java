package cn.black.book.service;

import java.util.ArrayList;
import java.util.List;

import cn.black.book.entity.User;
import cn.black.book.util.NoteResult;

public interface UserService {
	//查询所有用户
	public NoteResult<List<User>> loadUserAll();
	//查询所有的读者信息
	public NoteResult<List<User>> loadUser(Integer user_type);
	//获取读者分页
	public NoteResult<List<User>> loadUsers(Integer user_type,Integer page, Integer pageSize);
	
	//根据ID查询用户
	public NoteResult<User> loadUserById(Integer user_id);
	
	//删除用户
	public NoteResult<List> removeUsers(ArrayList<Integer> list);
	
	//更新用户
	public NoteResult<List> updateUser(Integer user_id,String user_phone,String user_name,String user_account,Integer user_sex,String user_email,Integer user_type,String user_city,String user_like,String user_Introduce);

	//更新用户头像
	public NoteResult<List> updateUserimg(Integer user_id,String user_img);
	
	//收藏书
	public NoteResult<List> collectionBooks(Integer user_id,String user_collection);
			
	//注册用户
	public NoteResult registerUser(String user_phone,String user_name,String user_account,Integer user_sex,String user_password,String user_email,Integer user_type,String user_city,String user_like);

	//用户登录
	public NoteResult<List> loginUserByPhone(String user_phone,String user_password);
	public NoteResult<List> loginUserByEmail(String user_email,String user_password);
	public NoteResult<List> loginUserByAccount(String user_account,String user_password);

	//查询用户分类
	public NoteResult<List> findGROUPBYUser();
}
