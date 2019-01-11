package org.rapp.service;

import java.util.List;

import org.rapp.pojo.dto.param.PluginUnitParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.PluginUnit;

/**
 * 插件服务接口
 * @author 张芳
 *
 */
public interface PluginUnitService {

	/**
	 * 查询插件列表信息
	 * @param params
	 * @return
	 */
	TableResult<PluginUnit> queryPluginUnits(PluginUnitParam param);
	
	/**
	 * 根据ID获取插件
	 * @param puId
	 * @return
	 */
	BaseResult<PluginUnit> queryPluginUnitById(String puId);
	
	/**
	 * 根据固件查询插件
	 * @param fwId
	 * @return
	 */
	BaseResult<PluginUnit> queryPluginUnitByCrossstool(String crossstool);
	
	/**
	 * 根据插件的md5值查询插件
	 * @param md5
	 * @return
	 */
	BaseResult<PluginUnit> queryPluginUnitByMD5(String md5);
	
	/**
	 * 查询所有的插件信息
	 * @return
	 */
	BaseResult<List<PluginUnit>> queryPluginUnitAll();
	
	/**
	 * 新增插件
	 * @param pu
	 * @return
	 */
	BaseResult<PluginUnit> addPluginUnit(PluginUnit pu);
	
	/**
	 * 删除插件
	 * @param pu
	 * @return
	 */
	BaseResult<String> removePluginUnit(List<PluginUnit> pus);
	
	/**
	 * 更新插件
	 * @param pu
	 * @return
	 */
	BaseResult<PluginUnit> editPluginUnit(PluginUnit pu);
}
