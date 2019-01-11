package org.rapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.rapp.comm.exception.BaseControllerException;
import org.rapp.comm.util.FileOperateUtil;
import org.rapp.comm.velocity.VelocityUtil;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.Role;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.param.AdvertParam;
import org.rapp.pojo.dto.param.AdvertResForm;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.AdvertItem;
import org.rapp.pojo.entry.AdvertTemp;
import org.rapp.pojo.entry.Channel;
import org.rapp.pojo.log.AdvertCheckLog;
import org.rapp.pojo.statistical.AdvertStatistical;
import org.rapp.pojo.statistical.StatisticalParam;
import org.rapp.pojo.web.Rapp;
import org.rapp.service.AdvertCheckService;
import org.rapp.service.AdvertService;
import org.rapp.service.AdvertTempService;
import org.rapp.service.BaseService;
import org.rapp.service.ChannelService;
import org.rapp.service.Keys;
import org.rapp.service.statistical.DataStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 广告配置控制器
 * @author 张芳
 *
 */
@RequestMapping("/apvert")
@Controller
public class AdvertController extends BaseController {

	@Autowired
	AdvertService advertService;
	
	@Autowired
	AdvertCheckService advertCheckService;
	
	@Autowired
	ChannelService channelService;
	
	@Autowired
	Rapp rapp;
	
	@Autowired
	VelocityUtil velocityUtil;
	
	@Autowired
	FileOperateUtil fileOperateUtil;
	
	@Autowired
	DataStatisticalService statisticalService;
	
	@Autowired
	BaseService service;
	
	@Autowired
	AdvertTempService tempService;
	
	private static final String ADVERT = "apvert/";
	
	/**
	 * 广告配置第一步
	 * @return
	 */
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public String toConfigurationPage() {
		BaseResult<Boolean> result = new BaseResult<Boolean>(true);
		//获取当前用户
		User user = (User) getSession().getAttribute(Keys.LOGIN_USER);
		//判断是否是身份 管理员不限制广告条数
		Role role = user.getRole();
		if (role.getAdvertMax()!=-1) {//如果不是管理员则进行广告数量校验
			int existingAd = advertService.totalAdvertExist(user.getUsername());
			if (existingAd >= role.getAdvertMax()) {
				//如果当前广告总数大于角色可配置的广告数量则返回错误提示页
				result = new BaseResult<>(false, "您当前可配置广告数量为：" + role.getAdvertMax() + ", 已配置：" + existingAd);
				return ADVERT + "result";
			}
		}
		getRequest().setAttribute("result", result);
		
		BaseResult<String> uuidResult = advertService.getUUID();
		if (!uuidResult.isSuccess()) {
			throw new BaseControllerException(uuidResult.getMsg());
		}
		getRequest().setAttribute("uuid", uuidResult.getData());
		
		BaseResult<List<Channel>> channels = channelService.queryChannelOfEnable(user);
		getRequest().setAttribute("channels", channels);
		
		List<AdvertTemp> temps = tempService.queryActiveTemp();
		getRequest().setAttribute("temps", temps);
		
		return ADVERT + "config";
	}
	
	/**
	 * 广告配置第一步提交
	 * @param advert
	 * @return
	 */
	@RequestMapping(value = "/config/submit", method = RequestMethod.POST)
	public String submitConfiguration(Advert advert){
		BaseResult<Advert> result = null;
		//获取当前用户
		User user = (User) getSession().getAttribute(Keys.LOGIN_USER);
		//判断是否是身份 管理员不限制广告条数
		Role role = user.getRole();
		if (role.getAdvertMax()!=-1) {//如果不是管理员则进行广告数量校验
			int existingAd = advertService.totalAdvertExist(user.getUsername());
			if (existingAd >= role.getAdvertMax()) {
				//如果当前广告总数大于角色可配置的广告数量则返回错误提示页
				result = new BaseResult<>(false, "您当前可配置广告数量为：" + role.getAdvertMax() + ", 已配置：" + existingAd);
				getRequest().setAttribute("result", result);
				return ADVERT + "result";
			}
		}
		
		//根据模板ID获取模板信息
//		AdvertTemp temp = tempService.queryAdvertTempById(advert.getTempId());
		BaseResult<AdvertTemp> temmmp = tempService.queryAdvertTempById(advert.getTempId());
		AdvertTemp temp = temmmp.getData();
		advert.setTemp(temp);
		
		getSession().setAttribute(ADVERT + advert.getAdvertId(), advert);
		
		return "redirect:/apvert/config/res/"+advert.getAdvertId();
	}
	
