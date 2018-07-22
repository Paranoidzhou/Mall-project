package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;

public class TestUser {
	
	@Test
	public void testUpdateImage(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		/*UserMapper um = 
			ac.getBean("userMapper",
					UserMapper.class);*/
		IUserService us = 
				ac.getBean("userService",IUserService.class);
		
		us.updateImage("/upload/a.png", 3);
	}
	
	
	
	@Test
	public void testUpdateU(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		IUserService us = 
				ac.getBean("userService",IUserService.class);
		
		us.updateUser(1, "admin1", 0, 
				"12345678957", "123@tedu.cn");
	}
	
	
	
	
	
	
	
	@Test
	public void testChangePassword(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		IUserService us = 
				ac.getBean("userService",IUserService.class);
		us.changePassword(6, "111111", "123456");
	}
	@Test
	public void testSelectById(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		UserMapper um = 
				ac.getBean("userMapper",UserMapper.class);
		System.out.println(
				um.selectUserById(6));
	}
	
	
	@Test
	public void testUpdateUser(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		UserMapper um = 
				ac.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setId(6);
		user.setUsername("小黑");
		user.setGender(1);
		user.setEmail("xiaohei@tedu.cn");
		user.setPhone("10086");
		//user.setPassword("111111");
		um.updateUser(user);
	}
	@Test
	public void testLogin(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		IUserService us = 
				ac.getBean("userService",IUserService.class);
		System.out.println(
				us.login("admin1","123456"));
	}
	
	
	
	@Test
	public void testCheckUsername(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		IUserService us = 
				ac.getBean("userService",IUserService.class);
		System.out.println(
				us.checkUsername("addmin"));
	}
	@Test
	public void testCheckPhone(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		IUserService us = 
				ac.getBean("userService",IUserService.class);
		System.out.println(
				us.checkPhone("13800138500"));
	}
	@Test
	public void testSelectByPhone(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		UserMapper um = 
				ac.getBean("userMapper",UserMapper.class);
		
		System.out.println(
				um.selectByPhone("13800138600"));
	}
	
	@Test
	public void testCheckEmail(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		IUserService us = 
				ac.getBean("userService",
						IUserService.class);
		System.out.println(
				us.checkEmail("admin555@tedu.cn"));
	}
	@Test
	public void testSelectByEmail(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-dao.xml",
						"application-service.xml");
		UserMapper um = 
				ac.getBean("userMapper",UserMapper.class);
		System.out.println(
				um.selectByEmail("admin2@tedu.cn"));
	}
	@Test
	public void testRegister(){
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				"application-dao.xml",
				"application-service.xml");
		IUserService is =
				ac.getBean("userService",
				IUserService.class);
		User user = new User();
		user.setUsername("admin1");
		user.setPassword("123456");
		user.setPhone("13920139000");
		user.setEmail("xiaobai1@tedu.cn");
		is.register(user);
	}
	
	
	
	@Test
	public void testSelectByUsername(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("application-dao.xml");
				
		UserMapper um =
				ac.getBean("userMapper",UserMapper.class);
		System.out.println(
			um.selectUserByUsername("admin1"));
	}
	@Test
	public void testInsertUser(){
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext("application-dao.xml");
		UserMapper um =
				ac.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
		user.setPhone("13800138000");
		user.setEmail("admin@tedu.cn");
		um.insertUser(user);
	}

}








