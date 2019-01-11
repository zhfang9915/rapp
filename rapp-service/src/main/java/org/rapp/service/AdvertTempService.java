package org.rapp.service;

import java.util.List;
import java.util.Map;

import org.rapp.pojo.dto.param.AdvertTempParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.AdvertTemp;
import org.rapp.pojo.entry.JSCode;

/**
 * 广告模版服务接口
 * 
 * @author 张芳
 *
 */
public interface AdvertTempService {
	
	/**
	 * 查询所有的广告模版
	 * @author 许延明
	 * @return
	 */
	public TableResult<AdvertTemp> queryAdvertTemps(AdvertTempParam param);
	
	/**
	 * 查询活动的广告模版
	 * @return
	 */
	List<AdvertTemp> queryActiveTemp();
	
	
	/**
	 * 新增广告模版
	 * @author 许延明
	 * @return
	 */
	BaseResult<AdvertTemp> addAdvertTemp(AdvertTemp temp);
	
	/**
	 * 编辑广告模版
	 * @author 许延明
	 * @return
	 */
	BaseResult<AdvertTemp> editAdvertTemp(AdvertTemp temp);
	
	/**
	 * 删除广告模版
	 * @author 许延明
	 * @return
	 */
	BaseResult<String> removeAdvertTemp(List<AdvertTemp> temps);
	
	/**
	 * 根据模板ID查询模板
	 * @param tempId
	 * @return
	 */
	BaseResult<AdvertTemp> queryAdvertTempById(int tempId);
	
	/**
	 * 生成广告模版显示html
	 * @param advert
	 * @param logid
	 * @return
	 */
	Map<String, String> createAdvertTemplate(Advert advert, String logid);


}
