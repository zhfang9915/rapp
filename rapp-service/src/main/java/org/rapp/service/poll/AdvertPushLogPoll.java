package org.rapp.service.poll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.rapp.pojo.log.AdvertPushLog;
import org.rapp.service.AdvertLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 广告流水同步
 * @author 张芳
 *
 */
public class AdvertPushLogPoll implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private	AdvertLogService advertLogService;
	
	private ConcurrentLinkedQueue<AdvertPushLog> queue;
	
	
	public AdvertPushLogPoll(ConcurrentLinkedQueue<AdvertPushLog> queue, AdvertLogService advertLogService) {
		this.queue = queue;
		this.advertLogService = advertLogService;
	}
	
	/**
	 * 执行同步
	 */
	public void run() {
		logger.debug("同步推送日志：" + Thread.currentThread().getName());
    	List<AdvertPushLog> logs = new ArrayList<>();
		while (!queue.isEmpty()) {
			AdvertPushLog log = queue.poll();
			logs.add(log);
        }
		if (!logs.isEmpty()) {
			advertLogService.advertPushLog(logs);
		}
    }
}
