package cn.tedu.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address")
public class AddressController {
	//显示address页面
	@RequestMapping("/showAddress.do")
	public String showAddress(){
		return "addressAdmin";
	}
		
}










