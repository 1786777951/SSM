package cn.black.book.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.black.book.entity.User;


//用户/作者
public interface UserDao {
	//查询所有用户
	public List<User> findUser();
	
	//查询用户
	public List<User> findByUserId(Integer user_type);
	
	//Page参数表示显示第几页的数据，pageSize表示每页显示的数据条数
	//查询分页
	public List<User> findByUsersId(@Param("user_type")Integer user_type,@Param("start")Integer start,@Param("end")Integer end);
	
	//根据ID查询用户
	public User loginUserById(Integer user_id);
	
	//删除用户
	public int removeUser(Integer user_id);
	
	//更新用户记录
	public int updateUser(User user);
	
	//更新头像
	public int updateUserimg(@Param("user_id")Integer user_id,@Param("user_img")String user_img);
	
	//注册用户
	public int registerUser(User user);
	
	//收藏书本
	public int collectionBooks(User user);
	
	//用户登录
	public List<User> loginUserByPhone(User user);
	public List<User> loginUserByEmail(User user);
	public List<User> loginUserByAccount(User user);
	
	//查询分组信息
	public List<Map<Integer,Integer>> findGROUPBYUser();
	
}
