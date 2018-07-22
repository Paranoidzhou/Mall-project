package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Goods;

public interface GoodsMapper {
	/**
	 * 查询热门商品
	 * @param categoryId
	 * @param offset
	 * @param count
	 * @return
	 */
	List<Goods> selectGoodsByCategoryId(
			@Param("categoryId") Integer categoryId,
			@Param("offset") Integer offset,
			@Param("count") Integer count);

}
