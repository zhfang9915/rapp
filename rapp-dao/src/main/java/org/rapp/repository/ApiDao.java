package org.rapp.repository;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.log.RouterState;

public interface ApiDao {
	
	/**
	 * 在线
	 */
	public static final int ONLINE = 1;
	
	/**
	 * 活跃
	 */
	public static final int ACTIVE = 2;

	/**
	 * 插入设备状态
	 * @param rs
	 * @return
	 */
	int insertRouterState(RouterState rs);
	
	/**
	 * 统计在线或者活跃设备
	 * @return
	 */
	Integer statisticalOnlineOrActive(
			@Param("statisticalDate")String statisticalDate, 
			@Param("type")int type, 
			@Param("createBy")String createBy);
}
