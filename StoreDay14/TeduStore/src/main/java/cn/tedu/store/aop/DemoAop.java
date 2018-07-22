package cn.tedu.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
//@Component表示实例化对象
//@Aspect表示当前类是切面类
@Component
@Aspect
public class DemoAop {
	//@Before表示在业务层方法之前执行
	//bean()表示规范的格式
	//bean(userService)表示在userService业务层的
	//方法之前执行
	@Before("bean(userService)")
	public void test1(){
		System.out.println("之前....");
	}
	//@After最终通知,不管有没有异常都会执行的代码
	@After("bean(userService)")
	public void test2(){
		System.out.println("之后1...");
	}
	//@AfterReturning不发生异常的时候执行的代码
	@AfterReturning("bean(userService)")
	public void test3(){
		System.out.println("之后2...");
	}
	//@AfterThrowing发生异常的时候执行的代码
	@AfterThrowing("bean(userService)")
	public void test4(){
		System.out.println("发生异常...");
	}
	//环绕通知
	//1.必须要有返回值
	//2.必须有参数 ProceedingJoinPoint jp
	//3.参数对象调用方法jp.proceed();
	@Around("bean(userService)")
	public Object test5(ProceedingJoinPoint jp) 
			throws Throwable{
		System.out.println("环绕通知前....");
		Object obj = jp.proceed();//表示调用业务方法
		//login();
		System.out.println("环绕通知后....");
		
		return obj;//返回业务逻辑方法的返回值
	}
	//测试业务层的性能
	@Around("bean(userService)")
	public Object test6(ProceedingJoinPoint jp) 
			throws Throwable{
		long start = System.currentTimeMillis();
		Object obj = jp.proceed();//表示调用业务方法
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);
		return obj;//返回业务逻辑方法的返回值
	}
	
	
}








