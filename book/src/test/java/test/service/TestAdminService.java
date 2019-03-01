package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.black.book.entity.Admin;
import cn.black.book.service.AdminService;
import cn.black.book.util.NoteResult;
import test.TestBase;

public class TestAdminService extends TestBase{
	private AdminService adminService;
	@Before
	public void init() {
		//父类的方法
		adminService = super.getContext().getBean("adminService",AdminService.class);
	}
	
	@Test
	public void adminTest() {
		NoteResult<List<Admin>> result = adminService.loadAdmin();
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		for(Admin admin:result.getData()) {
			System.out.println(admin);
		}
	}
	
	@Test
	public void adminTestLogin() {
		NoteResult<Admin> result = adminService.findAdminAccount("admin", "123456");
		System.out.println(result);
	}
	
	
}
