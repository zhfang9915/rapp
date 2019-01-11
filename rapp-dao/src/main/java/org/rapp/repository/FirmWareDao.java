package org.rapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.dto.param.FirmWareParam;
import org.rapp.pojo.entry.FirmWare;

/**
 * 固件持久化接口
 * @author 张芳
 *
 */
public interface FirmWareDao {

	/**
	 * 查询固件列表信息
	 * @param params
	 * @return
	 */
	List<FirmWare> listFirmWares(FirmWareParam param);
	
	/**
	 * 固件列表总数
	 * @param params
	 * @return
	 */
	int countFirmWares(FirmWareParam param);
	
	/**
	 * 根据ID获取固件
	 * @param fwId
	 * @return
	 */
	FirmWare queryFirmWareById(@Param("fwId")String fwId);
	
	/**
	 * 查询所有的JS代码信息
	 * @return
	 */
	List<FirmWare> queryFirmWareAll();
	
	/**
	 * 查询所有的JS代码信息
	 * @return
	 */
	List<FirmWare> queryFirmWareDownload();
	
	/**
	 * 新增固件
	 * @param fw
	 * @return
	 */
	int insertFirmWare(FirmWare fw);
	
	/**
	 * 删除固件
	 * @param fw
	 * @return
	 */
	int deleteFirmWare(@Param("list")List<FirmWare> fws);
	
	/**
	 * 更新固件
	 * @param fw
	 * @return
	 */
	int updateFirmWare(FirmWare fw);
}
