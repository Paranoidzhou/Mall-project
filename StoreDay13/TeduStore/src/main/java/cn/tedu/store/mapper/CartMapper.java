package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.vo.CartVo;

public interface CartMapper {
	/**
	 * 插入数据到cart表
	 * @param cart
	 */
	void insertCart(Cart cart);
	/**
	 * 查询购物车信息
	 * @param uid
	 * @return
	 */
	List<CartVo> selectCartByUid(Integer uid);
	/**
	 * 批量删除
	 * @param ids
	 */
	void deleteByBatch(Integer [] ids);
	/**
	 * 单行删除
	 * @param id
	 */
	void deleteById(Integer id);
	
	void updateById(@Param("num") Integer num,
			        @Param("id") Integer id);

}









