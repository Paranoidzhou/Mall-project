package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.mapper.GoodsMapper;

public class TestGoods {
	@Test
	public void testSelectGoodsById(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
					"application-dao.xml",
					"application-service.xml");
		GoodsMapper gm = 
				ac.getBean("goodsMapper",
				GoodsMapper.class);
		System.out.println(
				gm.selectGoodsById("10000000"));
	}
	@Test
	public void testGetCount(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
					"application-dao.xml",
					"application-service.xml");
		GoodsMapper gm = 
				ac.getBean("goodsMapper",
				GoodsMapper.class);
		System.out.println(gm.selectCount(163));
	}
	@Test
	public void testSelectByCid(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
					"application-dao.xml",
					"application-service.xml");
		GoodsMapper gm = 
				ac.getBean("goodsMapper",
				GoodsMapper.class);
		System.out.println(
		gm.selectGoodsByCategoryId(163, 0,3));
	}

}






