package org.rapp.service;

import java.util.List;

import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.AdvertPushSearch;
import org.rapp.pojo.dto.param.api.AdvertPushParam;
import org.rapp.pojo.dto.result.ApiResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.AdvertHistoryLog;
import org.rapp.pojo.entry.AdvertLog;
import org.rapp.pojo.log.AdvertPushLog;

/**
 * 广告流水
 * 
 * @author 张芳
 *
 */
public interface AdvertLogService {
	
	/**
	 * 上传广告流水
	 * @param log
	 * @return
	 */
	ApiResult<Integer> uploadAdvertLog(List<AdvertLog> logs);
	
	/**
	 * 广告推送记录
	 * @param log
	 * @return
	 */
	ApiResult<Integer> advertPushLog(List<AdvertPushLog> logs);

	/**
	 * 将广告流水放入队列
	 * @param log
	 */
	void offer(AdvertLog log);
	/**
	 * 将推送记录放入队列
	 * @param log
	 */
	AdvertPushLog offer(AdvertPushParam param, String advertId);
	/**
	 * 从队列里面获取广告流水并保存到数据库
	 */
	void poll();

	/**
	 * 查询广告流水
	 * @param user
	 * @param search
	 * @return
	 */
	TableResult<AdvertPushLog> queryPushLog4Table(User user, AdvertPushSearch search);
	
	/**
	 * 查询推送历史
	 * @param user
	 * @param param
	 * @return
	 */
	TableResult<AdvertHistoryLog> queryAdvertHistory(User user, AdvertPushSearch param);


}
