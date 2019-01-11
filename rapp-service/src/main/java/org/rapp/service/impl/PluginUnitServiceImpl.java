package org.rapp.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.rapp.comm.exception.BaseServiceException;
import org.rapp.pojo.dto.param.PluginUnitParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.PluginUnit;
import org.rapp.repository.PluginUnitDao;
import org.rapp.service.PluginUnitService;
import org.springframework.stereotype.Service;

@Service
public class PluginUnitServiceImpl implements PluginUnitService {
	
	@Resource
	PluginUnitDao puDao;

	@Override
	public TableResult<PluginUnit> queryPluginUnits(PluginUnitParam param) {
		List<PluginUnit> pus = puDao.listPluginUnits(param);
		int total = puDao.countPluginUnits(param);
		return new TableResult<>(total, pus);
	}

	@Override
	public BaseResult<PluginUnit> queryPluginUnitById(String pluginId) {
		PluginUnit pu = puDao.queryPluginUnitById(pluginId);
		if (pu == null) {
			return new BaseResult<PluginUnit>(false, "未查询到该插件信息");
		}
		return new BaseResult<PluginUnit>(pu);
	}
	
	@Override
	public BaseResult<PluginUnit> queryPluginUnitByCrossstool(String crossstool) {
		PluginUnit pu = puDao.queryPluginUnitByCrossstool(crossstool);
		if (pu == null) {
			return new BaseResult<PluginUnit>(false, "未查询到该插件信息");
		}
		return new BaseResult<PluginUnit>(pu);
	}
	
	@Override
	public BaseResult<PluginUnit> queryPluginUnitByMD5(String md5) {
		PluginUnit pu = puDao.queryPluginUnitByMD5(md5);
		if (pu == null) {
			return new BaseResult<PluginUnit>(false, "未查询到该插件信息");
		}
		return new BaseResult<PluginUnit>(pu);
	}

	@Override
	public BaseResult<List<PluginUnit>> queryPluginUnitAll() {
		List<PluginUnit> pus = puDao.queryPluginUnitAll();
		if (pus == null) {
			return new BaseResult<List<PluginUnit>>(false, "无可用的插件信息");
		}
		return new BaseResult<List<PluginUnit>>(pus);
	}
	
	@Override
	public BaseResult<PluginUnit> addPluginUnit(PluginUnit pu) {
		pu.setCreateTime(new Date());
		int insertCount = puDao.insertPluginUnit(pu);
		if (insertCount == 1) {
			return new BaseResult<PluginUnit>(pu);
		}
		return new BaseResult<PluginUnit>(false, "新增插件失败");
	}

	@Override
	public BaseResult<String> removePluginUnit(List<PluginUnit> pus) {
		try {
			int updateCount = puDao.deletePluginUnit(pus);
			if (updateCount > 0) {
				//插件删除成功时删除文件系统的插件
				for (PluginUnit pu : pus) {
					File file = new File(pu.getPluginPath());
					if (file.exists()) {
						file.delete();
					}
				}
				return new BaseResult<String>(true, "删除插件成功");
			}
			return new BaseResult<String>(false, "删除插件失败");
		} catch (Exception e) {
			throw new BaseServiceException("删除失败，该插件正在被使用！");
		}
	}

	@Override
	public BaseResult<PluginUnit> editPluginUnit(PluginUnit pu) {
		int updateCoutn = puDao.updatePluginUnit(pu);
		if (updateCoutn == 1) {
			return new BaseResult<PluginUnit>(pu);
		}
		return new BaseResult<PluginUnit>(false, "更新插件失败");
	}


}
