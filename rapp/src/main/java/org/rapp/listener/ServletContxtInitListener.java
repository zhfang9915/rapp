package org.rapp.listener;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.rapp.pojo.web.Rapp;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * ServletContxt初始化加载环境变量
 * @author 张芳
 *
 */
@Component("servletContxtInitListener")
public class ServletContxtInitListener implements ServletContextAware {

	@Resource Rapp rapp;
	
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		onApplicationInIt();
	}

	public void onApplicationInIt() {
		if (servletContext != null) {
			servletContext.setAttribute("rapp", rapp);
		}

	}

}
