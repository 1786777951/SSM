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
	
	//获取所有的读者信息
	public NoteResult<List<User>> loadUser(Integer user_type) {
		//读者的type属性为0
		List<User> list = userDao.findByUserId(user_type);
		//构建返回信息
		NoteResult<List<User>> result = new NoteResult<List<User>>();
		//填入返回信息
		if(list == null) {
			result.setStatus(1);
			result.setMsg("查询读者失败!");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("查询读者成功!");
		result.setCount(list.size());
		//将读者的信息保存到Data里
		result.setData(list);
		return result;
	}
	//获取分页的读者信息
	public NoteResult<List<User>> loadUsers(Integer user_type, Integer page, Integer pageSize) {
		int start = (page-1)*pageSize;
		int end = start + pageSize;
		//读者的type属性为0
		List<User> list = userDao.findByUsersId(user_type,start,end);
		//构建返回信息
		NoteResult<List<User>> result = new NoteResult<List<User>>();
		//填入返回信息
		if(list == null) {
			result.setStatus(1);
			result.setMsg("查询分页读者失败!");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("查询分页读者成功!");
		result.setCount(list.size());
		//将读者的信息保存到Data里
		result.setData(list);
		return result;
	}
	
	//删除用户
	public NoteResult<List> removeUsers(ArrayList<Integer> list) {
		int length = 0;
		for(int i=0;i<list.size();i++) {
			length += userDao.removeUser(list.get(i));
		}
		
		//构建返回信息
		NoteResult<List> result = new NoteResult<List>();
		if(length == list.size()) {
			result.setStatus(0);
			result.setMsg("删除用户成功");
			result.setCount(length);
		}else {
			result.setStatus(1);
			result.setMsg("删除用户失败");
			result.setCount(length);
		}
		return result;
	}

	//更新用户
	public NoteResult<List> updateUser(Integer user_id,String user_phone,String user_name,String user_account,Integer user_sex,String user_email,Integer user_type,String user_city,String user_like,String user_Introduce) {
		//设置User
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
		//构建返回信息
		NoteResult<List> result = new NoteResult<List>();
		int length = userDao.updateUser(user);
		if(length == 1) {
			result.setStatus(0);
			result.setMsg("更新用户成功");
			result.setCount(length);
		}else {
			result.setStatus(1);
			result.setMsg("更新用户失败");
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
		
		//构建返回信息
		NoteResult result = new NoteResult();
		int length = userDao.registerUser(user);
		if(length == 1) {
			result.setStatus(0);
			result.setMsg("注册用户成功");
			result.setCount(length);
		}else {
			result.setStatus(1);
			result.setMsg("注册用户失败");
			result.setCount(length);
		}
		return result;
	}
	
	public NoteResult<User> loadUserById(Integer user_id) {
		NoteResult<User> result = new NoteResult<User>();
		User user = userDao.loginUserById(user_id);
		if(user == null) {
			result.setStatus(1);
			result.setMsg("没有信息");
			return result;
		}
		result.setStatus(0);
		result.setMsg("查询登录信息成功");
		result.setData(user);
		return result;
	}
	
	//用户登录
	public NoteResult<List> loginUserByPhone(String user_phone,String user_password) {
		NoteResult<List> result = new NoteResult<List>();
		User user = new User();
		user.setUser_phone(user_phone);
		user.setUser_password(user_password);
		List<User> list = userDao.loginUserByPhone(user);
		if(list.size() != 1) {
			result.setStatus(1);
			result.setMsg("手机号输入错误");
			return result;
		}
		result.setStatus(0);
		result.setMsg("登录成功!");
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
			result.setMsg("邮箱输入错误");
			return result;
		}
		result.setStatus(0);
		result.setMsg("登录成功!");
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
			result.setMsg("账号输入错误");
			return result;
		}
		result.setStatus(0);
		result.setMsg("登录成功!");
		result.setData(list);
		return result;
	}
	
	//收藏书
	@Override
	public NoteResult<List> collectionBooks(Integer user_id, String user_collection) {
		NoteResult<List>  result = new NoteResult<List>();
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_collection(user_collection);
		int length = userDao.collectionBooks(user);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("失败");
			return result;
		}
		result.setStatus(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public NoteResult<List> updateUserimg(Integer user_id, String user_img) {
		NoteResult<List>  result = new NoteResult<List>();
		int length = userDao.updateUserimg(user_id, user_img);
		if(length != 1) {
			result.setStatus(1);
			result.setMsg("更换头像失败!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("更换头像成功!");
		return result;
	}
	//查询所有用户
	@Override
	public NoteResult<List<User>> loadUserAll() {
		NoteResult<List<User>>  result = new NoteResult<List<User>>();
		List<User> list = userDao.findUser();
		if(list == null) {
			result.setStatus(1);
			result.setMsg("获取全部用户失败!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("获取全部用户成功!");
		result.setData(list);
		return result;
	}
	@Override
	public NoteResult<List> findGROUPBYUser() {
		NoteResult<List> result = new NoteResult<List>();
		List<Map<Integer,Integer>> map = userDao.findGROUPBYUser();
		if(map == null) {
			result.setStatus(1);
			result.setMsg("查询失败!");
		}
		result.setStatus(0);
		result.setMsg("查询成功!");
		result.setData(map);
		return result;
	}
	
	

	

}
