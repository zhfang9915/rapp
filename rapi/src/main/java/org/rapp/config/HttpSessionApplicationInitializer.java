//package org.rapp.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
//import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
//
///**
// * 加载RedisHttpSessionConfiguration配置文件
// * 定义springSessionRepositoryFilter拦截所有的请求将session封装为spring session
// * @author 张芳
// */
//public class HttpSessionApplicationInitializer extends AbstractHttpSessionApplicationInitializer {
//	
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	//使用构造方法由于容器的继承关系会导致报错
////    public HttpSessionApplicationInitializer() {
////        super(RedisConfig.class);
////    }
//
//	@Override
//    protected String getDispatcherWebApplicationContextSuffix(){
//		logger.info("加载RedisHttpSessionConfiguration配置文件");
//        return AbstractDispatcherServletInitializer.DEFAULT_SERVLET_NAME; //这里返回的字符串就是配置DispatcherServlet的名称
//    }
//
//}
