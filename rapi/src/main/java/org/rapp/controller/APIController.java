package org.rapp.controller;

import java.util.List;
import java.util.Map;

import org.rapp.comm.util.MD5Utils;
import org.rapp.comm.velocity.VelocityUtil;
import org.rapp.pojo.dto.param.RouterParam;
import org.rapp.pojo.dto.param.api.AdvertPushParam;
import org.rapp.pojo.dto.param.api.JSCodePushParam;
import org.rapp.pojo.dto.param.api.PluginUpdateParam;
import org.rapp.pojo.dto.result.ApiResult;
import org.rapp.pojo.dto.result.PushAdvertResult;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.AdvertLog;
import org.rapp.pojo.entry.AdvertUploadLog;
import org.rapp.pojo.entry.Channel;
import org.rapp.pojo.entry.FirmWare;
import org.rapp.pojo.entry.JSCode;
import org.rapp.pojo.entry.PluginUnit;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.log.AdvertPushLog;
import org.rapp.pojo.log.RouterState;
import org.rapp.repository.ApiDao;
import org.rapp.service.AdvertLogService;
import org.rapp.service.ApiService;
import org.rapp.service.ChannelService;
import org.rapp.service.FirmWareService;
import org.rapp.service.PluginUnitService;
import org.rapp.service.util.SourceCodeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 广告流水控制类
 * 
 * @author 张芳
 *
 */
