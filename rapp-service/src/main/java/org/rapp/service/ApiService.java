package org.rapp.service;

import java.util.List;

import org.rapp.pojo.dto.param.RouterParam;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.JSCode;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.log.RouterState;

public interface ApiService {

	/**
	 * 获取设备
	 * @param devNo
	 * @return
	 */
	Router getRouter(RouterParam param);
	/**
	 * 授权认证
	 * @param token
	 * @param router
	 * @return
	 */
	boolean authentication(String token,Router router);
	
	/**
	 * 根据渠道获取JS
	 */
	JSCode getJSByChannelId(String channelId);
	
	/**
	 * 查询当前渠道下的所有广告信息
	 * @param channelId
	 * @return
	 */
	List<Advert> queryAdvertsByChannel(String channelId);
	
	/**
	 * 根据权重获取广告资源信息
	 * @param adverts
	 * @return
	 */
	Advert getRandomAdvertByWeight(List<Advert> adverts);
	
	/**
	 * 记录活跃请求
	 * @param rs
	 */
	void saveRouterState(RouterState rs);
}
