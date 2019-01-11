package org.rapp.service.wifi;

import javax.servlet.http.HttpServletRequest;

import org.rapp.pojo.dto.param.BaseTableParam;
import org.rapp.pojo.dto.param.WifiSpyParam;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.wifi.FrameData;
import org.rapp.pojo.wifi.WifiSpy;

public interface WifiSpyService {

	/**
	 * 保存WIFI侦探数据
	 * @param spy
	 */
	void asyncSaveWifiSpy(WifiSpy spy);
	
	/**
	 * 查询WIFI侦探路由器信息
	 * @param spy
	 * @return
	 */
	TableResult<WifiSpy> querySpy(WifiSpyParam spy);
	
	/**
	 * 根据路由器查询该路由器不活的用户信息
	 * @param request
	 * @return
	 */
	TableResult<FrameData> querySpyDataBySpyId(String spyId, BaseTableParam param);
}
