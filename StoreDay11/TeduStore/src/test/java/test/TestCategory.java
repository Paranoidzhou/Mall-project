package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;
import cn.tedu.store.service.IGoodsCategoryService;

public class TestCategory {
	@Test
	public void testGetCategory(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		IGoodsCategoryService cs = 
				ac.getBean("goodsCategoryService",
						IGoodsCategoryService.class);
		List<GoodsCategory> list2 =
				cs.getCategoryByParentId(161,0,3);
		System.out.println(list2);
		for(GoodsCategory cc :list2){
			System.out.println(
				cs.getCategoryByParentId(
						cc.getId(),null,null).size());
		}
		
	}
	@Test
	public void testSelectCategory(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		GoodsCategoryMapper cm = 
				ac.getBean("goodsCategoryMapper",
				GoodsCategoryMapper.class);
		//二级分类
		List<GoodsCategory> list = 
			cm.selectCategoryByParentId(161, 0, 3);
		System.out.println(list);
		//三级分类
		for(GoodsCategory cc:list){
			System.out.println(
					cm.selectCategoryByParentId(
					cc.getId(),null, null).size());
		}
	}

}








