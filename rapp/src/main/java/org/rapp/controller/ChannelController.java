package org.rapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.rapp.comm.annotation.Permission;
import org.rapp.comm.exception.BaseControllerException;
import org.rapp.comm.util.CalendarUtil;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.ChannelParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.ChannelReuslt;
import org.rapp.pojo.dto.result.ListResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Channel;
import org.rapp.pojo.entry.JSCode;
import org.rapp.pojo.entry.JStree;
import org.rapp.pojo.entry.Router;
import org.rapp.repository.ChannelDao;
import org.rapp.service.ChannelService;
import org.rapp.service.JSCodeService;
import org.rapp.service.Keys;
import org.rapp.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 渠道 Handler
 * @author 张芳
 */
@RequestMapping("/channel")
@Controller
public class ChannelController extends BaseController {
	
	@Resource
	ChannelService channelService;
	
	@Resource
	JSCodeService codeService;
	
	@Autowired
	RouterService routerService;
	

	private final static String CHANNEL = "channel/";
	
	private final static int pageSize = 10;
	
	@Autowired ChannelDao dao;
	
	/**
	 * 渠道管理页
	 * @return
	 */
	@RequestMapping(value = "/list/index", method = RequestMethod.GET)
	public String list (@SessionAttribute(Keys.LOGIN_USER)User user) {
		getRequest().setAttribute("createBy", user.getUsername());
		return CHANNEL + "list";
	}
	
	@RequestMapping(value = "/list/{page}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ListResult<ChannelReuslt> ListChannelByPage(@PathVariable("page")Integer page, @SessionAttribute(Keys.LOGIN_USER)User user){
		ChannelParam param = new ChannelParam();
		if (user.getRole().getRoleId() > 1) {//非管理员
			param.setCreateBy(user.getUsername());
		}
		param.setLimit(pageSize + "");
		param.setOffset((page-1)*pageSize + "");
		param.setChannelNameCh(getRequest().getParameter("key"));
		TableResult<ChannelReuslt> channles = channelService.queryMyChannels(param);
		int pageCount = channles.getTotal();
		if (pageCount % pageSize != 0) {
			pageCount = pageCount / pageSize + 1;
		} else {
			pageCount = pageCount / pageSize;
		}
		
		return new ListResult<>(pageCount, channles.getRows());
	}
	
