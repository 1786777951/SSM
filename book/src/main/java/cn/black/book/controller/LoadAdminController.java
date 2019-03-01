package cn.black.book.controller;
/**
 * ����Ա
 */
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.black.book.entity.Admin;
import cn.black.book.service.AdminService;
import cn.black.book.util.NoteResult;

@Controller
@RequestMapping("/Admin")
public class LoadAdminController {

	//����Service
	@Resource
	public AdminService adminService;
	
	@ResponseBody
	@RequestMapping("/loadAdmin")
	public NoteResult<List<Admin>> execute(){
		//��ѯ���еĹ���Ա
		NoteResult<List<Admin>> result = adminService.loadAdmin();
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/findAdminID")
	public NoteResult<Admin> findAdminID(HttpServletRequest  request){
		Integer admin_id = (Integer) request.getSession().getAttribute("admin_id");
		//��ѯ��������Ա
		NoteResult<Admin> result = adminService.findAdminID(admin_id);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/findAdminAccount")
	public NoteResult<Admin> findAdminAccount(String admin_account,String admin_password,HttpServletRequest  request){
		//��ѯ��������Ա
		NoteResult<Admin> result = adminService.findAdminAccount(admin_account, admin_password);
		if(result.getStatus()==0) {
			Admin admin = (Admin)result.getData();
			request.getSession().setAttribute("admin_id", admin.getAdmin_id());
		}
		return result;
	}
}
