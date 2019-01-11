package org.rapp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.rapp.comm.exception.BaseServiceException;
import org.rapp.pojo.dto.param.AdvertTempParam;
import org.rapp.pojo.dto.param.JSCodeParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.AdvertTemp;
import org.rapp.pojo.entry.JSCode;
import org.rapp.repository.AdvertTempDao;
import org.rapp.service.AdvertTempService;
import org.rapp.service.util.SourceCodeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvertTempServiceImpl implements AdvertTempService {

	@Autowired AdvertTempDao tempDao;
	
	/**
	 * 查询所有的广告模版
	 * @author 许延明
	 * @return
	 */
	@Override
	public TableResult<AdvertTemp> queryAdvertTemps(AdvertTempParam param) {
		List<AdvertTemp> temps = tempDao.queryAdvertTemps(param);
		for (AdvertTemp temp:temps) {
			System.out.println("时间:" + temp.getCreateTime());
		}
		int total = tempDao.queryAdvertTempsCount(param);
		return new TableResult<>(total, temps);
	}
	
	/**
	 * 查询激活的广告模版
	 * @author 许延明
	 * @return
	 */
	@Override
	public List<AdvertTemp> queryActiveTemp() {
		AdvertTempParam param = new AdvertTempParam();
		param.setState('Y');
		return tempDao.queryAdvertTemps(param);
	}

	/**
	 * 根据ID查询模板
	 * @author 许延明
	 * @return
	 */
	@Override
	public BaseResult<AdvertTemp> queryAdvertTempById(int tempId) {
		AdvertTemp temp = tempDao.queryAdvertTempById(tempId);
		if (temp == null) {
			return new BaseResult<AdvertTemp>(false, "未查询到该模板信息");
		}
		return new BaseResult<AdvertTemp>(temp);
	}

	@Override
	public Map<String, String> createAdvertTemplate(Advert advert, String logid) {
		AdvertTemp temp = advert.getTemp();
		return null;
	}

	/**
	 * 新增
	 * @author 许延明
	 * @return
	 */
	@Override
	public BaseResult<AdvertTemp> addAdvertTemp(AdvertTemp temp) {
		temp.setCreateTime(new Date());
		int insertCount = tempDao.insertAdvertTemp(temp);
		if (insertCount == 1) {
			return new BaseResult<AdvertTemp>(temp);
		}
		return new BaseResult<AdvertTemp>(false, "新增模板失败");
	}

	/**
	 * 更新
	 * @author 许延明
	 * @return
	 */
	@Override
	@Transactional
	public BaseResult<AdvertTemp> editAdvertTemp(AdvertTemp temp) {
		int updateCoutn = tempDao.updateAdvertTemp(temp);
		if (updateCoutn == 1) {
			return new BaseResult<AdvertTemp>(temp);
		}
		return new BaseResult<AdvertTemp>(false, "更新模板失败");
	}

	/**
	 * 删除
	 * @author 许延明
	 * @return
	 */
	@Override
	public BaseResult<String> removeAdvertTemp(List<AdvertTemp> temps) {
		try {
			int updateCount = tempDao.deleteAdvertTemp(temps);
			if (updateCount > 0) {
				return new BaseResult<String>(true, "删除模板成功");
			}
			return new BaseResult<String>(false, "删除模板失败");
		} catch (Exception e) {
			throw new BaseServiceException("删除失败，该模板正在被使用！");
		}
	}

}
