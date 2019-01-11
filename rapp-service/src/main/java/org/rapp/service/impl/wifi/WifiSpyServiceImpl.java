package org.rapp.service.impl.wifi;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.rapp.pojo.dto.param.BaseTableParam;
import org.rapp.pojo.dto.param.WifiSpyParam;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.wifi.FrameData;
import org.rapp.pojo.wifi.WifiSpy;
import org.rapp.repository.wifi.WifiSpyDao;
import org.rapp.service.wifi.WifiSpyService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WifiSpyServiceImpl implements WifiSpyService {

	@Resource
	WifiSpyDao spyDao;
	
	@Async
	@Override
	public void asyncSaveWifiSpy(WifiSpy spy) {
		//判断spy的mark是否是6个！
		if (!"!!!!!!".equals(spy.getMark())) {
			return;//是非法数据帧
		}
		
		//执行保存WIFI路由器信息
		spyDao.saveSpy(spy);
		//判断是否保存成功
		if (spy.getSpyId()==null) {//如果失败再保存一次
			spyDao.saveSpy(spy);
		}
		
		//再判断是否保存成功
		if (spy.getSpyId()==null) {//如果失败则忽略
			return;
		}
		
		//开始保存用户信息
		spyDao.saveSpyDatas(spy.getSpyId(), spy.getDatas());
		
	}

	@Override
	public TableResult<WifiSpy> querySpy(WifiSpyParam spy) {
		return new TableResult<>(spyDao.querySpyCount(spy), spyDao.querySpy(spy));
	}

	@Override
	public TableResult<FrameData> querySpyDataBySpyId(String spyId, BaseTableParam param) {
		List<FrameData> datas = spyDao.querySpyDataBySpyId(spyId, param.getLimit(), param.getOffset());
		int total = spyDao.querySpyDataBySpyIdCount(spyId);
		return new TableResult<>(total, datas);
	}

}
