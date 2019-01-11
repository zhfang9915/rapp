package org.rapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: ApiLogAscept 
 * @Description: TODO
 * @author zhfang
 *  
 */
@Aspect
@Component
public class ApiLogAscept {
	
	private Logger logger = LoggerFactory.getLogger(ApiLogAscept.class);
	

	@Pointcut("execution(* org.rapp.controller.*.*(..))")
	public void pointCutMethed() {}
	
	@Around("pointCutMethed()")
	public Object ApiLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("--------------------");
		Object result = null;
//		Stopwatch stopwatch = Stopwatch.createStarted();
		try {
			result = joinPoint.proceed();	//方法执行
		} catch (Throwable e) {
			throw e;
		} finally {
		}
		return result;
	}
	
}
