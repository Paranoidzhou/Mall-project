package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;

public class TestCart {
	@Test
	public void testInsertCart(){
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				"application-dao.xml",
				"application-service.xml");
		CartMapper cm = 
	ac.getBean("cartMapper",CartMapper.class);
		Cart cart = new Cart();
		cart.setUid(3);
		cart.setGoodsId("10000000");
		cart.setNum(10);
		
		cm.insertCart(cart);
	}

}







