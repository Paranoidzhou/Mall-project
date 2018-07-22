package cn.tedu.store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.ICartService;

@Controller
@RequestMapping("/cart")
public class CartController extends 
				BaseController{
	@Resource
	private ICartService cartService;
	//添加购物车
	@RequestMapping("/addCart.do")
	@ResponseBody
	public ResponseResult<Void> addCart(
			HttpSession session,
			String goodsId,
			Integer num){
		ResponseResult<Void> rr = 
				new ResponseResult<Void>();
		Cart cart = new Cart();
		cart.setUid(this.getId(session));
		cart.setGoodsId(goodsId);
		cart.setNum(num);
		cartService.addCart(cart);
		rr.setState(1);
		rr.setMessage("添加成功!");
		return rr;
	}

}









