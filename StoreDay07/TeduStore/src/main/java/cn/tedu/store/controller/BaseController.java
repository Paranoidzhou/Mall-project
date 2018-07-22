package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import cn.tedu.store.bean.User;

public class BaseController {
	public Integer getId(HttpSession session){
		User user = 
			(User)session.getAttribute("user");
		if(user==null){
			return null;
		}else{
			return user.getId();
		}
	}

}






