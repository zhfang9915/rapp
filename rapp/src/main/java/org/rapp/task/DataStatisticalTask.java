//package org.rapp.task;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataStatisticalTask {
//	
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Scheduled(cron="0 0/1 *  * * ? ")   //每10秒执行一次
//    public void doTask() {
//		logger.info("rapp Do Task...");
//    }
//}
