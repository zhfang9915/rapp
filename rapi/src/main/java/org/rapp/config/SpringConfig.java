package org.rapp.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author 张芳
 * Spring IOC 配置类
 */
@Configuration
@ComponentScan(basePackages = {"org.rapp"},
		excludeFilters = {
				@Filter(type = FilterType.ANNOTATION, value = Controller.class)
		})	//启用spring组件扫描,并排除webConfig中的注解
@PropertySource({"classpath:jdbc.properties","classpath:mail.properties"})
@EnableTransactionManagement //配置基于注解的声明式事务默认使用注解来管理事务行为
@EnableAsync	//启用spring异步调用
@EnableAspectJAutoProxy(proxyTargetClass = true) //指定使用CGLIB代理，并开启  
@ImportResource(locations={"classpath:/spring-configuration/applicationContext-dataSource.xml"})
public class SpringConfig implements EnvironmentAware {
	
	private Environment env;
	
	
	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

	
	/**
	 * 注册邮件发送的bean
	 * @return
	 */
	@Bean
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mail.host"));//邮件服务器主机名
		mailSender.setPort(env.getProperty("mail.port", Integer.class));//邮件服务器STMP端口
		mailSender.setUsername(env.getProperty("mail.username"));//邮箱用户名
		mailSender.setPassword(env.getProperty("mail.password"));//邮箱密码
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailSender.setJavaMailProperties(properties);
		return mailSender;
	}
	
	/**
	 * 配置数据源
	 * @return
	 * @throws PropertyVetoException
	 */
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			//数据库链接驱动
			dataSource.setDriverClass(env.getProperty("jdbc.driverClass"));
			//数据库链接地址
			dataSource.setJdbcUrl(env.getProperty("jdbc.jdbcUrl"));
			//数据库用户名
			dataSource.setUser(env.getProperty("jdbc.user"));
			//数据库连接密码
			dataSource.setPassword(env.getProperty("jdbc.password"));
			dataSource.setInitialPoolSize(env.getProperty("jdbc.initialPoolSize", Integer.class));
			//连接池最大连接数
			dataSource.setMaxPoolSize(env.getProperty("jdbc.maxPoolSize", Integer.class));
			//连接池最小连接数
			dataSource.setMinPoolSize(env.getProperty("jdbc.minPoolSize", Integer.class));
			dataSource.setMaxIdleTime(env.getProperty("jdbc.maxIdleTime", Integer.class));
			dataSource.setIdleConnectionTestPeriod(env.getProperty("jdbc.idleConnectionTestPeriod", Integer.class));
			dataSource.setPreferredTestQuery(env.getProperty("jdbc.preferredTestQuery"));
			dataSource.setAcquireIncrement(env.getProperty("jdbc.acquireIncrement", Integer.class));
			//获取连接重试次数
			dataSource.setAcquireRetryAttempts(env.getProperty("jdbc.acquireRetryAttempts", Integer.class));
			dataSource.setAcquireRetryDelay(env.getProperty("jdbc.acquireRetryDelay", Integer.class));
			dataSource.setBreakAfterAcquireFailure(env.getProperty("jdbc.breakAfterAcquireFailure", Boolean.class));
			//获取连接超时时间
			dataSource.setCheckoutTimeout(env.getProperty("jdbc.checkoutTimeout", Integer.class));
			//关闭连接后不自动commit
			dataSource.setAutoCommitOnClose(env.getProperty("jdbc.autoCommitOnClose", Boolean.class));
			dataSource.setForceIgnoreUnresolvedTransactions(env.getProperty("jdbc.forceIgnoreUnresolvedTransactions", Boolean.class));
			dataSource.setUnreturnedConnectionTimeout(env.getProperty("jdbc.unreturnedConnectionTimeout", Integer.class));
			dataSource.setMaxStatements(env.getProperty("jdbc.maxStatements", Integer.class));
			dataSource.setMaxStatementsPerConnection(env.getProperty("jdbc.maxStatementsPerConnection", Integer.class));
			dataSource.setTestConnectionOnCheckin(env.getProperty("jdbc.testConnectionOnCheckin", Boolean.class));
			dataSource.setTestConnectionOnCheckout(env.getProperty("jdbc.testConnectionOnCheckout", Boolean.class));
			dataSource.setUsesTraditionalReflectiveProxies(env.getProperty("jdbc.usesTraditionalReflectiveProxies", Boolean.class));
			dataSource.setNumHelperThreads(env.getProperty("jdbc.numHelperThreads", Integer.class));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	/**
	 * 配置事务管理器
	 * @return
	 */
	@Bean
	@Autowired//必须使用自动装配，否则事务管理的不是同一个数据源
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		//注入数据库连接池
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
	
	/**
	 * 配置扫描Dao接口包,动态实现DAO接口,注入到spring容器
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		//根据 Bean 名称注入 SqlSessionFactory
		configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		//需要扫描的Dao接口
		configurer.setBasePackage("org.rapp.repository");
		return configurer;
	}
	
	/**
	 * 启用 @value 获取属性文件值
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
