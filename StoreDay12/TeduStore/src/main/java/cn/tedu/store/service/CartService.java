package cn.tedu.store.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
@Service
public class CartService implements ICartService{
	@Resource
	private CartMapper cartMapper;
	@Override
	public void addCart(Cart cart) {
		cartMapper.insertCart(cart);
		
	}

}
