package org.rapp.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rapp.comm.annotation.NotLogin;
import org.rapp.service.Keys;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Session 超时拦截器
 * @author 张芳
 *
 */
public class SessionTimeOutInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = true;  
        if (handler instanceof HandlerMethod) {  
        	 NotLogin notLogin = ((HandlerMethod) handler).getMethod().getAnnotation(NotLogin.class); 
			if (notLogin == null) {// 没有该注解则需要验证
				if (request.getSession().getAttribute(Keys.LOGIN_USER) == null) {// 没登录就要求登录
					if (request.getHeader("x-requested-with") != null
							&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
						// ajax请求响应头会有x-requested-with
						response.setHeader("SessionTimeOut", "timeout");
					} else {
						String url = request.getRequestURL().toString();
						if (url.contains("/router/register")) {// 设备注册
							String param = request.getQueryString();
							if (param != null) {
								url += ("?" + param);
							}
						} else {
							url = "index";
						}
						url = URLEncoder.encode(url, "UTF-8");
						response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":"
								+ request.getServerPort() + request.getContextPath() + "/login/index?to=" + url);
					}
					flag = false;
				}
			}
        }  
        return flag;
	}
}