	/**
	 * 广告配置第二步
	 * @return
	 */
	@RequestMapping(value = "/config/res/{advertId}", method = RequestMethod.GET)
	public String configResource(@PathVariable("advertId")String advertId) {
		Advert advert = (Advert) getSession().getAttribute(ADVERT + advertId);
		if (advert == null) {
			return "redirect:/apvert/config";
		}
		getRequest().setAttribute("advert", advert);
		return ADVERT + "config_res";
	}
	
	/**
	 * 广告配置第二步提交
	 * @return
	 */
	@RequestMapping(value = "/config/res/submit/{advertId}", method = RequestMethod.POST)
	public String configResourceSubmit(AdvertResForm res, @PathVariable("advertId")String advertId) {
		User user = (User) getSession().getAttribute(Keys.LOGIN_USER);
		Advert advert = (Advert) getSession().getAttribute(ADVERT + advertId);
		if (advert == null) {
			return "redirect:/apvert/config";
		}
		advert.setCreateBy(user.getUsername());
		
		List<AdvertItem> items = new ArrayList<>();
		//判断文件是否是上传资源
		for (int i = 0; i < res.getAdvertUrl().size(); i++) {
			AdvertItem item = new AdvertItem();
			item.setAdvertId(advert.getAdvertId());
			item.setAdvertUrl(res.getAdvertUrl().get(i));
			item.setToUrl(res.getToUrl().get(i));
			if (res.getAdvertUrl().get(i).startsWith(rapp.getNginx())) {
				item.setLocalUrl(rapp.getUploadPath() + res.getAdvertUrl().get(i).replaceAll(rapp.getNginx(), ""));
			}
			items.add(item);
		}
		advert.setItems(items);
		
		//保存广告基本配置信息
		advertService.configurationAdvert(advert);
		getSession().removeAttribute(ADVERT + advertId);
		return "redirect:/apvert/detail/" + advert.getAdvertId();
	}
	
	/**
	 * 广告预览
	 * @param advert
	 * @return
	 */
	@RequestMapping(value = "/temp/show/{advertId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> showAdvertTemplate(AdvertResForm res, @PathVariable("advertId")String advertId) {
		User user = (User) getSession().getAttribute(Keys.LOGIN_USER);
		Advert advert = (Advert) getSession().getAttribute(ADVERT + advertId);
		List<AdvertItem> items = new ArrayList<>();
		//判断文件是否是上传资源
		for (int i = 0; i < res.getAdvertUrl().size(); i++) {
			AdvertItem item = new AdvertItem();
			item.setAdvertUrl(res.getAdvertUrl().get(i));
			item.setToUrl(res.getToUrl().get(i));
			items.add(item);
		}
		advert.setItems(items);
		
//		AdvertTemp temp = tempService.queryAdvertTempById(advert.getTempId());
//		advert.setTemp(temp);
		
		//生成广告展示模版
		Map<String, String> map = velocityUtil.createAdvertTemplate(advert, "");
		
		return new BaseResult<String>("<script>"+map.get("js")+"</script>"+map.get("data"));
	}
	
	/**
	 * 广告配置提交
	 * @param advert
	 * @return
	 */
	@RequestMapping(value = "/config/result/{advertNo}", method = RequestMethod.GET)
	public String configurationResult(@PathVariable("advertNo")String advertId, 
			@SessionAttribute(Keys.LOGIN_USER)User user){
		BaseResult<Advert> result = advertService.queryAdvertById(advertId, user);
		
		getRequest().setAttribute("result", result);
		return ADVERT + "result";
	}
	