	/**
	 * 渠道管理页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@Permission("/channel/index")
	public String index (@SessionAttribute(Keys.LOGIN_USER)User user) {
		getRequest().setAttribute("roleId", user.getRole().getRoleId());
		return CHANNEL + "index";
	}
	
	@RequestMapping(value = "/index/add", method = RequestMethod.GET)
	@Permission("/channel/index/add")
	public String addChannelPage(@SessionAttribute(Keys.LOGIN_USER)User user) {
		int roleId = user.getRole().getRoleId();
		getRequest().setAttribute("roleId", roleId);
		if (roleId < 3) {
			BaseResult<List<JSCode>> codes = codeService.queryJSCodeAll();
			getRequest().setAttribute("codes", codes);
		}
		return CHANNEL + "create";
	}
	
	@RequestMapping(value = "/index/update/{channelId}", method = RequestMethod.GET)
	@Permission("/channel/index/update")
	public String updateChannelPage(@SessionAttribute(Keys.LOGIN_USER)User user,
			@PathVariable("channelId")String channelId) {
		BaseResult<Channel> result = channelService.queryChannelById(channelId);
		getRequest().setAttribute("channel", result.getData());
		int roleId = user.getRole().getRoleId();
		getRequest().setAttribute("roleId", roleId);
		if (roleId < 3) {
			BaseResult<List<JSCode>> codes = codeService.queryJSCodeAll();
			getRequest().setAttribute("codes", codes);
		}
		return CHANNEL + "update";
	}
	
	/**
	 * 查询渠道列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/channel/list")
	public TableResult<Channel> queryChannels (@SessionAttribute(Keys.LOGIN_USER)User user, 
			@RequestBody ChannelParam param) { 
		if (user.getRole().getRoleId() > 2) {//非管理员
			param.setCreateBy(user.getUsername());
		}
		return channelService.queryChannels(param);
	}
	
	/**
	 * 新增渠道
	 * @param channel
	 * @return
	 */
	@RequestMapping(value = "/addChannel", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/channel/addChannel")
	public String addChannel(@SessionAttribute(Keys.LOGIN_USER)User user, Channel channel) {
		channel.setCreateBy(user.getUsername());//创建人
		BaseResult<Channel> result = channelService.addChannel(channel);
		if (!result.isSuccess()) {
			throw new BaseControllerException(result.getMsg());
		}
		if (StringUtils.isNotBlank(channel.getCodeId())) {
			BaseResult<JSCode> codeResult = codeService.queryJSCodeById(channel.getCodeId());
			result.getData().setCode(codeResult.getData());
		}
//		getRequest().setAttribute("result", result);
//		getRequest().setAttribute("roleId", user.getRole().getRoleId());
		return "redirect:/channel/detail/" + result.getData().getChannelId();
//		return CHANNEL + "result";
	}
	
	/**
	 * 更新渠道
	 * @param channel
	 * @return
	 */
	@RequestMapping(value = "/updateChannel", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/channel/updateChannel")
	public String updateChannel(Channel channel) {
		BaseResult<Channel> result = channelService.editChannel(channel);
		if (!result.isSuccess()) {
			throw new BaseControllerException(result.getMsg());
		}
		if (channel.getCodeId() != null) {
			BaseResult<JSCode> codeResult = codeService.queryJSCodeById(channel.getCodeId());
			result.getData().setCode(codeResult.getData());
		}
//		getRequest().setAttribute("result", result);
//		getRequest().setAttribute("roleId", user.getRole().getRoleId());
		return "redirect:/channel/detail/" + result.getData().getChannelId();
//		return CHANNEL + "result";
	}
	
	/**
	 * 删除渠道
	 * @param channel
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/channel/delete")
	public BaseResult<String> deleteChannel(@RequestBody List<Channel> channels) {
		return channelService.removeChannel(channels);
	}
	
	@RequestMapping(value = "/detail/{channelId}", method = RequestMethod.GET)
	public String detailChannel(@SessionAttribute(Keys.LOGIN_USER)User user,
			@PathVariable("channelId")String channelId) {
		BaseResult<Channel> result = channelService.showDetail(user, channelId);
		getRequest().setAttribute("result", result);
		getRequest().setAttribute("roleId", user.getRole().getRoleId());
		return CHANNEL + "detail";
	}
	
	
	/**
	 * 删除渠道
	 * @param router
	 * @return
	 */
	@RequestMapping(value = "/delete/{channelId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> deleteChannel(@PathVariable("channelId")String channelId) {
		List<Channel> channels = new ArrayList<Channel>();
		Channel channel = new Channel();
		channel.setChannelId(channelId);
		channels.add(channel);
		return channelService.removeChannel(channels);
	}
	
	/**
	 * 查询我的区域信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/query/my", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<List<ChannelReuslt>> queryMyChannel(@SessionAttribute(Keys.LOGIN_USER)User user) {
		return channelService.queryMyChannel(CalendarUtil.getAssignDate(0, "yyyy-MM-dd"), user.getUsername(), null, "0", "5");
	}
	
	@RequestMapping(value = "/query/my", method = RequestMethod.GET)
	public String queryMyAllChannel() {
		return CHANNEL + "my_channel";
	}
	
	@RequestMapping(value = "/query/tree", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public List<JStree> getTreeData(@RequestParam(value = "parentId") String parentId, @SessionAttribute(Keys.LOGIN_USER)User user) {
		System.out.println(parentId);
		List<JStree> list = new ArrayList<>();
		if ("#".equals(parentId)) {
			JStree tree = new JStree("c_001", "我的区域", "#", true, "../../img/user_icon.png");
			list.add(tree);
		}else if ("c_001".equals(parentId)) {
			List<Channel> channels = channelService.queryChannelAll(user).getData();
			for (Channel channel : channels) {
				JStree ct = new JStree(channel.getChannelId(), channel.getChannelNameCh(), "c_001", true, "../../img/channel_icon.png");
				list.add(ct);
			}
		}else {
			List<Router> routers = routerService.queryRouterByChannel(user, parentId);
			for (Router router : routers) {
				JStree rt = new JStree(router.getDevNo(), router.getDevName(), parentId, false, "../../img/r_off_icon.png");
				list.add(rt);
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/query4map/{type}/{nodeId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<List<Router>> showRouterInMap(@PathVariable("type")int type, @PathVariable("nodeId")String keyword, 
			@SessionAttribute(Keys.LOGIN_USER)User user) {
		BaseResult<List<Router>> result = null;
		List<Router> routers = null;
		if (type == 1) {//查询全渠道
			routers = routerService.queryRouterByChannel(user, null);
		}else if (type == 2) {//查询指定渠道
			routers = routerService.queryRouterByChannel(user, keyword);
		}else{//查询指定设备
			Router router = routerService.queryRouterByDevNo(keyword).getData();
			routers = new ArrayList<>();
			routers.add(router);
		}
		if (routers == null || routers.size() == 0) {
			result = new BaseResult<>(false, "未查询到设备信息");
		}else{
			result = new BaseResult<>(routers);
		}
		return result;
	}
}
