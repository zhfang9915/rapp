package org.rapp.controller.wifi;

import org.rapp.comm.annotation.Permission;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.dto.param.BaseTableParam;
import org.rapp.pojo.dto.param.WifiSpyParam;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.wifi.FrameData;
import org.rapp.pojo.wifi.WifiSpy;
import org.rapp.service.wifi.WifiSpyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/spy")
@Controller
public class WifiSpyController extends BaseController {

	@Autowired
	WifiSpyService spyService;
	
	private static final String VIEW_FIX = "/spy/";
	
	/**
	 * 访问WIFI侦探数据展示页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@Permission("/spy/index")
	public String index() {
		return VIEW_FIX + "index";
	}
	
	/**
	 * 查询WIFI侦探路由器
	 * @param spy
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/spy/list")
	public TableResult<WifiSpy> wifiSpyList(@RequestBody WifiSpyParam spy) {
		return spyService.querySpy(spy);
	}
	
	/**
	 * 查询WIFI侦探路由器捕获的用户信息
	 * @return
	 */
	@RequestMapping(value = "/data/{spyId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/spy/data")
	public TableResult<FrameData> frameDataList(@PathVariable("spyId")String spyId, @RequestBody BaseTableParam param) {
		return spyService.querySpyDataBySpyId(spyId, param);
	}
}