	/**
	 * 广告资源上传
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> uploadAdvertFile(@RequestParam("advert_file")MultipartFile file, HttpServletRequest request){
		BaseResult<String> result = null;
		//判断文件是否选择
		if (file.isEmpty()) {
			result = new BaseResult<String>(false, "请选择文件后上传");
			return result;
		}
		String newName = System.currentTimeMillis() + "";//以时间戳命名
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();   
        Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            String realName = mFile.getOriginalFilename();
            newName += realName.substring(realName.indexOf('.'));
            break;
        }
		//从本地上传
		boolean uploadState = fileOperateUtil.uploadByName(request, "res", newName);//上传图片
		if (!uploadState) {
			result = new BaseResult<String>(false, "广告资源上传失败，请重试！");
			return result;
		}
		result = new BaseResult<String>(rapp.getNginx() + "res/" + newName);
		return result;
	}
	
	/**
	 * 列表页
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String table(@SessionAttribute(Keys.LOGIN_USER)User user) {
//		BaseResult<List<Channel>> channels = channelService.queryChannelAll(user);
//		getRequest().setAttribute("channels", channels);
		return ADVERT + "table";
	}
	
	/**
	 * 表格展示
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/table", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public TableResult<Advert> queryAdverts(@RequestBody AdvertParam param) {
		return advertService.queryAdvertForTable(param);
	}
	
	/**
	 * 列表页
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(AdvertParam param) {
		User user = (User) getSession().getAttribute(Keys.LOGIN_USER);
		param.setCreateBy(user.getUsername());
		BaseResult<List<Advert>> result = advertService.queryAdvertForList(param);
		getRequest().setAttribute("result", result);
		return ADVERT + "list";
	}
	
	
	/**
	 * 删除广告配置信息
	 * @param adverts
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> deleteAdvert(@RequestBody List<Advert> adverts) {
		
		return advertService.deleteAdvertConfig(adverts);
	}
	
	/**
	 * 删除广告配置信息
	 * @param adverts
	 * @return
	 */
	@RequestMapping(value = "/delete/{advertId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> deleteAdvert(@PathVariable("advertId")String advertId,
			@SessionAttribute(Keys.LOGIN_USER)User user) {
		List<Advert> adverts = new ArrayList<Advert>();
		Advert advert = advertService.queryAdvertById(advertId, user).getData();
		adverts.add(advert);
		return advertService.deleteAdvertConfig(adverts);
	}
	
	
	/**
	 * 更新广告第一步配置
	 * @param advert
	 * @return
	 */
	@RequestMapping(value = "/update/{advertId}", method = RequestMethod.GET)
	public String update(@SessionAttribute(Keys.LOGIN_USER)User user, 
			@PathVariable("advertId")String advertId) {
		//根据ID查询当前广告的信息
		BaseResult<Advert> result = advertService.queryAdvertById(advertId, user);
		getSession().setAttribute(ADVERT + advertId, result.getData());
		
		//查询渠道列表
		BaseResult<List<Channel>> channels = channelService.queryChannelOfEnable(user);
		//去除已选的渠道
		List<Channel> temp = new ArrayList<>();
		for (Channel exits : result.getData().getChannels()) {
			for (Channel all : channels.getData()) {
				if (exits.getChannelId().equals(all.getChannelId())) {
					exits.setChannelNameCh(all.getChannelNameCh());
					temp.add(all);
				}
			}
		}
		channels.getData().removeAll(temp);
		getRequest().setAttribute("result", result);
		getRequest().setAttribute("channels", channels);
		return ADVERT + "update";
	}
	
	
	/**
	 * 更新广告第一步配置提交
	 * @param advert
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	public String updateAdvert(@SessionAttribute(Keys.LOGIN_USER)User user, Advert advert) {
		Advert sAdvert = (Advert) getSession().getAttribute(ADVERT + advert.getAdvertId());
		sAdvert.setAdvertName(advert.getAdvertName());
		sAdvert.setChannelsId(advert.getChannelsId());
		sAdvert.setOffTime(advert.getOffTime());
		sAdvert.setWeight(advert.getWeight());
		sAdvert.setRemark(advert.getRemark());
		getSession().setAttribute(ADVERT + advert.getAdvertId(), sAdvert);
		return "redirect:/apvert/update/res/" + advert.getAdvertId();
	}
	
	/**
	 * 更新广告配置第二步
	 * @return
	 */
	@RequestMapping(value = "/update/res/{advertId}", method = RequestMethod.GET)
	public String updateResource(@SessionAttribute(Keys.LOGIN_USER)User user,
			@PathVariable("advertId")String advertId) {
		Advert advert = (Advert) getSession().getAttribute(ADVERT + advertId);
		if (advert == null) {
			return "redirect:/apvert/list";
		}
		System.out.println(super.toJson(advert));
		getRequest().setAttribute("advert", advert);
		return ADVERT + "update_res";
	}
	
	@RequestMapping(value = "/update/res/{advertId}", method = RequestMethod.POST)
	public String UpdateResourceSubmit(@SessionAttribute(Keys.LOGIN_USER)User user, AdvertResForm res,
			@PathVariable("advertId")String advertId) {
		Advert advert = (Advert) getSession().getAttribute(ADVERT + advertId);
		if (advert == null) {
			return "redirect:/apvert/list";
		}
		
		List<AdvertItem> items = new ArrayList<>();
		
		//判断文件是否是上传资源
		for (int i = 0; i < res.getAdvertUrl().size(); i++) {
			AdvertItem item = new AdvertItem();
			item.setAdvertId(advert.getAdvertId());
			item.setAdvertUrl(res.getAdvertUrl().get(i));
			item.setToUrl(res.getToUrl().get(i));
			if (res.getAdvertUrl().get(i).startsWith(rapp.getNginx())) {
				item.setLocalUrl(rapp.getUploadPath() + res.getAdvertUrl().get(i).replaceAll(rapp.getNginx(), ""));
			}
			items.add(item);
		}
		advert.setItems(items);
		
		//保存广告基本配置信息
		BaseResult<Advert> result = advertService.updateAdvertConfig(advert);
		if (!result.isSuccess()) {
			throw new BaseControllerException(result.getMsg());
		}
		getSession().removeAttribute(ADVERT + advertId);
		return "redirect:/apvert/detail/" + advert.getAdvertId();
	}
	
