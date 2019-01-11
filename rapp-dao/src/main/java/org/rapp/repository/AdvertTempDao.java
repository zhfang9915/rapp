package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.AdvertTempParam;
import org.rapp.pojo.entry.AdvertTemp;
import org.rapp.pojo.entry.JSCode;

/**
 * 广告模版持久化
 * @author 张芳
 *
 */
public interface AdvertTempDao {
	
	/**
	 * 查询广告模版
	 * @return
	 */
	List<AdvertTemp> queryAdvertTemps(AdvertTempParam param);
	
	/**
	 * 查询广告模版总数
	 * @param param
	 * @return
	 */
	int queryAdvertTempsCount(AdvertTempParam param);
	
	/**
	 * 根据ID查询指定模板
	 * @param tempId
	 * @return
	 */
	AdvertTemp queryAdvertTempById(int tempId);
	
	/**
	 * 新增
	 * @param temp
	 * @return
	 */
	int insertAdvertTemp(AdvertTemp temp);
	
	/**
	 * 删除
	 * @param code
	 * @return
	 */
	int deleteAdvertTemp(@Param("list")List<AdvertTemp> temps);
	
	/**
	 * 更新
	 * @param code
	 * @return
	 */
	int updateAdvertTemp(AdvertTemp temp);
}
