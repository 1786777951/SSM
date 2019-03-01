package test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import cn.black.book.entity.User;
import cn.black.book.service.UserService;
import cn.black.book.util.NoteResult;
import test.TestBase;

public class TestUserService extends TestBase{
	private UserService userService;
	@Before
	public void init() {
		//����ķ���
		userService = super.getContext().getBean("userService",UserService.class);
	}
	
	@Test
	public void adminTest() {
		//��ѯ���еĶ���
		NoteResult<List<User>> results = userService.loadUser(0);
		//��ѯ��ҳ�Ķ���
		NoteResult<List<User>> result = userService.loadUsers(0,1,10);
				
		NoteResult<List<User>> table = new NoteResult<List<User>>();
		if(results.getStatus() == 0 && result.getStatus() == 0) {
			//����layuiҪ�󷵻ص�json���ݸ�ʽ
			table.setStatus(0);
			table.setMsg("�ɹ�");
	        //��ȫ�����ݵ�������Ϊcount����ǰ̨��һ����������
			table.setCount(results.getCount());
	        //����ҳ������ݷ��أ�ÿҳҪ��ʾ�����ݣ�
	        table.setData(result.getData());
		}else {
			
		}
		System.out.println(table);
	}
	
	@Test
	public void registerUser() {
		NoteResult result = userService.registerUser("12312","���", "123456", 0, "123456", "123@qq.com", 0, "��ݸ", "������");
		System.out.println(result);
	}
	
	@Test 
	public void updateUser() {
		NoteResult result = userService.updateUser(10060, "213", "123132", "", 0, "",  0, "", "", null);
		System.out.println(result);
	}
	
	@Test 
	public void loginUser() {
		String User = "{user_phone:\"13713428454\","
				 + "user_account:\"1\","
				 + "user_email:\"1\","
				 + "user_password:\"123456\",}";
		NoteResult result = userService.loginUserByPhone("13713428454", "123456");
		System.out.println(result);
	}
	
	@Test 
	public void loginUserById() {
		NoteResult<List> result = userService.loginUserByPhone("13713428454", "123456");
		List list = result.getData();
		User user = (User) list.get(0);
		System.out.println(user.getUser_id());
	}
	
	@Test 
	public void loadReader() {
		NoteResult<List<User>> result = userService.loadUserAll();
		System.out.println(result);
	}
}
