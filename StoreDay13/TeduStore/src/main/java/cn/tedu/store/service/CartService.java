package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.vo.CartVo;
@Service
public class CartService implements ICartService{
	@Resource
	private CartMapper cartMapper;
	@Override
	public void addCart(Cart cart) {
		cartMapper.insertCart(cart);
		
	}
	@Override
	public List<CartVo> getCartByUid(Integer uid) {
		
		return cartMapper.selectCartByUid(uid);
	}
	@Override
	public void delByBatch(Integer[] ids) {
		cartMapper.deleteByBatch(ids);
		
	}
	@Override
	public void delById(Integer id) {
		cartMapper.deleteById(id);
		
	}
	@Override
	public void updateById(Integer num, Integer id) {
		cartMapper.updateById(num, id);
		
	}

}