	/**
	 * 查看广告详情
	 * @param advertId
	 * @return
	 */
	@RequestMapping(value = "/detail/{advertId}", method = RequestMethod.GET)
	public String detail(@SessionAttribute(Keys.LOGIN_USER)User user,
			@PathVariable("advertId")String advertId) {
		//根据ID查询当前广告的信息
		BaseResult<Advert> result = advertService.queryAdvertById(advertId, user);
		//查询渠道列表
		BaseResult<List<Channel>> channels = channelService.queryChannelAll(user);
		List<Channel> temp = new ArrayList<>();
		for (Channel exits : result.getData().getChannels()) {
			for (Channel all : channels.getData()) {
				if (exits.getChannelId().equals(all.getChannelId())) {
					temp.add(all);
				}
			}
		}
		result.getData().setChannels(temp);
		
		StatisticalParam param = new StatisticalParam(service.getCreateBy(user));
		param.setAdvertId(advertId);
		param.setStatisticalType(StatisticalParam.DAY);
		BaseResult<List<AdvertStatistical>> datas = statisticalService.statisticalAdvertByDate(param);
		BaseResult<Map<String, String>> mapResult = null;
		if (datas.isSuccess()) {
			Map<String, String> adate = new HashMap<>();
			String push = "";
			String click = "";
			for (AdvertStatistical as : datas.getData()) {
				push += as.getPushCount() + ",";
				click += as.getClickCount() + ",";
			}
			adate.put("push", push.substring(0, push.length()-1));
			adate.put("click", click.substring(0, click.length()-1));
			mapResult = new BaseResult<Map<String,String>>(adate);
		}else{
			mapResult = new BaseResult<Map<String,String>>(false, datas.getMsg());
		}
		
		getRequest().setAttribute("advertId", advertId);
		getRequest().setAttribute("result", result);
		getRequest().setAttribute("mapResult", mapResult);
		return ADVERT + "detail";
	}
	
	
	/**
	 * 审核列表页
	 * @return
	 */
	@RequestMapping(value = "/uncheck", method = RequestMethod.GET)
	public String uncheck() {
		return ADVERT + "uncheck";
	}
	
	/**
	 * 查询待审核列表
	 * @return
	 */
	@RequestMapping(value = "/uncheck/list", method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public TableResult<Advert> listUnCheckAdvert() {
		String limit = getRequest().getParameter("limit");
		String offset = getRequest().getParameter("offset");
		AdvertParam param = new AdvertParam();
		param.setState("1");//查询待审核
		param.setLimit(limit);
		param.setOffset(offset);
		return advertService.queryAdvertForTable(param);
	}
	
	@RequestMapping(value = "/check/{advertId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> checkAdvert(@PathVariable("advertId")String advertId) {
		int state = Integer.valueOf(getRequest().getParameter("state"));
		if (state < 0 && state > 4) {
			return new BaseResult<String>(false, "您的操作未被定义");
		}
		User user = (User)getSession().getAttribute(Keys.LOGIN_USER);
		Map<String, Object> param = transformMapParam(getRequest());
		param.put("checkBy", user.getUsername());
		
		return advertCheckService.checkAdvert(param);
	}
	
	/**
	 * 启用/禁用
	 * @param method
	 * @param advertId
	 * @return
	 */
	@RequestMapping(value = "/{method}/{advertId}", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> enableOrDisable(@SessionAttribute(Keys.LOGIN_USER)User user, 
			@PathVariable("method")String method, @PathVariable("advertId")String advertId) {
		if ("enable".equals(method)) {
			Advert advert = advertService.queryAdvertById(advertId, user).getData();
			if (advert.getState()==1) {
				return new BaseResult<>(false, "该广告还未审核,无法启用!");
			}
			return advertCheckService.enableOrDisableAdvert(advertId, "4");
		}else {
			return advertCheckService.enableOrDisableAdvert(advertId, "0");
		}
		
	}
	
	/**
	 * 打开未读审核消息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/unread", method = RequestMethod.GET)
	public String unread(@SessionAttribute(Keys.LOGIN_USER)User user) {
		BaseResult<List<AdvertCheckLog>> result =  advertCheckService.queryUnreadLog(user.getUsername());
		getRequest().setAttribute("result", result);
		return ADVERT + "unread";
	}
	
}
