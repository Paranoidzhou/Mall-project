package cn.tedu.store.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAop1 {
	@Before("execution(* cn.tedu.store.service.UserService.login(..))")
	public void log(){
		System.out.println("基于方法的切点的定义...");
	}
	
}









