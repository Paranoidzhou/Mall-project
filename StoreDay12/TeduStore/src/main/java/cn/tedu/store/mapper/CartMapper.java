package cn.tedu.store.mapper;

import cn.tedu.store.bean.Cart;

public interface CartMapper {
	/**
	 * 插入数据到cart表
	 * @param cart
	 */
	void insertCart(Cart cart);

}
