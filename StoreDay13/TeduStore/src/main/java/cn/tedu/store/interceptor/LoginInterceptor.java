package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//登录拦截器
public class LoginInterceptor 
			 implements HandlerInterceptor{
	//在controller方法之前执行
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.判断session中有没有user对象
		HttpSession session = 
				request.getSession();
		Object obj = session.getAttribute("user");		
		//2.如果有,return true;
		if(obj!=null){
			return true;
		}else{
		//3.如果没有,重定向的到登录页面
			String url = 
					request.getContextPath()+
					"/user/showLogin.do";
			response.sendRedirect(url);
			return false;
		}
	}
	//控制器方法执行完成,但是没有响应视图
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	//视图响应成功之后执行.
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
