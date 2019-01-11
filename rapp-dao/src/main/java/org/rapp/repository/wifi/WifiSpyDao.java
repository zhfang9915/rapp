package org.rapp.repository.wifi;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.BaseTableParam;
import org.rapp.pojo.dto.param.WifiSpyParam;
import org.rapp.pojo.wifi.FrameData;
import org.rapp.pojo.wifi.WifiSpy;

/**
 * WIFI侦探持久化
 * @author 张芳
 *
 */
public interface WifiSpyDao {

	/**
	 * 保存WIFI侦探路由器信息
	 * @param spy
	 * @return
	 */
	int saveSpy(WifiSpy spy);
	
	/**
	 * 保存全部的WIFI侦探携带的客户信息
	 * @param spy
	 * @return
	 */
	int saveSpyDatas(@Param("spyId")String spyId, @Param("list")List<FrameData> datas);
	
	/**
	 * 查询WIFI侦探路由器信息
	 * @param spy
	 * @return
	 */
	List<WifiSpy> querySpy(WifiSpyParam spy);
	/**
	 * 查询WIFI侦探路由器信息总数
	 * @param spy
	 * @return
	 */
	int querySpyCount(WifiSpyParam spy);
	
	/**
	 * 根据路由器查询该路由器不活的用户信息
	 * @param spyId
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<FrameData> querySpyDataBySpyId(@Param("spyId")String spyId, @Param("limit")String limit, @Param("offset")String offset);
	/**
	 * 根据路由器查询该路由器不活的用户信息总数
	 * @param spyId
	 * @return
	 */
	int querySpyDataBySpyIdCount(@Param("spyId")String spyId);
}
