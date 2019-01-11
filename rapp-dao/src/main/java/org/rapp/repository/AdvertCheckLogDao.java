package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.log.AdvertCheckLog;

public interface AdvertCheckLogDao {
	
	/**
	 * 插入审核日志(消息)
	 * @param log
	 * @return
	 */
	int insertCheckLog(AdvertCheckLog log);
	
	/**
	 * 查询当前用户的未读审核消息
	 * @param createBy
	 * @return
	 */
	int queryUnreadCount(@Param("createBy")String createBy);
	
	/**
	 * 更新阅读状态为2(已读)
	 * @param logs
	 * @return
	 */
	int updateReadState(@Param("logs")List<AdvertCheckLog> logs);
	
	/**
	 * 查询未读的广告审核信息
	 * @param reviewer
	 * @return
	 */
	List<AdvertCheckLog> queryUnreadCheckLog(@Param("reviewer")String reviewer);
}
