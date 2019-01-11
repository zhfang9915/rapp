package org.rapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置
 * @author 张芳
 *
 */
@Configuration
@EnableScheduling	//启用定时任务
@ComponentScan(basePackages="org.rapp.task")
public class SpringScheduledConfig {

	
	
}
