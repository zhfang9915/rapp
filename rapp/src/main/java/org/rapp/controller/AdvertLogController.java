package org.rapp.controller;

import java.util.List;

import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.AdvertParam;
import org.rapp.pojo.dto.param.AdvertPushSearch;
import org.rapp.pojo.dto.param.PushDetailParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.ListResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.AdvertHistoryLog;
import org.rapp.pojo.entry.PushDetail;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.log.AdvertPushLog;
import org.rapp.service.AdvertLogService;
import org.rapp.service.AdvertService;
import org.rapp.service.Keys;
import org.rapp.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 广告流水
 * @author 张芳
 *
 */
@Controller
@RequestMapping("/aplog")
public class AdvertLogController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AdvertLogService advertLogService;
	
	@Autowired
	AdvertService advertService;
	
	@Autowired
	RouterService routerService;
	
	private final String ADVERT_LOG = "advertLog";
	
	/**
	 * 广告流水表格页
	 * @return
	 */
	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String table() {
		return ADVERT_LOG + "/table";
	}
	
	/**
	 * 查询广告流水表格数据
	 * @param user
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/search/table", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public TableResult<AdvertPushLog> queryAdvertLog4Table(
			@SessionAttribute(Keys.LOGIN_USER)User user, @RequestBody AdvertPushSearch search) {
		logger.info(search.toString());
		return advertLogService.queryPushLog4Table(user, search);
	}
	
	
	private final static int pageSize = 8;
	
	/**
	 * 根据广告获取推送明细
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/detail/{advertId}/{page}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ListResult<PushDetail> queryPushDetailByAdvert(
			@SessionAttribute(Keys.LOGIN_USER)User user, @PathVariable("page")Integer page, @PathVariable("advertId")String advertId) {
		PushDetailParam param = new PushDetailParam();
		if (user.getRole().getRoleId() > 2) {//非管理员
			param.setCreateBy(user.getUsername());
		}
		param.setLimit(pageSize + "");
		param.setOffset((page-1)*pageSize + "");
		param.setAdvertId(advertId);
		TableResult<PushDetail> pds = advertService.queryPushDetailByAdvert(param);
		int pageCount = pds.getTotal();
		if (pageCount % pageSize != 0) {
			pageCount = pageCount / pageSize + 1;
		} else {
			pageCount = pageCount / pageSize;
		}
		return new ListResult<>(pageCount, pds.getRows());
	}
	
	
	/**
	 * 广告流水表格页
	 * @return
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String history(@SessionAttribute(Keys.LOGIN_USER)User user) {
		BaseResult<List<Router>> routers = routerService.queryRouterAll(user);
		getRequest().setAttribute("routers", routers);
		
		AdvertParam param = new AdvertParam();
		param.setCreateBy(user.getUsername());
		BaseResult<List<Advert>> ads = advertService.queryAdvertForList(param);
		getRequest().setAttribute("ads", ads);
		return ADVERT_LOG + "/history";
	}
	
	/**
	 * 查询广告流水表格数据
	 * @param user
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/search/history", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public TableResult<AdvertHistoryLog> queryAdvertLogHistory4Table(
			@SessionAttribute(Keys.LOGIN_USER)User user, @RequestBody AdvertPushSearch search) {
		logger.info("历史查询："+search.toString());
		return advertLogService.queryAdvertHistory(user, search);
	}
}
