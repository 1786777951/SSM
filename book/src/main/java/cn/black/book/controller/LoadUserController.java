package cn.black.book.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.black.book.entity.User;
import cn.black.book.service.UserService;
import cn.black.book.util.NoteResult;

/**
 * ��ѯ�û�
 * @author Exception
 *
 */
@Controller
@RequestMapping("/User")
public class LoadUserController {
	//����Service
	@Resource
	public UserService userService;
	
	//��ѯ
	@ResponseBody
	@RequestMapping("/loadUserAll")
	public NoteResult<List<User>> loadUserAll(){
		NoteResult<List<User>> results = userService.loadUserAll();
        //���ظ�ǰ��
        return results;
	}
	
	//��ѯ���еĶ���
	@ResponseBody
	@RequestMapping("/loadUserReader")
	public NoteResult<List<User>> loadUserReader(Integer user_type){
		NoteResult<List<User>> results = userService.loadUser(user_type);
        //���ظ�ǰ��
        return results;
	}
	
	//��ѯ��ҳ�Ķ���
	@ResponseBody
	@RequestMapping("/loadUser")
	public NoteResult<List<User>> backReader(Integer user_type,Integer page, Integer limit) throws IOException{
		//��ѯ���еĶ���
		NoteResult<List<User>> results = userService.loadUser(user_type);
		//��ѯ��ҳ�Ķ���
		NoteResult<List<User>> result = userService.loadUsers(user_type,page,limit);
		NoteResult<List<User>> table = new NoteResult<List<User>>();
		if(results.getStatus() == 0 && result.getStatus() == 0) {
			//����layuiҪ�󷵻ص�json���ݸ�ʽ
			table.setStatus(0);
			table.setMsg("�ɹ�");
	        //��ȫ�����ݵ�������Ϊcount����ǰ̨��һ����������
			table.setCount(results.getCount());
	        //����ҳ������ݷ��أ�ÿҳҪ��ʾ�����ݣ�
	        table.setData(result.getData());
		}
        //���ظ�ǰ��
        return table;
	}
	
	//���SESSION
	@ResponseBody
	@RequestMapping("/cleanSession")
	public int initSession(HttpServletRequest request){
		Enumeration em = request.getSession().getAttributeNames();
		while(em.hasMoreElements()){
			 request.getSession().removeAttribute(em.nextElement().toString());
		}
		return 1;
	}
	
	//ɾ���û�
	@ResponseBody
	@RequestMapping("/removeUsers")
	public NoteResult<List> removeUsers(@RequestBody ArrayList<Integer> list){
		NoteResult<List> result = userService.removeUsers(list);
		return result;
	}
	
	//����ID��ѯ�û�
	@ResponseBody
	@RequestMapping("/loadUserById")
	public NoteResult<User> loadUserById(HttpServletRequest  request) {
		Integer user_id = (Integer) request.getSession().getAttribute("user_id");
		NoteResult<User> result = userService.loadUserById(user_id);
		return result;
	}
	
	//�û���¼
	@ResponseBody
	@RequestMapping("/loginUser")
	public NoteResult<List> loginUser(String user_phone,String user_account,String user_email,String user_password,String type,HttpServletRequest  request){
		NoteResult<List> result = null;
		if("phone".equals(type)) {
			result = userService.loginUserByPhone(user_phone, user_password);
		}else if("account".equals(type)) {
			result = userService.loginUserByAccount(user_account, user_password);
		}else if("email".equals(type)) {
			result = userService.loginUserByEmail(user_email, user_password);
		}
		//�û���½�ɹ�!
		if(result.getStatus()==0) {
			List list = result.getData();
			User user = (User) list.get(0);
			request.getSession().setAttribute("user_id", user.getUser_id());
		}
		return result;
	}
	
	//�����û�
	@ResponseBody
	@RequestMapping("/updateUser")
	public NoteResult<List> updateUsers(Integer user_id,String user_phone,String user_name,String user_account,Integer user_sex,String user_email,Integer user_type,String user_city,String user_like,String user_Introduce){
		NoteResult<List> result = userService.updateUser(user_id,user_phone, user_name, user_account, user_sex, user_email, user_type, user_city,user_like,user_Introduce);
		return result;
	}
	
	//�ղ���
	@ResponseBody
	@RequestMapping("/updateCollection")
	public NoteResult<List> updateCollection(Integer user_id,String user_collection){
		NoteResult<List> result = userService.collectionBooks(user_id, user_collection);
		return result;
	}
	
	//ע���˺�
	@ResponseBody
	@RequestMapping("/registerUser")
	public NoteResult registerUser(String user_phone,String user_name,String user_account,Integer user_sex,String user_password,String user_email,String user_city,String user_like) {
		NoteResult result = userService.registerUser(user_phone,user_name, user_account, user_sex, user_password, user_email, 0, user_city, user_like);
		return result;
	}
	//����ͷ��
	@ResponseBody
	@RequestMapping("/registerUserImg")
	public NoteResult registerUserImg(Integer user_id,String user_img) {
		NoteResult result = userService.updateUserimg(user_id, user_img);
		return result;
	}
	
	//�ύͷ��
	@ResponseBody
	@RequestMapping(value="/uploader" , produces="text/html;charset=UTF-8")
    public String  upload(@RequestParam("fileToUpload")MultipartFile file,Integer user_id,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = "";// �ļ�·��
        String json = "";
        if (file!=null) {// �ж��ϴ����ļ��Ƿ�Ϊ��
            String type = null;// �ļ�����
            String fileName = file.getOriginalFilename();// �ļ�ԭ����
            System.out.println("�ϴ����ļ�ԭ����:"+fileName);
            // �ж��ļ�����
            type = fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// �ж��ļ������Ƿ�Ϊ��
            	    if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                     // ��Ŀ��������ʵ�ʷ������еĸ�·��
                     String realPath = request.getSession().getServletContext().getRealPath("/")+"lib"+File.separator+"images"+File.separator+"user"+File.separator;
                     // �Զ�����ļ�����
                     String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                     // ���ô��ͼƬ�ļ���·��
                     path = realPath+/*System.getProperty("file.separator")+*/trueFileName;
                     System.out.println("���ͼƬ�ļ���·��:"+realPath);
                     // ת���ļ���ָ����·��
                     file.transferTo(new File(path));
                     System.out.println("�ļ��ɹ��ϴ���ָ��Ŀ¼��");                  
                     json = "{\"res\":\"1\",\"user_img\":\"user/"+trueFileName+"\"}";
                 }else {
                     System.out.println("����������Ҫ���ļ�����,�밴Ҫ�������ϴ�");
                     //return null;
                     json = "{\"res\":0}";
                 }
            }else {
                System.out.println("�ļ�����Ϊ��");
                //return null;
                json = "{\"res\":0}";
           }  
        }else{
            System.out.println("û���ҵ����Ӧ���ļ�");
            json = "{\"res\":0}";
            //return null;
       }
        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream().print(json);
        return json;
	}
	
	//��ѯ�����
	@ResponseBody
	@RequestMapping("/findGROUPBYUser")
	public NoteResult<List> findGROUPBYUser(){
		NoteResult<List> result = userService.findGROUPBYUser();
		return result;
	}
	
}
