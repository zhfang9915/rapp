package org.rapp.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.AdvertPushSearch;
import org.rapp.pojo.dto.param.PushDetailParam;
import org.rapp.pojo.entry.AdvertHistoryLog;
import org.rapp.pojo.entry.AdvertLog;
import org.rapp.pojo.entry.PushDetail;
import org.rapp.pojo.log.AdvertPushLog;

public interface AdvertLogDao {

	/**
	 * 保存广告流水
	 * @param log
	 * @return
	 */
	int insertAdvertLog(@Param("logs")List<AdvertLog> logs);
	
	int insertAdvertPushLog(@Param("logs")List<AdvertPushLog> logs);
	
	/**
	 * 查询推送日志
	 * @param search
	 * @return
	 */
	List<AdvertPushLog> selectAdvertPushLog(AdvertPushSearch search);
	int selectAdvertPushLogCount(AdvertPushSearch search);
	
	
	List<AdvertLog> selectAdvertLog();
	
	
	/**
	 * 根据条件查询推送明细
	 * @return
	 */
	List<PushDetail> queryPushDetailByAdvert(PushDetailParam param);
	int queryPushDetailByAdvertCount(PushDetailParam param);
	
	/**
	 * 查询推送流水历史
	 * @param param
	 * @return
	 */
	List<AdvertHistoryLog> queryAdvertHistory(AdvertPushSearch param);
	int queryAdvertHistoryCount(AdvertPushSearch param);
	
	/**
	 * 统计日推送量
	 * @param type
	 * @param pushTime
	 * @param createBy
	 * @return
	 */
	Map<String, Long> totalPushAtDay(@Param("pushTime")String pushTime, @Param("createBy")String createBy);
}
