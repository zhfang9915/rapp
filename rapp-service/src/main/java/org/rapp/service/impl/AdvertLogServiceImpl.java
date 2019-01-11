package org.rapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.rapp.comm.util.CalendarUtil;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.AdvertPushSearch;
import org.rapp.pojo.dto.param.api.AdvertPushParam;
import org.rapp.pojo.dto.result.ApiResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.AdvertHistoryLog;
import org.rapp.pojo.entry.AdvertLog;
import org.rapp.pojo.log.AdvertPushLog;
import org.rapp.repository.AdvertLogDao;
import org.rapp.service.AdvertLogService;
import org.rapp.service.BaseService;
import org.rapp.service.poll.AdvertLogPoll;
import org.rapp.service.poll.AdvertPushLogPoll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertLogServiceImpl implements AdvertLogService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired AdvertLogDao advertLogDao;
	
	@Autowired BaseService service;

	/**
	 * 生成广告流水ID
	 * @return
	 */
	private String getLogId(){
		String now = CalendarUtil.parseLongTime(new Date());
		int random = (int)((Math.random()*9+1)*1000000);
		return now + "0000" + random;
	}
	
	@Override
	public ApiResult<Integer> uploadAdvertLog(List<AdvertLog> logs) {
		
		try {
			logger.info("同步上传流水数量：" + logs.size());
			int count = advertLogDao.insertAdvertLog(logs);
			logger.info("同步上传流水成功总数：" + count);
			if (count == 0) {
				return new ApiResult<Integer>("001", "保存流水失败");
			}
			return new ApiResult<Integer>("000", "成功");
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ApiResult<Integer>("999", "系统异常");
		}
	}
	
	@Override
	public ApiResult<Integer> advertPushLog(List<AdvertPushLog> logs) {
		
		try {
			logger.info("同步推送数量：" + logs.size());
			int count = advertLogDao.insertAdvertPushLog(logs);
			logger.info("同步推送成功总数：" + count);
			if (count == 0) {
				return new ApiResult<Integer>("001", "保存推送记录失败");
			}
			return new ApiResult<Integer>("000", "成功");
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ApiResult<Integer>("999", "系统异常");
		}
	}
	
	private static ConcurrentLinkedQueue<AdvertLog> uploadLogQueue = new ConcurrentLinkedQueue<AdvertLog>();

	private static ConcurrentLinkedQueue<AdvertPushLog> pushQueue = new ConcurrentLinkedQueue<AdvertPushLog>();
	
	@Override
	public void offer(AdvertLog log) {
		log.setId(getLogId());
		logger.info(log.toString());
		uploadLogQueue.offer(log);

	}
	
	
	@Override
	public AdvertPushLog offer(AdvertPushParam param, String advertId) {
		AdvertPushLog log = new AdvertPushLog();
		log.setId(getLogId());
		log.setDevNo(param.getDevNo());
		log.setAdvertId(advertId);
		log.setUrl(param.getUrl());
		log.setPushTime(new Date());
		log.setPhoneMac(param.getPhoneMac());
		pushQueue.offer(log);
		return log;
	}
	
	@Override
	public void poll() {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		try {
			pool.execute(new AdvertLogPoll(uploadLogQueue, this));//开启异步线程执行操作
			pool.execute(new AdvertPushLogPoll(pushQueue, this));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("线程调度异常-->" + e.getMessage());
		}finally {
			try {
				pool.shutdown();//关闭线程
				// 等待子线程结束，再继续执行下面的代码
				pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
				logger.info("pool isShutdown -->" + pool.isShutdown());
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.info("pool shutdown exception-->" + e.getMessage());
			}
		} 
	}
	
	
	@Override
	public TableResult<AdvertPushLog> queryPushLog4Table(User user, AdvertPushSearch search) {
		try {
			String createBy = null;
			if (user.getRole().getRoleId() > 1) {
				// 非管理员
				createBy = user.getUsername();
			}
			search.setCreateBy(createBy);
			List<AdvertPushLog> logs = advertLogDao.selectAdvertPushLog(search);
			int total = advertLogDao.selectAdvertPushLogCount(search);
			return new TableResult<>(total, logs);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new TableResult<>(0, new ArrayList<AdvertPushLog>());
		}
	}
	
	@Override
	public TableResult<AdvertHistoryLog> queryAdvertHistory(User user, AdvertPushSearch param) {
		try {
			param.setCreateBy(service.getCreateBy(user));
			List<AdvertHistoryLog> logs = advertLogDao.queryAdvertHistory(param);
			int total = advertLogDao.queryAdvertHistoryCount(param);
			return new TableResult<>(total, logs);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new TableResult<>(0, new ArrayList<AdvertHistoryLog>());
		}
	}
	
}
