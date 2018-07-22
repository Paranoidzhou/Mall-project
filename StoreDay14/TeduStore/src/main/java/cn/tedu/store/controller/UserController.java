package cn.tedu.store.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController 
		extends BaseController{
	@Resource
	private IUserService userService;
	//显示register.jsp页面
	@RequestMapping("/showRegister.do")
	public String showRegister(){
		return "register";
	}
	//显示login页面
	@RequestMapping("/showLogin.do")
	public String showLogin(){
		return "login";
	}
	//显示修改密码的页面
	@RequestMapping("/showPassword.do")
	public String showPassword(){
		return "personal_password";
	}
	//显示修改个人信息
	@RequestMapping("/showPersonInfo.do")
	public String showPersonInfo(){
		return "personPage";
	}
	
	
	//验证用户名是否存在
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public ResponseResult<Void> checkUsername(
			String username){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		boolean b = 
			userService.checkUsername(username);
	
		if(b){
			rr.setState(0);
			rr.setMessage("用户名不可以使用");
		}else{
			rr.setState(1);
			rr.setMessage("用户名可以使用");
		}
		return rr;
	}
	//验证邮箱是否存在
	@RequestMapping("/checkEmail.do")
	@ResponseBody
	public ResponseResult<Void> checkEmail(
			String email){
		ResponseResult<Void> rr =
				new ResponseResult<Void>();
		if(userService.checkEmail(email)){
			rr.setState(0);
			rr.setMessage("邮箱不可以使用");
		}else{
			rr.setState(1);
			rr.setMessage("邮箱可以使用");
		}
		return rr;
	}
	//验证电话号码是否存在
	@RequestMapping("/checkPhone.do")
	@ResponseBody
	public ResponseResult<Void> checkPhone(String phone){
		ResponseResult<Void> rr =
				new  ResponseResult<Void>();
		if(userService.checkPhone(phone)){
			rr.setState(0);
			rr.setMessage("电话号码不可以使用");
		}else{
			rr.setState(1);
			rr.setMessage("电话号码可以使用");
		}
		return rr;
	}
	//提交注册
	@RequestMapping("/register.do")
	@ResponseBody
	public ResponseResult<Void> register(
	 @RequestParam("uname") String username,
	 @RequestParam("upwd") String password,
	 String email,String phone){
		ResponseResult<Void> rr =
				new ResponseResult<Void>();
		try{
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setPhone(phone);
			userService.register(user);
			rr.setState(1);
			rr.setMessage("注册成功");
		}catch(RuntimeException e){
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		
		return rr;
	}
	//登录
	@RequestMapping("/login.do")
	@ResponseBody
	public ResponseResult<Void> login(
			String username,
			String password,
			HttpSession session){
		//1.创建rr对象
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		try{
			//2.调用业务层方法login(username,password);返回user对象;把user对象设置到session中.
			User user = 
					userService.login(username, password);
			session.setAttribute("user",user);
			//3.state = 1 message = "登录成功"
			rr.setState(1);
			rr.setMessage("登录成功");
		}catch(RuntimeException e){
			//4.state = 0 message = e.getMessage()
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	
	//退出
	@RequestMapping("/exit.do")
	public String exit(HttpSession session){
		session.invalidate();
		return "redirect:../main/showIndex.do";
	}
	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public ResponseResult<Void> updatePassword(
			HttpSession session,
			String oldPwd,
			String newPwd){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		try{
			
			userService.changePassword(
					this.getId(session), 
				    oldPwd, newPwd);
			rr.setState(1);
			rr.setMessage("修改成功!");
		}catch(RuntimeException e){
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	//修改个人信息
	@RequestMapping("/updateUser.do")
	@ResponseBody
	public ResponseResult<Void> updateUser(
			HttpSession session,
			String username,
			Integer gender,
			String phone,
			String email){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		try{
			userService.updateUser(
					this.getId(session), 
					username, gender, 
					phone, email);
			//获取user对象
			User user = 
				userService.getUserById(
						this.getId(session));
			//覆盖session的user对象
			session.setAttribute("user", user);
			rr.setState(1);
			rr.setMessage("修改成功");
		}catch(RuntimeException e){
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	//上传图片
	@RequestMapping("/upload.do")
	@ResponseBody
	public ResponseResult<Void> upload(
	@RequestParam("file") MultipartFile file,
	HttpSession session) throws Exception{
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		//获取服务器的真实路径
		String path = 
				session.getServletContext().
				getRealPath("/");
		System.out.println(path);
		//上传
		file.transferTo(
				new File(path,"/upload/"+file.getOriginalFilename()));
		
		//保存头像
		userService.updateImage(
				"/upload/"+file.getOriginalFilename(), 
				this.getId(session));
		//更新session中的user对象
		session.setAttribute(
				"user",
				userService.
				getUserById(this.getId(session)));
		
		rr.setState(1);
		rr.setMessage("上传图片成功!");
		return rr;
		
	}
}















