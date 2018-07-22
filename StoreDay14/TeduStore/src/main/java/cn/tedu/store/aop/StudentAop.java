package cn.tedu.store.aop;

import org.springframework.stereotype.Component;

@Component
public class StudentAop {
	public void log(){
		System.out.println("StudentAop.log");
	}

}
