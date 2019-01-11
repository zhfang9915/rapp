package org.rapp.comm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 无需登录的方法注解
 * @author 张芳
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)
public @interface NotLogin {

	
}
