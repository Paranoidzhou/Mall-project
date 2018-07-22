package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;
@Service
public class GoodsCategoryService 
		implements IGoodsCategoryService{
	@Resource
	private GoodsCategoryMapper goodsCategoryMapper;
	@Override
	public List<GoodsCategory> getCategoryByParentId(
			Integer parentId, Integer offset, Integer count) {
		
		return goodsCategoryMapper.selectCategoryByParentId(
				parentId, offset, count);
	}

}




