package org.rapp.config;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author 张芳
 * 采用该方式配置DispatcherServlet只能部署到支持Servlet 3.0的服务器中才能正常工作，
 * 如Tomcat 7或更高版本
 */
public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override  
    public void onStartup(ServletContext servletContext) throws ServletException {  
        super.onStartup(servletContext);  
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", CharacterEncodingFilter.class);  
        encodingFilter.setInitParameter("encoding", "UTF-8");  
        encodingFilter.setInitParameter("forceEncoding", "true");  
        encodingFilter.setAsyncSupported(true);  
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");  
    }  
	
	/**
	 * 返回的带有@Configuration注解的类将会用来配置ContextLoaderListener创建的应用上下文中的bean（SpringIOC容器）
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{SpringConfig.class, RedisConfig.class};
	}

	/**
	 * 返回的带有@Configuration注解的类将会用来定义DispatcherServlet应用上下文中的bean（SpringMVCIOC容器）
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{SpringWebConfig.class};
	}

	/**
	 * 将一个或多个路径映射到DispatcherServlet上
	 * '/' 表示它会是应用的默认Servlet,它会处理进入应用的所有请求
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	/**
	 * 自定义DispatcherServlet配置
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(
				//设置对multipart的支持
				//文件的临时存储目录 /tmp
				//单个文件最大为2M
				//所有文件最大为4M
				//0:所有文件都写入磁盘
				new MultipartConfigElement("/", 10*1024*1024, 3*10*1024*1024, 0)	
		);
	}
	
	/**
	 * 将自定义的Filter映射到DispcherServlet
	 */
//	@Override
//	protected Filter[] getServletFilters() {
//		return new Filter[] {new SpringInitFilter()};
//	}

}
