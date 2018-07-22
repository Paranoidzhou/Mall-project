package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;

public class TestCart {
	@Test
	public void testUpdateById(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		CartMapper cm = 
				ac.getBean("cartMapper",CartMapper.class);
		cm.updateById(10, 1);
	}
	@Test
	public void testDelById(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		CartMapper cm = 
				ac.getBean("cartMapper",CartMapper.class);
		cm.deleteById(10);
	}
	@Test
	public void testDelByBatch(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		CartMapper cm = 
				ac.getBean("cartMapper",CartMapper.class);
		cm.deleteByBatch(new Integer[]{3,4,5});
	}
	@Test
	public void testSelectByUid(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		CartMapper cm = 
				ac.getBean("cartMapper",CartMapper.class);
		System.out.println(
				cm.selectCartByUid(3));
	}
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







