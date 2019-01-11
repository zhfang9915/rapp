package org.rapp.controller;

import java.util.List;

import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.AdvertParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.echart.EChartsData;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.statistical.AdvertStatistical;
import org.rapp.pojo.statistical.StatisticalParam;
import org.rapp.service.AdvertService;
import org.rapp.service.BaseService;
import org.rapp.service.Keys;
import org.rapp.service.RouterService;
import org.rapp.service.statistical.DataStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/ds")
public class DataStatisticalController extends BaseController {
	
	private static final String DATA_CENTER = "data";
	
	@Autowired
	DataStatisticalService statisticalService;
	
	@Autowired
	AdvertService advertService;
	
	@Autowired
	RouterService routerService;
	
	@Autowired
	BaseService service;
	
	/**
	 * 广告推送次数统计视图
	 * @return
	 */
	@RequestMapping(value = "/apc", method = RequestMethod.GET)
	public String advertStatisticalIndex() {
		User user = (User) getSession().getAttribute(Keys.LOGIN_USER);
		AdvertParam param = new AdvertParam();
		param.setCreateBy(user.getUsername());
		BaseResult<List<Advert>> result = advertService.queryAdvertForList(param);
		getRequest().setAttribute("ads", result);
		
		BaseResult<List<Router>> routers = routerService.queryRouterAll(user);
		getRequest().setAttribute("routers", routers);
		return DATA_CENTER + "/advert_push_count";
	}
	
	/**
	 * 查询广告推送统计
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/apc/advert/bar", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<EChartsData> advertStatisticalByAdvert(@SessionAttribute(Keys.LOGIN_USER) User user, StatisticalParam param) {
		BaseResult<List<AdvertStatistical>> result = statisticalService.statisticalAdvertByAdvert(user, param);
		if (!result.isSuccess()) {//统计出错
			return new BaseResult<>(false, result.getMsg());
		}
		return statisticalService.setEChartsData(result.getData());
	}
	
	
	/**
	 * 最近半个月推送情况
	 * @param user
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/apc/date/bar", method = RequestMethod.POST,
	produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<EChartsData> advertStatisticalByDate(@SessionAttribute(Keys.LOGIN_USER) User user, StatisticalParam param) {
		param.setCreateBy(service.getCreateBy(user));
		BaseResult<List<AdvertStatistical>> result = statisticalService.statisticalAdvertByDate(param);
		if (!result.isSuccess()) {//统计出错
			return new BaseResult<>(false, result.getMsg());
		}
		return statisticalService.setEChartsData(result.getData());
	}
	
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/apc/hour/bar", method = RequestMethod.POST,
//			produces = {"application/json;charset=UTF-8"})
//	@ResponseBody
//	public BaseResult<EChartsData> pushAdvertByHours(@SessionAttribute(Keys.LOGIN_USER) User user) {
//		StatisticalParam param = new StatisticalParam(service.getCreateBy(user));
//		String pushTime = getRequest().getParameter("pt");
//		if (StringUtils.isNotBlank(pushTime)) {
//			param.setPushTime(pushTime);
//		}else{
//			param.setPushTime(CalendarUtil.getLongDate(new Date()));
//		}
//		param.setAdvertId(getRequest().getParameter("aid"));
//		BaseResult<List<AdvertStatistical>> result = statisticalService.pushAdvertByHours(param);
//		if (!result.isSuccess()) {//统计出错
//			return new BaseResult<>(false, result.getMsg());
//		}
//		
//		return statisticalService.setEChartsData(result.getData());
//	}
	
	
	
	/**
	 * 广告推送次数统计视图
	 * @return
	 */
	@RequestMapping(value = "/apc/router", method = RequestMethod.GET)
	public String routerStatisticalIndex(@SessionAttribute(Keys.LOGIN_USER)User user) {
		BaseResult<List<Router>> result = routerService.queryRouterAll(user);
		getRequest().setAttribute("routers", result);
		return DATA_CENTER + "/router_push_count";
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/apc/router/bar", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<EChartsData> routerStatisticalByDate(@SessionAttribute(Keys.LOGIN_USER) User user, StatisticalParam param) {
		param.setCreateBy(service.getCreateBy(user));
		BaseResult<List<AdvertStatistical>> result = statisticalService.statisticalRouterByDate(param);
		if (!result.isSuccess()) {//统计出错
			return new BaseResult<>(false, result.getMsg());
		}
		return statisticalService.setEChartsData(result.getData());
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/apc/router/day/bar", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<EChartsData> routerDaysStatistical(@SessionAttribute(Keys.LOGIN_USER) User user, StatisticalParam param) {
		param.setCreateBy(service.getCreateBy(user));
		
		BaseResult<List<AdvertStatistical>> result = statisticalService.statisticalRouterByDate(param);
		if (!result.isSuccess()) {//统计出错
			return new BaseResult<>(false, result.getMsg());
		}
		
		
		return statisticalService.setEChartsData(result.getData());
	}
	
	
	
}
