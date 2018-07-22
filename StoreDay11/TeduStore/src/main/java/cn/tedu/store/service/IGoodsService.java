package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Goods;

public interface IGoodsService {
	/**
	 * 获取热门商品
	 * @param categoryId
	 * @param offset
	 * @param count
	 * @return
	 */
	List<Goods> getGoodsByCategoryId(
			Integer categoryId,
			Integer offset,
			Integer count);

}
