package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.mapper.GoodsMapper;

public class TestGoods {
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






