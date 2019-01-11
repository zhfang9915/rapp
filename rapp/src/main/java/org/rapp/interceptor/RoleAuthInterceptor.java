package org.rapp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rapp.comm.annotation.Permission;
import org.rapp.pojo.account.Auth;
import org.rapp.pojo.account.Role;
import org.rapp.pojo.account.User;
import org.rapp.service.Keys;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限校验 拦截器
 * @author 张芳
 *
 */
public class RoleAuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = true;  
//        if (handler instanceof HandlerMethod) {  
//            Permission permission = ((HandlerMethod) handler).getMethod().getAnnotation(Permission.class); 
//            if (permission != null) {// 有该注解则需要验证
//            	User user = (User) request.getSession().getAttribute(Keys.LOGIN_USER);
//            	Role role = user.getRole();
//            	if (!isHasAuth(role.getAuths(), permission.value())) {
//                	if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
//        				//ajax请求响应头会有x-requested-with  
//        				response.setHeader("Permission", "NoPermission");
//        			}else {
//        				response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" 
//        	        			+ request.getServerPort() + request.getContextPath()+"/401");
//        			}
//                    flag = false;  
//				}
//            }  
//        }  
        return flag;
	}
	
	/**
	 * 是否具有相应的权限
	 * @param auths
	 * @param path
	 * @return
	 */
	private boolean isHasAuth(List<Auth> auths, String path) {
		for (Auth auth : auths) {
			if (path.equals(auth.getRequestUrl())) {
				return true;
			}
		}
		return false;
	}
}
