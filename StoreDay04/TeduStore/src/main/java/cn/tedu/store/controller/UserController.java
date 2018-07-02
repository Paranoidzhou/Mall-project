package cn.tedu.store.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	//显示register.jsp页面
	@RequestMapping("/showRegister.do")
	public String showRegister(){
		return "register";
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
}















