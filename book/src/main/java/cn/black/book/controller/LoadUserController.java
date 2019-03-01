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
 * 查询用户
 * @author Exception
 *
 */
@Controller
@RequestMapping("/User")
public class LoadUserController {
	//调用Service
	@Resource
	public UserService userService;
	
	//查询
	@ResponseBody
	@RequestMapping("/loadUserAll")
	public NoteResult<List<User>> loadUserAll(){
		NoteResult<List<User>> results = userService.loadUserAll();
        //返回给前端
        return results;
	}
	
	//查询所有的读者
	@ResponseBody
	@RequestMapping("/loadUserReader")
	public NoteResult<List<User>> loadUserReader(Integer user_type){
		NoteResult<List<User>> results = userService.loadUser(user_type);
        //返回给前端
        return results;
	}
	
	//查询分页的读者
	@ResponseBody
	@RequestMapping("/loadUser")
	public NoteResult<List<User>> backReader(Integer user_type,Integer page, Integer limit) throws IOException{
		//查询所有的读者
		NoteResult<List<User>> results = userService.loadUser(user_type);
		//查询分页的读者
		NoteResult<List<User>> result = userService.loadUsers(user_type,page,limit);
		NoteResult<List<User>> table = new NoteResult<List<User>>();
		if(results.getStatus() == 0 && result.getStatus() == 0) {
			//这是layui要求返回的json数据格式
			table.setStatus(0);
			table.setMsg("成功");
	        //将全部数据的条数作为count传给前台（一共多少条）
			table.setCount(results.getCount());
	        //将分页后的数据返回（每页要显示的数据）
	        table.setData(result.getData());
		}
        //返回给前端
        return table;
	}
	
	//清除SESSION
	@ResponseBody
	@RequestMapping("/cleanSession")
	public int initSession(HttpServletRequest request){
		Enumeration em = request.getSession().getAttributeNames();
		while(em.hasMoreElements()){
			 request.getSession().removeAttribute(em.nextElement().toString());
		}
		return 1;
	}
	
	//删除用户
	@ResponseBody
	@RequestMapping("/removeUsers")
	public NoteResult<List> removeUsers(@RequestBody ArrayList<Integer> list){
		NoteResult<List> result = userService.removeUsers(list);
		return result;
	}
	
	//根据ID查询用户
	@ResponseBody
	@RequestMapping("/loadUserById")
	public NoteResult<User> loadUserById(HttpServletRequest  request) {
		Integer user_id = (Integer) request.getSession().getAttribute("user_id");
		NoteResult<User> result = userService.loadUserById(user_id);
		return result;
	}
	
	//用户登录
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
		//用户登陆成功!
		if(result.getStatus()==0) {
			List list = result.getData();
			User user = (User) list.get(0);
			request.getSession().setAttribute("user_id", user.getUser_id());
		}
		return result;
	}
	
	//更新用户
	@ResponseBody
	@RequestMapping("/updateUser")
	public NoteResult<List> updateUsers(Integer user_id,String user_phone,String user_name,String user_account,Integer user_sex,String user_email,Integer user_type,String user_city,String user_like,String user_Introduce){
		NoteResult<List> result = userService.updateUser(user_id,user_phone, user_name, user_account, user_sex, user_email, user_type, user_city,user_like,user_Introduce);
		return result;
	}
	
	//收藏书
	@ResponseBody
	@RequestMapping("/updateCollection")
	public NoteResult<List> updateCollection(Integer user_id,String user_collection){
		NoteResult<List> result = userService.collectionBooks(user_id, user_collection);
		return result;
	}
	
	//注册账号
	@ResponseBody
	@RequestMapping("/registerUser")
	public NoteResult registerUser(String user_phone,String user_name,String user_account,Integer user_sex,String user_password,String user_email,String user_city,String user_like) {
		NoteResult result = userService.registerUser(user_phone,user_name, user_account, user_sex, user_password, user_email, 0, user_city, user_like);
		return result;
	}
	//更换头像
	@ResponseBody
	@RequestMapping("/registerUserImg")
	public NoteResult registerUserImg(Integer user_id,String user_img) {
		NoteResult result = userService.updateUserimg(user_id, user_img);
		return result;
	}
	
	//提交头像
	@ResponseBody
	@RequestMapping(value="/uploader" , produces="text/html;charset=UTF-8")
    public String  upload(@RequestParam("fileToUpload")MultipartFile file,Integer user_id,HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = "";// 文件路径
        String json = "";
        if (file!=null) {// 判断上传的文件是否为空
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:"+fileName);
            // 判断文件类型
            type = fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
            	    if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                     // 项目在容器中实际发布运行的根路径
                     String realPath = request.getSession().getServletContext().getRealPath("/")+"lib"+File.separator+"images"+File.separator+"user"+File.separator;
                     // 自定义的文件名称
                     String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                     // 设置存放图片文件的路径
                     path = realPath+/*System.getProperty("file.separator")+*/trueFileName;
                     System.out.println("存放图片文件的路径:"+realPath);
                     // 转存文件到指定的路径
                     file.transferTo(new File(path));
                     System.out.println("文件成功上传到指定目录下");                  
                     json = "{\"res\":\"1\",\"user_img\":\"user/"+trueFileName+"\"}";
                 }else {
                     System.out.println("不是我们想要的文件类型,请按要求重新上传");
                     //return null;
                     json = "{\"res\":0}";
                 }
            }else {
                System.out.println("文件类型为空");
                //return null;
                json = "{\"res\":0}";
           }  
        }else{
            System.out.println("没有找到相对应的文件");
            json = "{\"res\":0}";
            //return null;
       }
        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream().print(json);
        return json;
	}
	
	//查询书分类
	@ResponseBody
	@RequestMapping("/findGROUPBYUser")
	public NoteResult<List> findGROUPBYUser(){
		NoteResult<List> result = userService.findGROUPBYUser();
		return result;
	}
	
}
