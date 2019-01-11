package org.rapp.service;

import java.util.List;
import java.util.Map;

import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.log.AdvertCheckLog;

/**
 * 广告配合服务接口
 * @author 张芳
 *
 */
public interface AdvertCheckService {

	
	/**
	 * 审核广告
	 * @param advert
	 * @param user
	 * @return
	 */
	BaseResult<String> checkAdvert(Map<String, Object> params);
	
	/**
	 * 启用/禁用广告
	 * @param advertId
	 * @param state
	 * @return
	 */
	BaseResult<String> enableOrDisableAdvert(String advertId, String state);
	
	/**
	 * 查询名下的未读消息数
	 * @param username
	 * @return
	 */
	int totalUnreadAdvertMsg(String username);

	/**
	 * 查询未审核的广告列表
	 * @return
	 */
	int tableUncheckAdverts();
	
	/**
	 * 查询未读的广告信息
	 * @param username
	 * @return
	 */
	BaseResult<List<AdvertCheckLog>> queryUnreadLog(String username);
}
