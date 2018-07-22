package cn.tedu.store.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.tedu.store.service.StudentService;

//生成动态代理类的辅助类
@Component
public class StudentProxyHandler implements 
					InvocationHandler{
	@Resource
	private StudentService studentService;
	@Resource
	private StudentAop studentAop;
	//获取动态代理类的对象
	public Object getProxy(){
		//第一个参数:表示目标类的类加载器的对象
		//第二个参数:表示目标类的接口对象
		//第三个参数:表示实现了InvocationHandler接口的类的对象
		return Proxy.newProxyInstance(
			studentService.getClass().getClassLoader(), 
			studentService.getClass().getInterfaces(),
			this);
	}
	
	@Override
	public Object invoke(Object proxy, 
			Method method, Object[] args) 
					throws Throwable {
			//模拟前置通知
			studentAop.log();
			//表示通过反射调用studentService的方法
			//studentService.login(args);
			Object obj =
			method.invoke(studentService, args);
			
		return obj;
	}

}








