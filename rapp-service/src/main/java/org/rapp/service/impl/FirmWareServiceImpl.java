package org.rapp.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.rapp.comm.exception.BaseServiceException;
import org.rapp.pojo.dto.param.FirmWareParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.FirmWare;
import org.rapp.repository.FirmWareDao;
import org.rapp.service.FirmWareService;
import org.springframework.stereotype.Service;

@Service
public class FirmWareServiceImpl implements FirmWareService {
	
	@Resource
	FirmWareDao fwDao;

	@Override
	public TableResult<FirmWare> queryFirmWares(FirmWareParam param) {
		List<FirmWare> fws = fwDao.listFirmWares(param);
		int total = fwDao.countFirmWares(param);
		return new TableResult<>(total, fws);
	}

	@Override
	public BaseResult<FirmWare> queryFirmWareById(String fwId) {
		FirmWare fw = fwDao.queryFirmWareById(fwId);
		if (fw == null) {
			return new BaseResult<FirmWare>(false, "未查询到该版本固件信息");
		}
		return new BaseResult<FirmWare>(fw);
	}
	
	@Override
	public BaseResult<FirmWare> queryFirmWareByVersion(String version) {
		FirmWareParam param = new FirmWareParam();
		param.setVersion(version);
		FirmWare fw = fwDao.listFirmWares(param).get(0);
		if (fw == null) {
			return new BaseResult<FirmWare>(false, "未查询到该固件信息");
		}
		return new BaseResult<FirmWare>(fw);
	}

	@Override
	public BaseResult<List<FirmWare>> queryFirmWareAll() {
		List<FirmWare> fws = fwDao.queryFirmWareAll();
		if (fws == null) {
			return new BaseResult<List<FirmWare>>(false, "无可用的固件信息");
		}
		return new BaseResult<List<FirmWare>>(fws);
	}
	@Override
	public BaseResult<List<FirmWare>> queryFirmWareDownload() {
		List<FirmWare> fws = fwDao.queryFirmWareDownload();
		if (fws == null) {
			return new BaseResult<List<FirmWare>>(false, "无可用的固件信息");
		}
		return new BaseResult<List<FirmWare>>(fws);
	}
	
	@Override
	public BaseResult<FirmWare> addFirmWare(FirmWare fw) {
		fw.setCreateTime(new Date());
		int insertCount = fwDao.insertFirmWare(fw);
		if (insertCount == 1) {
			return new BaseResult<FirmWare>(fw);
		}
		return new BaseResult<FirmWare>(false, "新增固件失败");
	}

	@Override
	public BaseResult<String> removeFirmWare(List<FirmWare> fws) {
		try {
			int updateCount = fwDao.deleteFirmWare(fws);
			if (updateCount > 0) {
				//固件删除成功时删除文件系统的固件
				for (FirmWare firmWare : fws) {
					File file = new File(firmWare.getFwPath());
					if (file.exists()) {
						file.delete();
					}
				}
				return new BaseResult<String>(true, "删除固件成功");
			}
			return new BaseResult<String>(false, "删除固件失败");
		} catch (Exception e) {
			throw new BaseServiceException("删除失败，该固件正在被使用！");
		}
	}

	@Override
	public BaseResult<FirmWare> editFirmWare(FirmWare fw) {
		int updateCoutn = fwDao.updateFirmWare(fw);
		if (updateCoutn == 1) {
			return new BaseResult<FirmWare>(fw);
		}
		return new BaseResult<FirmWare>(false, "更新固件失败");
	}


}