@RequestMapping("/api")
@Controller
public class APIController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	AdvertLogService advertLogService;
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	ChannelService channelService;
	
	@Autowired
	FirmWareService fwService;
	
	@Autowired
	PluginUnitService puService;
	
	@Autowired
	VelocityUtil velocityUtil;
	
	/**
	 * 插件升级(C语言请求)
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/plugin/update", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String updatePlugin(PluginUpdateParam param) {
		RouterState rs = new RouterState(param.getDevNo(), ApiDao.ONLINE);
		try {
			RouterParam rParam = new RouterParam();
			rParam.setDevNo(param.getDevNo());
			Router router = apiService.getRouter(rParam);
			if (router == null) {
				logger.info(param.getDevNo() + "认证未通过(无效的设备)");
				return "999\r\n认证未通过(无效的设备)";
			}
			if (!apiService.authentication(param.getToken(), router)) {
				logger.info(param.getDevNo() + "认证未通过(设备信息异常)");
				return "999\r\n认证未通过(设备信息异常)";
			}
			
			//根据设备获取固件信息
			FirmWare fw = fwService.queryFirmWareById(router.getFwId()).getData();
			if (fw == null || !fw.getVersion().equals(param.getFwv())) {
				logger.info(param.getDevNo() + "固件版本不一致");
				return "001\r\n固件版本不一致";
			}
			
			//对比交叉编译版本
			if (!fw.getCrossstool().equals(param.getGccv())) {
				logger.info(param.getDevNo() + "交叉编译版本不一致");
				return "001\r\n交叉编译版本不一致";
			}
			
			//查交叉询当前固件交叉编译版本获取对应的插件
			PluginUnit pu = puService.queryPluginUnitByCrossstool(fw.getCrossstool()).getData();
			if (pu == null) {
				logger.info(param.getDevNo() + "没有符合该固件的插件");
				return "001\r\n没有符合该固件的插件";
			}
			if (pu.getVersion().equals(param.getPv())) {
				logger.info(param.getDevNo() + "插件已是最新版本");
				apiService.saveRouterState(rs);
				return "003\r\n插件已是最新版本";
			}
			//返回下载链接和插件的MD5
			logger.info(param.getDevNo() + "返回下载地址");
			String token = MD5Utils.getMD5(pu.getMd5()+pu.getDownloadUrl());
			apiService.saveRouterState(rs);
			return "000\r\n" + pu.getMd5() + "\r\n" + pu.getDownloadUrl() + "\r\n" + token;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(param.getDevNo() + "系统异常");
			return "777\r\n系统异常";
		} 
	}
	
	
	/**
	 * 推送JS模版信息(C语言请求)
	 * @return
	 */
	@RequestMapping(value = "/pushJS", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String pushJS(JSCodePushParam param) {
		RouterState rs = new RouterState(param.getDevNo(), ApiDao.ACTIVE);
		try {
			//查询设备信息
			RouterParam rParam = new RouterParam();
			rParam.setDevNo(param.getDevNo());
			Router router = apiService.getRouter(rParam);
			if (router == null) {
				logger.info(param.getDevNo() + "认证未通过(无效的设备)");
				return "999\r\n认证未通过(无效的设备)";
			}
			if (!apiService.authentication(param.getToken(), router)) {
				logger.info(param.getDevNo() + "认证未通过(设备信息异常)");
				return "999\r\n认证未通过(设备信息异常)";
			}
			//获取JS
			JSCode js = apiService.getJSByChannelId(router.getChannelId());
			js.setCode(SourceCodeFormat.sourceCodeDiscode(js.getCode()));//转码
			logger.info(param.getDevNo() + "成功返回JS代码");
			String token = MD5Utils.getMD5(js.getServerIp() + js.getServerPort() + js.getCode());
			apiService.saveRouterState(rs);
			return "000\r\n" + token + "\r\n" + js.getServerIp() + "\r\n" 
					+ js.getServerPort() + "\r\n" + js.getCode();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(param.getDevNo() + "系统异常");
			return "777\r\n系统异常";
		}
	}
	
	
	/**
	 * 推送广告(ajax请求)
	 * @return
	 */
	@RequestMapping(value = "/pushRes", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ApiResult<PushAdvertResult> pushAdvert(AdvertPushParam param){
		try {
			logger.info("devNo-->" + param.getDevNo());
			//根据设备号获取设备信息
			RouterParam rParam = new RouterParam();
			rParam.setDevNo(param.getDevNo());
			rParam.setState("2");//激活状态
			Router router = apiService.getRouter(rParam);
			if (router == null) {
				logger.info(param.getDevNo() + "认证未通过(无效的设备)");
				return new ApiResult<>("999", "认证未通过(无效的设备)");
			}
			
			//验证渠道是否可用
			Channel channel = channelService.queryChannelById(router.getChannelId()).getData();
			if (channel == null || channel.getState() == 0) {
				//渠道不存在或者是未启用
				logger.info(param.getDevNo() + "该渠道未启用");
				return new ApiResult<>("001", "该渠道未启用");
			}
			
			//根据设备的渠道获取当前渠道下的所有广告信息
			List<Advert> adverts = apiService.queryAdvertsByChannel(router.getChannelId());
			if (adverts == null || adverts.size() == 0) {
				logger.info(param.getDevNo() + "该渠道下无可推送的广告资源");
				return new ApiResult<>("001", "该渠道下无可推送的广告资源");
			}
			
			//调用算法获取随机推送的广告
			Advert advert = apiService.getRandomAdvertByWeight(adverts);
			logger.info("推送的广告为：" + advert);
			//异步记录广告推送日志
			AdvertPushLog log = advertLogService.offer(param, advert.getAdvertId());
			
			//生成广告模版
			Map<String, String> map = velocityUtil.createAdvertTemplate(advert, log.getId());
			PushAdvertResult push = new PushAdvertResult(log.getId(), map.get("data"), map.get("js").split(","));
			ApiResult<PushAdvertResult> result = new ApiResult<PushAdvertResult>(push);
			result.setToken(MD5Utils.getMD5(log.getId() + map.get("data")));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(param.getDevNo() + "系统异常");
			return new ApiResult<>("777", "系统异常");
		}
	}
	
	
	/**
	 * 广告流水上传接口(ajax请求)
	 * @param log
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ApiResult<String> uploadAdvertLog(AdvertUploadLog uplog) {
		try {
			//查询设备信息
//			RouterParam rParam = new RouterParam();
//			rParam.setDevNo(log.getDevNo());
//			rParam.setState("2");//激活状态
//			Router router = apiService.getRouter(rParam);
//			if (router == null) {
//				logger.info(log.getDevNo() + "认证未通过(无效的设备)");
//				return new ApiResult<>("999", "认证未通过(无效的设备)");
//			}
//			if (!apiService.authentication(log.getToken(), router)) {
//				logger.info(log.getDevNo() + "认证未通过(设备信息异常)");
//				return new ApiResult<>("999", "认证未通过(设备信息异常)");
//			}
			AdvertLog log = new AdvertLog();
			switch (uplog.getState()) {
			case 1://显示
				log.setShowTime(uplog.getTimestamp());
				break;
			case 2://关闭
				log.setCloseTime(uplog.getTimestamp());
				break;
			case 3://点击
				log.setClickTime(uplog.getTimestamp());
				break;
			}
			log.setLogid(uplog.getLogid());
			advertLogService.offer(log);//加入队列
			logger.info(log.getLogid() + "流水上传成功");
			ApiResult<String> result = new ApiResult<>("成功");
			result.setToken(MD5Utils.getMD5("成功"));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(uplog.getLogid() + "系统异常");
			return new ApiResult<>("777", "系统异常");
		}
	}
	
	
	/**
	 * JSONP调用 解决跨域
	 * @param callbackparam
	 * @param uplog
	 * @return
	 */
	@RequestMapping(value = "/uplog", method = RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String uploadAdvertLogB(String callbackparam, AdvertUploadLog uplog) {
		logger.info("流水上传回调函数：{}", callbackparam);
		logger.info("流水上传参数：{}", uplog);
		try {
			AdvertLog log = new AdvertLog();
			switch (uplog.getState()) {
			case 1://显示
				log.setShowTime(uplog.getTimestamp());
				break;
			case 2://关闭
				log.setCloseTime(uplog.getTimestamp());
				break;
			case 3://点击
				log.setClickTime(uplog.getTimestamp());
				break;
			}
			log.setLogid(uplog.getLogid());
			advertLogService.offer(log);//加入队列
			logger.info(log.getLogid() + "流水上传成功");
			ApiResult<String> result = new ApiResult<>("成功");
			result.setToken(MD5Utils.getMD5("成功"));
			return callbackparam + "(" + super.toJson(result) + ")";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(uplog.getLogid() + "系统异常");
			return callbackparam + "(" + super.toJson(new ApiResult<>("777", "系统异常")) + ")";
		}
	}
	
}
