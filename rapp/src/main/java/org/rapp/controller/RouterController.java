package org.rapp.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.rapp.comm.annotation.NotLogin;
import org.rapp.comm.util.MD5Utils;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.RouterParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.ListResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Channel;
import org.rapp.pojo.entry.FirmWare;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.web.Rapp;
import org.rapp.service.BaseService;
import org.rapp.service.ChannelService;
import org.rapp.service.FirmWareService;
import org.rapp.service.Keys;
import org.rapp.service.RouterService;
import org.rapp.service.TokenValidationService;
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
 * 设备管理
 * @author 张芳
 *
 */
@RequestMapping("/router")
@Controller
public class RouterController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired RouterService routerService;
	
	@Autowired ChannelService channelService;
	
	@Autowired FirmWareService firmWareService;
	
	@Autowired BaseService service;
	
	@Autowired Rapp rapp;
	
	@Autowired TokenValidationService validationService;
	
	private static final String ROUTER = "router/";
	

	/**
	 * 打开设备注册页面
	 * @param user
	 * @param router
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@SessionAttribute(Keys.LOGIN_USER) User user, Router router) {
		BaseResult<Router> result = null;
		logger.info("设备请求注册信息：" + router.toString());
		//token校验
		if (!validationService.validationDveNoAndMac(router.getMac(), router.getFwv(), router.getToken())) {
			logger.info("设备注册token校验未通过：" + router.getMac());
			result = new BaseResult<Router>(false, "设备信息认证不通过");
			getRequest().setAttribute("result", result);
			return ROUTER + "result";
		}
		
		//校验该设备是否注册过
		BaseResult<Router> rresult = routerService.queryRouterByMac(router.getMac());
		if (rresult.isSuccess()) {
			logger.info("该设备已注册：" + router.getDevNo());
			result = new BaseResult<Router>(false, "该设备已注册");
			getRequest().setAttribute("result", result);
			return ROUTER + "result";
		}
		
		//根据固件的版本获取固件信息
		FirmWare fw = firmWareService.queryFirmWareByVersion(router.getFwv()).getData();
		if (fw == null) {
			logger.info("不存在该版本的固件信息：" + router.getFwv());
			result = new BaseResult<Router>(false, "该版本固件不存在");
			getRequest().setAttribute("result", result);
			return ROUTER + "result";
		}
		//设备关系绑定
		router.setFwId(fw.getFwId());
		
		//查询用户名下的所有渠道
		BaseResult<List<Channel>> channels = channelService.queryChannelAll(user);
		
		getRequest().setAttribute("channels", channels);
		result = new BaseResult<Router>(router);
		getRequest().setAttribute("result", result);
		
		return ROUTER + "register";
	}
	
	/**
	 * 提交注册信息
	 * @param response
	 * @param router
	 * @throws IOException 
	 */
	@RequestMapping(value = "/register/submit", method = RequestMethod.POST)
	public String submitRegister(@SessionAttribute(Keys.LOGIN_USER)User user,
						HttpServletResponse response, Router router) throws IOException {
		logger.info("设备提交注册信息：" + router.toString());
		//设备注册
		BaseResult<Router> result = routerService.register(user, router);
		
		if (result.isSuccess()) {
			//测试
//			String myBackUrl = rapp.getRootPath()+"router/result";
//			String token = MD5Utils.getMD5(router.getDevNo() + router.getMac());
//			String param = "?devNo=" + router.getDevNo() + "&token=" + token 
//					+ "&mac=" + router.getMac();
//			response.sendRedirect(myBackUrl + param);
			String myBackUrl = rapp.getRootPath()+"router/result";
			String token = MD5Utils.getMD5(router.getDevNo() + router.getMac());
			String param = "&devNo=" + router.getDevNo() + "&token=" + token 
					+ "&backUrl=" + URLEncoder.encode(myBackUrl, "UTF-8");
			logger.info("跳转地址：" + router.getBackUrl() + param);
			response.sendRedirect(router.getBackUrl() + param);
			return null;
		}
		getRequest().setAttribute("result", result);
		
		return ROUTER + "result";
	}
	
	/**
	 * 注册回调
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(@SessionAttribute(Keys.LOGIN_USER)User user) throws IOException {
		String devNo = getRequest().getParameter("devNo");
		String mac = getRequest().getParameter("mac");
		String token = getRequest().getParameter("token");
		logger.info("回调参数：" + devNo);
		
		if (validationService.validationDveNoAndMac(devNo, mac, token)) {
			logger.info("回调的设备编码为：" + devNo);
			BaseResult<Router> result = routerService.callback(devNo, mac);
			getRequest().setAttribute("result", result);
		}else {
			getRequest().setAttribute("result", new BaseResult<>(false, "注册信息错误,注册失败！"));
		}
		return ROUTER + "result";
	}
	
	/**
	 * 表格展示页
	 * @return
	 */
	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String showTable(@SessionAttribute(Keys.LOGIN_USER)User user) {
		BaseResult<List<Channel>> channels = channelService.queryChannelAll(user);
		getRequest().setAttribute("channels", channels);
		
		getRequest().setAttribute("roleId", user.getRole().getRoleId());
		
		return ROUTER + "table";
	}
	
	/**
	 * 表格展示我的设备
	 * @param user
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/query4Table", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public TableResult<Router> query4Table(@SessionAttribute(Keys.LOGIN_USER)User user,
			@RequestBody RouterParam param) {
		
		return routerService.queryRouter4Table(user, param);
	}
	
	/**
	 * 删除设备
	 * @param router
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> deleteRouter(@RequestBody List<Router> routers, @SessionAttribute(Keys.LOGIN_USER)User user) {
		
		return routerService.deleteRouter(routers, service.getCreateBy(user));
	}
	
	
	/**
	 * 更新设备所属渠道
	 * @param router
	 * @return
	 */
	@RequestMapping(value = "/update/{channelId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> updateRouterChannel(@RequestBody List<Router> routers,
			@PathVariable("channelId")String channelId) {
		return routerService.updateRouter4Channel(routers, channelId);
	}
	
	/**
	 * 为渠道分配设备
	 * @return
	 */
	@RequestMapping(value = "/update/{channelId}/index", method = RequestMethod.GET)
	public String bindingRouterIndex(@PathVariable("channelId")String channelId){
		Channel channel = channelService.queryChannelById(channelId).getData();
		getRequest().setAttribute("channel", channel);
		return ROUTER + "binding_router";
	}
	
	
	
	/**
	 * 为渠道分配设备
	 * @return
	 */
	@RequestMapping(value = "/update/{channelId}/submit", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> bindingRouterSubmit(@PathVariable("channelId")String channelId, @RequestBody String[] macArr){
		List<Router> routers = new ArrayList<>();
		for (String mac : macArr) {
			Router router = new Router();
			router.setMac(mac);
			routers.add(router);
		}
		BaseResult<String> result = routerService.updateRouter4Channel(routers, channelId);
		return result;
	}
	
	/**
	 * 更新设备地址信息
	 * @param router
	 * @return
	 */
	@RequestMapping(value = "/update/address", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> updateRouterAddress(Router router) {
		return routerService.updateRouterAddress(router);
	}
	
	
	@RequestMapping(value = "/fw/update", method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@NotLogin
	public String updateRouterFirmware(Router router) {
		//token校验
		if (!validationService.validationDveNoAndMac(router.getMac(), router.getFwv(), router.getToken())) {
			logger.info("设备固件更新token校验未通过：" + router.getMac());
			return "001\r\n设备信息认证不通过";
		}
		
		//校验该设备是否注册过
		BaseResult<Router> result = routerService.queryRouterByMac(router.getMac());
		if (result.isSuccess()) {
			Router routerdb = result.getData();
			//获取固件
			BaseResult<FirmWare> fwResult = firmWareService.queryFirmWareByVersion(router.getFwv());
			if (!fwResult.isSuccess()) {
				return "001\r\n不存在该固件信息";
			}
			routerdb.setFwId(fwResult.getData().getFwId());
			BaseResult<String> updateFW = routerService.updateRouterFirmware(routerdb);
			if (!updateFW.isSuccess()) {
				return "001\r\n更新设备固件版本失败";
			}
			return "000\r\n" + routerdb.getDevNo() 
				+ "\r\n" + MD5Utils.getMD5(routerdb.getDevNo() + routerdb.getMac());
		}else {
			logger.info("不存在该MAC的路由器：" + router.getMac());
			return "001\r\n设备信息不存在";
		}
		
	}
	
	private final static int pageSize = 18;
	
	/**
	 * 设备绑定页
	 * @param user
	 * @param channelId
	 * @return
	 */
	@RequestMapping(value = "/binding/{channelId}", method = RequestMethod.GET)
	public String routerBindingIndex(@PathVariable("channelId")String channelId) {
		Channel channel = channelService.queryChannelById(channelId).getData();
		getRequest().setAttribute("channel", channel);
		return ROUTER + "binding";
	}
	
	/**
	 * 查询该渠道下已绑定的设备信息
	 * @param user
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/binding/list/{page}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ListResult<Router> routerBindingList(@SessionAttribute(Keys.LOGIN_USER)User user, @PathVariable("page")Integer page) {
		RouterParam param = new RouterParam();
		if (user.getRole().getRoleId() > 1) {//非管理员
			param.setCreateBy(user.getUsername());
		}
		param.setChannelId(getRequest().getParameter("channelId"));
		param.setLimit(pageSize + "");
		param.setOffset((page-1)*pageSize + "");
		param.setDevName(getRequest().getParameter("key"));
		TableResult<Router> routers = routerService.queryRouter4Table(user, param);
		
		int pageCount = routers.getTotal();
		if (pageCount % pageSize != 0) {
			pageCount = pageCount / pageSize + 1;
		} else {
			pageCount = pageCount / pageSize;
		}
		return new ListResult<>(pageCount, routers.getRows());
	}
	
	@RequestMapping(value = "/free/list/{page}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ListResult<Router> routerFreeList(@SessionAttribute(Keys.LOGIN_USER)User user, @PathVariable("page")Integer page) {
		RouterParam param = new RouterParam();
		if (user.getRole().getRoleId() > 1) {//非管理员
			param.setCreateBy(user.getUsername());
		}
		param.setLimit(pageSize + "");
		param.setOffset((page-1)*pageSize + "");
		param.setDevName(getRequest().getParameter("key"));
		TableResult<Router> routers = routerService.queryFreeRouter(param);
		
		int pageCount = routers.getTotal();
		if (pageCount % pageSize != 0) {
			pageCount = pageCount / pageSize + 1;
		} else {
			pageCount = pageCount / pageSize;
		}
		return new ListResult<>(pageCount, routers.getRows());
	}
	
	/**
	 * 我的设备列表页
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list (@SessionAttribute(Keys.LOGIN_USER)User user) {
		BaseResult<List<Channel>> channels = channelService.queryChannelAll(user);
		getRequest().setAttribute("channels", channels);
		getRequest().setAttribute("createBy", user.getUsername());
		return ROUTER + "list";
	}
	
	/**
	 * 我的设备列表
	 * @param page
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list/{page}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ListResult<Router> ListChannelByPage(@PathVariable("page")Integer page, @SessionAttribute(Keys.LOGIN_USER)User user){
		RouterParam param = new RouterParam();
		if (user.getRole().getRoleId() > 1) {//非管理员
			param.setCreateBy(user.getUsername());
		}
		param.setLimit(((page-1)*pageSize + pageSize) + "");
		param.setOffset((page-1)*pageSize + "");
		param.setDevName(getRequest().getParameter("key"));
		TableResult<Router> routers = routerService.queryRouter4Table(user, param);
		int pageCount = routers.getTotal();
		if (pageCount % pageSize != 0) {
			pageCount = pageCount / pageSize + 1;
		} else {
			pageCount = pageCount / pageSize;
		}
		
		return new ListResult<>(pageCount, routers.getRows());
	}
	
	/**
	 * 删除设备
	 * @param router
	 * @return
	 */
	@RequestMapping(value = "/delete/{devNo}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> deleteRouter(@SessionAttribute(Keys.LOGIN_USER)User user, @PathVariable("devNo")String devNo) {
		return routerService.deleteRouterByNo(devNo, user.getUsername());
	}
	
	/**
	 * 设备绑定渠道
	 * @return
	 */
	@RequestMapping(value = "/binding/{channelId}/{devNo}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> bindingChannel(@PathVariable("channelId")String channelId, @PathVariable("devNo")String devNo) {
		logger.info(devNo + "绑定渠道" + channelId);
		return routerService.bindingRouter4Channel(devNo, channelId);
	}
	
	@RequestMapping(value = "/detail/{devNo}", method = RequestMethod.GET)
	public String routerDetail(@PathVariable("devNo")String devNo, @SessionAttribute(Keys.LOGIN_USER)User user) {
		Router router = routerService.queryRouterDetailByDevNo(devNo, user).getData();
		getRequest().setAttribute("router", router);
		getRequest().setAttribute("devNo", devNo);
		if (null == router.getChannel()) {
			BaseResult<List<Channel>> channels = channelService.queryChannelAll(user);
			getRequest().setAttribute("channels", channels);
		}
		return ROUTER + "detail";
	}
	
	/**
	 * 渠道页接触设备绑定关系
	 * @return
	 */
	@RequestMapping(value = "/unbind/{channelId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> removeBindingRouterSubmit(@RequestBody String[] devNoArr,
			@PathVariable("channelId")String channelId) {
		logger.info("解除渠道绑定关系：" + channelId);
		List<Router> routers = new ArrayList<>();
		for (String devNo : devNoArr) {
			Router router = new Router();
			router.setDevNo(devNo);
			routers.add(router);
		}
		return routerService.removeRouterChannel(routers, channelId);
	}
	
	
}
