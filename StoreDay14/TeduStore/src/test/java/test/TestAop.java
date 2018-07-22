package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.aop.StudentProxyHandler;
import cn.tedu.store.service.IStudentService;

public class TestAop {
	@Test
	public void testStudentProxy(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-aop.xml",
						"application-service.xml",
						"application-dao.xml");
		IStudentService st =
				ac.getBean("studentProxy",
				IStudentService.class);
		st.addStudent();
		
	}
	@Test
	public void testStudentHandler(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"application-aop.xml",
						"application-service.xml",
						"application-dao.xml");
		//获取辅助类的对象
		StudentProxyHandler sph = 
		   ac.getBean("studentProxyHandler",
				StudentProxyHandler.class);
		//获取代理类的对象
		IStudentService ss = 
			(IStudentService)sph.getProxy();
		ss.addStudent();
	}

}









