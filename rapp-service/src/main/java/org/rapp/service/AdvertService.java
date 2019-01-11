package org.rapp.service;

import java.util.List;

import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.AdvertParam;
import org.rapp.pojo.dto.param.PushDetailParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.PushDetail;

/**
 * 广告配合服务接口
 * 
 * @author 张芳
 *
 */
public interface AdvertService {
	
	/**
	 * 获取UUID
	 * @return
	 */
	BaseResult<String> getUUID();

	/**
	 * 广告配置
	 * 
	 * @param advert
	 * @return
	 */
	BaseResult<Advert> configurationAdvert(Advert advert);

	/**
	 * 表格展示广告列表信息
	 * 
	 * @param param
	 * @return
	 */
	TableResult<Advert> queryAdvertForTable(AdvertParam param);

	/**
	 * 表格展示广告列表信息
	 * 
	 * @param param
	 * @return
	 */
	BaseResult<List<Advert>> queryAdvertForList(AdvertParam param);

	/**
	 * 删除或批量删除广告配置信息
	 * 
	 * @param adverts
	 * @return
	 */
	BaseResult<String> deleteAdvertConfig(List<Advert> adverts);

	/**
	 * 更新广告配置信息
	 * 
	 * @param advert
	 * @return
	 */
	BaseResult<Advert> updateAdvertConfig(Advert advert);

	/**
	 * 根据ID查询广告信息
	 * 
	 * @param advertId
	 * @return
	 */
	BaseResult<Advert> queryAdvertById(String advertId, User user);

	/**
	 * 查询名下已有的广告总数
	 * 
	 * @param username
	 * @return
	 */
	int totalAdvertExist(String username);

	/**
	 * 根据广告信息获取推送明细
	 */
	TableResult<PushDetail> queryPushDetailByAdvert(PushDetailParam param);


}
