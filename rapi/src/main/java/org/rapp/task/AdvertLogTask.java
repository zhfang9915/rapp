package org.rapp.task;

import org.rapp.service.AdvertLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AdvertLogTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AdvertLogService logService;
	
	
	@Scheduled(cron="0 0/1 *  * * ? ")   //每10秒执行一次
    public void doTask() {
		logger.info("Do Task...");
    	logService.poll();
    }
}
