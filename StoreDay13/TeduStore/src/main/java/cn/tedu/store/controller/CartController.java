package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.vo.CartVo;

@Controller
@RequestMapping("/cart")
public class CartController extends 
				BaseController{
	
	@Resource
	private ICartService cartService;
	//显示购物车页面
	@RequestMapping("/showCart.do")
	public String showCart(
			HttpSession session,
			ModelMap map){
		List<CartVo> list = 
		cartService.getCartByUid(this.getId(session));
		map.addAttribute("cartVoList",list);
		return "cart";
	}
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

	//批量删除
	@RequestMapping("/delByBatch.do")
	public String delByBatch(Integer[] ids){
		cartService.delByBatch(ids);
		return "redirect:../cart/showCart.do";
	}
	//单行删除
	@RequestMapping("/delById.do")
	public String delById(Integer id){
		cartService.delById(id);
		return "redirect:../cart/showCart.do";
	}
	//修改数量
	@RequestMapping("/updateById.do")
	@ResponseBody
	public ResponseResult<Void> updateById(
			Integer num,Integer id){
		ResponseResult<Void> rr =
				new ResponseResult<Void>();
		cartService.updateById(num, id);
		rr.setState(1);
		rr.setMessage("成功");
		return rr;
	}
	

}









