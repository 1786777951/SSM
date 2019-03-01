package cn.black.book.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.black.book.entity.User;


//�û�/����
public interface UserDao {
	//��ѯ�����û�
	public List<User> findUser();
	
	//��ѯ�û�
	public List<User> findByUserId(Integer user_type);
	
	//Page������ʾ��ʾ�ڼ�ҳ�����ݣ�pageSize��ʾÿҳ��ʾ����������
	//��ѯ��ҳ
	public List<User> findByUsersId(@Param("user_type")Integer user_type,@Param("start")Integer start,@Param("end")Integer end);
	
	//����ID��ѯ�û�
	public User loginUserById(Integer user_id);
	
	//ɾ���û�
	public int removeUser(Integer user_id);
	
	//�����û���¼
	public int updateUser(User user);
	
	//����ͷ��
	public int updateUserimg(@Param("user_id")Integer user_id,@Param("user_img")String user_img);
	
	//ע���û�
	public int registerUser(User user);
	
	//�ղ��鱾
	public int collectionBooks(User user);
	
	//�û���¼
	public List<User> loginUserByPhone(User user);
	public List<User> loginUserByEmail(User user);
	public List<User> loginUserByAccount(User user);
	
	//��ѯ������Ϣ
	public List<Map<Integer,Integer>> findGROUPBYUser();
	
}
