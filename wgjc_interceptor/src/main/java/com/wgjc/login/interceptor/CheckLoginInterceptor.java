package com.wgjc.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wgjc.login.annotation.Login;

/** 
 * @Description: 登录权限注解验证
 * @author hc
 * @date 2019年7月29日下午4:13:38
 */
public class CheckLoginInterceptor implements HandlerInterceptor {
	public static Log log = LogFactory.getLog(CheckLoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		Login methoLlogin = null;
		Login classLogin = null;
		if (!(handler instanceof HandlerMethod)) {
            return true;
        }
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        String methodName = handlerMethod.getMethod().getName();
        log.info("访问方法名：" + methodName);
        
        methoLlogin = handlerMethod.getMethod().getAnnotation(Login.class);//反射查看方法上是否有注解
        classLogin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Login.class);//反射查看类上面是否有注解
        if(null == methoLlogin && null == classLogin) {
        	return true;
        }
        
        if (null == request.getSession().getAttribute("loginUser")) {
           log.info("用户未登录！");
       	   request.getRequestDispatcher("/login").forward(request,response);
           return false;
       }
       return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
