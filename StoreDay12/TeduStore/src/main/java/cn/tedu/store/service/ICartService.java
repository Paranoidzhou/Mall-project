package cn.tedu.store.service;

import cn.tedu.store.bean.Cart;

public interface ICartService {
	/**
	 * 添加购物车数据
	 * @param cart
	 */
	void addCart(Cart cart);

}
