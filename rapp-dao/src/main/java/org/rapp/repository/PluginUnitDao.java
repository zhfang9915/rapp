package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.PluginUnitParam;
import org.rapp.pojo.entry.PluginUnit;

/**
 * 插件持久化接口
 * @author 张芳
 *
 */
public interface PluginUnitDao {

	/**
	 * 查询插件列表信息
	 * @param params
	 * @return
	 */
	List<PluginUnit> listPluginUnits(PluginUnitParam param);
	
	/**
	 * 插件列表总数
	 * @param params
	 * @return
	 */
	int countPluginUnits(PluginUnitParam param);
	
	/**
	 * 根据ID获取插件
	 * @param puId
	 * @return
	 */
	PluginUnit queryPluginUnitById(@Param("pluginId")String pluginId);
	
	/**
	 * 根据固件获取插件
	 * @param puId
	 * @return
	 */
	PluginUnit queryPluginUnitByCrossstool(@Param("crossstool")String crossstool);
	
	/**
	 * 根据插件的md5值查询插件
	 * @param md5
	 * @return
	 */
	PluginUnit queryPluginUnitByMD5(@Param("md5")String md5);
	
	/**
	 * 查询所有的JS代码信息
	 * @return
	 */
	List<PluginUnit> queryPluginUnitAll();
	
	/**
	 * 新增插件
	 * @param pu
	 * @return
	 */
	int insertPluginUnit(PluginUnit pu);
	
	/**
	 * 删除插件
	 * @param pu
	 * @return
	 */
	int deletePluginUnit(@Param("list")List<PluginUnit> pus);
	
	/**
	 * 更新插件
	 * @param pu
	 * @return
	 */
	int updatePluginUnit(PluginUnit pu);
}
