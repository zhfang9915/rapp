package org.rapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rapp.pojo.dto.param.RouterParam;
import org.rapp.pojo.dto.result.ApiResult;
import org.rapp.pojo.entry.Router;
import org.rapp.repository.RouterDao;
import org.rapp.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 认证
 * @author 张芳
 *
 */
@Component
public class ApiTokenInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	RouterDao routerDao;
	
	@Autowired
	ApiService apiService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			ApiResult<Boolean> result = null;
			ObjectMapper mapper = new ObjectMapper();
			
			String devNo = request.getParameter("devNo");
			String token = request.getParameter("token");
			//查询设备信息
			RouterParam rParam = new RouterParam();
			rParam.setDevNo(devNo);
			Router router = routerDao.queryRouterByDevNo(rParam);
			if (router == null) {
				result = new ApiResult<>("999", "认证未通过(无效的设备)");
				response.getWriter().print(mapper.writeValueAsString(result));
				return false;
			}
			if (!apiService.authentication(token, router)) {
				result = new ApiResult<>("999", "认证未通过(设备信息异常)");
				response.getWriter().print(mapper.writeValueAsString(result));
				return false;
			}
		}
		return true;
	}
	
}
