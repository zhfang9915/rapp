package org.rapp.config;

import org.rapp.interceptor.RoleAuthInterceptor;
import org.rapp.interceptor.SessionTimeOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author 张芳 Spring MVC 配置类
 */
@Configuration // 表面是配置类
@EnableWebMvc // 启用Spring MVC
@ComponentScan(basePackages = { "org.rapp.controller" }) // 启用组件扫描，使注解可用
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 配置视图解析器
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/"); // 视图路径前缀
		viewResolver.setSuffix(".jsp"); // 视图后缀】
		// viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		return viewResolver;
	}

	/**
	 * 配置启用servlet3.0基础的解析multipart请求
	 * 
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		// StandardServletMultipartResolver 强制要求配置临时文件目录
//		return new CustomMultipartResolver();
		return new StandardServletMultipartResolver();
	}

	/**
	 * 配置静态资源的处理
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 配置过滤器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionTimeOutInterceptor())
			.excludePathPatterns("/login/*", "/base/*", "/authentication/**", "/index");
		registry.addInterceptor(new RoleAuthInterceptor());
	}

}
