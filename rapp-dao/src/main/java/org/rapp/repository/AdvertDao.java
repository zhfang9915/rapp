package org.rapp.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.AdvertParam;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.AdvertItem;
import org.rapp.pojo.entry.AdvertTemp;

public interface AdvertDao {
	
	/**
	 * 获取主键
	 * @return
	 */
	String getUUID();

	/**
	 * 配置广告信息
	 * @param advert
	 * @return
	 */
	int insertAdvert(Advert advert);
	/**
	 * 插入广告明细
	 * @param items
	 * @return
	 */
	int insertAdvertItem(@Param("items")List<AdvertItem> items);
	/**
	 * 配置渠道和广告的对应关系
	 * @param advertId
	 * @param channelId
	 * @return
	 */
	int insertAdvertChannel(@Param("advertId")String advertId, @Param("channels")Object[] channels);
	
	/**
	 * 列表展示广告信息
	 * @param param
	 * @return
	 */
	List<Advert> listAdverts(AdvertParam param);
	
	/**
	 * 表格展示广告信息总数
	 * @param param
	 * @return
	 */
	List<Advert> tableAdverts(AdvertParam param);
	/**
	 * 表格展示广告信息总数
	 * @param param
	 * @return
	 */
	int tableAdvertsCount(AdvertParam param);
	
	/**
	 * 删除或批量删除广告配置信息
	 * @param adverts
	 * @return
	 */
	int deleteAdvert(@Param("adverts")List<Advert> adverts);
	
	/**
	 * 批量删除广告明细信息
	 * @param adverts
	 * @return
	 */
	int deleteAdvertItem(@Param("adverts")List<Advert> adverts);
	
	/**
	 * 删除广告渠道信息表
	 * @param adverts
	 * @return
	 */
	int deleteAdvertChannel(@Param("advertId")String advertId, @Param("channelIds")Collection<String> channelIds);
	
	/**
	 * 根据广告ID 删除广告渠道信息
	 * @param advertId
	 * @return
	 */
	int deleteAdvertChannelByAdvertId(@Param("adverts")List<Advert> adverts);
	
	/**
	 * 更新广告配置信息
	 * @param advert
	 * @return
	 */
	int updateAdvert(Advert advert);
	
	/**
	 * 根据广告ID获取广告所属的渠道IDs
	 * @param advertId
	 * @return
	 */
	List<String> queryAdvertChannelIds(@Param("advertId")String advertId);
	
	/**
	 * 根据ID查询广告
	 * @param advertId
	 * @return
	 */
	Advert queryAdvertById(@Param("advertId")String advertId, @Param("createBy")String createBy);
	
	/**
	 * 查询名下已有的广告总数
	 * @param createBy
	 * @return
	 */
	int totalAdvertExist(@Param("createBy")String createBy);
	
	/**
	 * 更新广告状态
	 * @param advertId
	 * @param state
	 * @return
	 */
	int updateState(@Param("advertId")String advertId, @Param("state")String state);
	
	/**
	 * 查询指定渠道下的所有广告资源信息
	 * @param channelId
	 * @param nowDate
	 * @return
	 */
	List<Advert> queryAdvertsByChannelId(@Param("channelId")String channelId, @Param("nowDate")String nowDate);
	
	/**
	 * 查询名下的广告总数
	 * @param createBy
	 * @return
	 */
	int queryUserAdvertsCount(@Param("createBy")String createBy);
	
	/**
	 * 查询广告模版
	 * @return
	 */
	List<AdvertTemp> selectActiveAdvertTemps();
}
