package org.rapp.service;

import java.util.List;

import org.rapp.pojo.dto.param.FirmWareParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.FirmWare;

/**
 * 固件服务接口
 * @author 张芳
 *
 */
public interface FirmWareService {

	/**
	 * 查询固件列表信息
	 * @param params
	 * @return
	 */
	TableResult<FirmWare> queryFirmWares(FirmWareParam param);
	
	/**
	 * 根据ID获取固件
	 * @param fwId
	 * @return
	 */
	BaseResult<FirmWare> queryFirmWareById(String fwId);
	/**
	 * 根据版本获取固件信息
	 * @param version
	 * @return
	 */
	BaseResult<FirmWare> queryFirmWareByVersion(String version);
	
	/**
	 * 查询所有的JS信息
	 * @return
	 */
	BaseResult<List<FirmWare>> queryFirmWareAll();
	BaseResult<List<FirmWare>> queryFirmWareDownload();
	
	/**
	 * 新增固件
	 * @param fw
	 * @return
	 */
	BaseResult<FirmWare> addFirmWare(FirmWare fw);
	
	/**
	 * 删除固件
	 * @param fw
	 * @return
	 */
	BaseResult<String> removeFirmWare(List<FirmWare> fws);
	
	/**
	 * 更新固件
	 * @param fw
	 * @return
	 */
	BaseResult<FirmWare> editFirmWare(FirmWare fw);
}
