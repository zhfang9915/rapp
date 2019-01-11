package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.RouterParam;
import org.rapp.pojo.entry.Router;

public interface RouterDao {

	/**
	 * 根据设备ID查询设备信息
	 * 
	 * @param devNo
	 * @return
	 */
	Router queryRouterByDevNo(RouterParam param);
	
	Router queryRouterByMac(@Param("mac") String mac);

	/**
	 * 插入设备信息
	 * 
	 * @param router
	 * @return
	 */
	int insertRouter(Router router);
	
	/**
	 * 条件查询设备并展示在table中
	 * @param param
	 * @return
	 */
	List<Router> queryRouter4Table(RouterParam param);
	int queryRouter4TableCount(RouterParam param);
	
	/**
	 * 查询为绑定渠道的设备
	 * @param createBy
	 * @return
	 */
	List<Router> queryFreeRouter(RouterParam param);
	int queryFreeRouterCount(RouterParam param);
	
	/**
	 * 根据设备编码查询设备
	 * @param devNo
	 * @return
	 */
	Router queryRouterById(@Param("devNo")String devNo);
	
	/**
	 * 根据mac地址更新设备编码
	 * @param devNo
	 * @param mac
	 * @return
	 */
	int updateRouterByMac(@Param("devNo")String devNo, @Param("mac")String mac);
	
	/**
	 * 删除设备
	 * @param routers
	 * @return
	 */
	int deleteRouterByMac(@Param("routers")List<Router> routers, @Param("createBy")String createBy);
	int deleteRouterByNo(@Param("devNo")String devNo, @Param("createBy")String createBy);
	
	/**
	 * 更新设备的渠道
	 * @param routers
	 * @param channelId
	 * @return
	 */
	int updateRouterChannel(@Param("routers")List<Router> routers, @Param("channelId")String channelId);
	int updateRouterChannelByNo(@Param("devNo")String devNo, @Param("channelId")String channelId);
	
	/**
	 * 接触绑定
	 * @param devNo
	 * @param channelId
	 * @return
	 */
	int removeRouterChannel(@Param("routers")List<Router> routers, @Param("channelId")String channelId);
	
	/**
	 * 更新设备的地址信息
	 * @param router
	 * @return
	 */
	int updateRouterAddress(Router router);
	
	/**
	 * 更新固件版本信息
	 * @param router
	 * @return
	 */
	int updateRouterFirmware(Router router);
	
	/**
	 * 查询名下的设备总数
	 * @param createBy
	 * @return
	 */
	int queryUserRouterCount(@Param("createBy")String createBy);
	/**
	 * 查询激活的设备
	 * @param createBy
	 * @return
	 */
	int queryActiceRouterCount(@Param("createBy")String createBy);
}
