package org.rapp.config;

import org.rapp.comm.redis.cache.RedisCacheTrans;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;

import redis.clients.jedis.JedisPoolConfig;

//@EnableRedisHttpSession(maxInactiveIntervalInSeconds=900, redisNamespace="Rapp_Session")
@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig implements EnvironmentAware {
	
	Environment env;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
	
	/**
     * 配置 redis 数据源连接池
     */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		poolConfig.setBlockWhenExhausted(env.getProperty("redis.jedisPoolConfig.blockWhenExhausted", Boolean.class));
		
		//是否启用pool的jmx管理功能, 默认true
		poolConfig.setJmxEnabled(env.getProperty("redis.jedisPoolConfig.jmxEnabled", Boolean.class));
		
		//默认就好
        //jedisPoolConfig.setJmxNamePrefix("pool");
		
        //jedis调用returnObject方法时，是否进行有效检查
		poolConfig.setTestOnReturn(env.getProperty("redis.jedisPoolConfig.testOnReturn", Boolean.class));
		
        //是否启用后进先出, 默认true
		poolConfig.setLifo(env.getProperty("redis.jedisPoolConfig.lifo", Boolean.class));
		
        //最大空闲连接数, 默认8个
		poolConfig.setMaxIdle(env.getProperty("redis.jedisPoolConfig.maxIdle", Integer.class));
		
        //最大连接数, 默认8个
		poolConfig.setMaxTotal(env.getProperty("redis.jedisPoolConfig.maxTotal", Integer.class));
		
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
		poolConfig.setMaxWaitMillis(env.getProperty("redis.jedisPoolConfig.maxWaitMillis", Integer.class));
		
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		poolConfig.setMinEvictableIdleTimeMillis(env.getProperty("redis.jedisPoolConfig.minEvictableIdleTimeMillis", Integer.class));
        
		//最小空闲连接数, 默认0
		poolConfig.setMinIdle(env.getProperty("redis.jedisPoolConfig.minIdle", Integer.class));
		
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		poolConfig.setNumTestsPerEvictionRun(env.getProperty("redis.jedisPoolConfig.mumTestsPerEvictionRun", Integer.class));
		
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
		poolConfig.setSoftMinEvictableIdleTimeMillis(env.getProperty("redis.jedisPoolConfig.softMinEvictableIdleTimeMillis", Integer.class));
		
        //在获取连接的时候检查有效性, 默认false
		poolConfig.setTestOnBorrow(env.getProperty("redis.jedisPoolConfig.testOnBorrow", Boolean.class));
        
		//在空闲时检查有效性, 默认false
		poolConfig.setTestWhileIdle(env.getProperty("redis.jedisPoolConfig.testWhileIdle", Boolean.class));
        
		//逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		poolConfig.setTimeBetweenEvictionRunsMillis(env.getProperty("redis.jedisPoolConfig.timeBetweenEvictionRunsMillis", Integer.class));

		return poolConfig;
	}
	
	
	/**
	 * Spring-redis连接池管理工厂
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig());
		factory.setHostName(env.getProperty("redis.host"));
		factory.setPort(env.getProperty("redis.port", Integer.class));
		factory.setPassword(env.getProperty("redis.password"));
		return factory;
	}
	
	/**
	 * 配置 redis 作为 mybatis 的缓存数据库
	 */
	@Bean
	public RedisCacheTrans redisCacheTrans() {
		RedisCacheTrans redisCacheTrans = new RedisCacheTrans();
		redisCacheTrans.setJedisConnectionFactory(jedisConnectionFactory());
		return redisCacheTrans;
		
	}
	
	/**
	 * 注入CustomerCookieSerializer到CookieHttpSessionStrategy中
	 * @return
	 */
	@Bean
	public DefaultCookieSerializer cookieSerializer() {
		DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
		//jdk1.8以后正确格式"com.cn"，在jdk1.8以前正确格式".com.cn"
		cookieSerializer.setDomainName("qcwifi.ltd");
		cookieSerializer.setCookieName("JSESSIONID");
		return cookieSerializer;
	}

}
