package org.rapp.service.poll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.rapp.pojo.entry.AdvertLog;
import org.rapp.service.AdvertLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 广告流水同步
 * @author 张芳
 *
 */
public class AdvertLogPoll implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private	AdvertLogService advertLogService;
	
	private ConcurrentLinkedQueue<AdvertLog> queue;
	
	
	public AdvertLogPoll(ConcurrentLinkedQueue<AdvertLog> queue, AdvertLogService advertLogService) {
		this.queue = queue;
		this.advertLogService = advertLogService;
	}
	
	/**
	 * 执行同步
	 */
	public void run() {
		logger.debug("同步请求流水：" + Thread.currentThread().getName());
    	List<AdvertLog> logs = new ArrayList<>();
		while (!queue.isEmpty()) {
			AdvertLog log = queue.poll();
			logs.add(log);
        }
		logger.info("更新上传流水logs个数{}", logs.size());
		if (!logs.isEmpty()) {
			advertLogService.uploadAdvertLog(logs);
		}
    }
}
