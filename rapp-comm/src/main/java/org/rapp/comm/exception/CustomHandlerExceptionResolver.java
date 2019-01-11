package org.rapp.comm.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rapp.pojo.dto.result.BaseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 重写 SimpleMappingExceptionResolver 类的 resolveException() 方法 拦截普通请求和ajax请求
 * 
 * @author 张芳
 *
 */
@Component
public class CustomHandlerExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		ex.printStackTrace();
		
		response.setCharacterEncoding("UTF-8");
		if (!(request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
			// 如果不是异步请求
			if (ex instanceof BaseControllerException || ex instanceof BaseServiceException) {
				request.setAttribute("exceptionMsg", ex.getMessage());
			}else {
				request.setAttribute("exceptionMsg", "系统内部出错，若刷新后仍无效，请联系管理员");
			}
			return new ModelAndView("error/error");
		} else {// JSON格式返回
			try (PrintWriter out = response.getWriter()) {
				BaseResult<String> result = new BaseResult<>(false, ex.getMessage());
				String resultJson = new ObjectMapper().writeValueAsString(result);
				out.write(resultJson);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
