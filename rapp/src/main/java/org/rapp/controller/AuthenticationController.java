package org.rapp.controller;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.rapp.comm.util.MD5Utils;
import org.rapp.comm.wechat.service.WeChatService;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.entry.authentication.wechat.WechatAuth;
import org.rapp.pojo.entry.authentication.wechat.WechatAuthLog;
import org.rapp.service.ApiService;
import org.rapp.service.AuthenticationService;
import org.rapp.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 认证
 * @author 张芳
 *
 */
@RequestMapping("/authentication")
@Controller
public class AuthenticationController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	private static final String authentication = "authentication";
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	RouterService routerService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	
	/**
	 * 商户微信公众号配置页
	 */
	@RequestMapping(value = "/config/wechat", method = RequestMethod.GET)
	public String weChatConfig() {
		return authentication + "/wechat";
	}
	
	/**
	 * 保存微信公众号配置
	 * @return
	 */
	@RequestMapping(value = "/config/wechat", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<String> weChatConfigSubmit() {
		return null;
	}
	
	
	@RequestMapping(value = "/test")
	public String test() {
		return authentication + "/test";
	}
	
    @Autowired
    WeChatService weChatService;
    
	@RequestMapping(value = "/getAccessToken")
	@ResponseBody
	public String getAccessToken() {
		String secretKey = weChatService.getSecretKey("3246000", "QC_WIFI_4G");
		System.out.println("secretKey-->" + secretKey);
		//b1b9ac84d075b03dd4e19b86533fd315
		return secretKey;
	}
	
	/**
	 * 微信授权
	 * @return
	 */
	@RequestMapping(value = "/wechat/login")
	public String authentication(WechatAuthLog wa) {
		//判断该路由器是否前辰已注册的路由器
		Router router = routerService.queryRouterByDevNo(wa.getDevNo()).getData();
		if (router == null) {
			logger.info("微信授权认证：" + wa.getDevNo() + " 该路由器未在前辰平台注册");
			getRequest().setAttribute("exceptionMsg", "该路由器未在前辰平台注册，无法为您提供免费上网，请谅解！");
			return authentication + "/error";
		}
		if (!apiService.authentication(wa.getToken(), router)) {
			logger.info("微信授权认证：" + wa.getDevNo() + " 该设备信息异常");
			getRequest().setAttribute("exceptionMsg", "无法完成该路由器的验证，无法为您提供免费上网，请谅解！");
			return authentication + "/error";
		}
		
		//判断是否认证过，认证过则直接返回授权页(TODO 此处逻辑待商议)
		if(true){
			
		}
		
		//一言不合先记录认证数据
		BaseResult<String> result = authenticationService.saveWechatAuthLog(wa);
		if (!result.isSuccess()) {
			logger.info("记录认证流水失败");
			getRequest().setAttribute("exceptionMsg", "暂时无法为您提供免费上网，请稍后再试！");
			return authentication + "/error";
		}
		//回调函数的流水ID
		getRequest().setAttribute("cbid", result.getData());
		
		return authentication + "/auth_wechat";
	}
	
	/**
	 * 移动端/PC端认证页面
	 * @return
	 */
	@RequestMapping(value = "/wechat/page/{cbid}/{page}", method = RequestMethod.GET)
	public String authenticationPage(@PathVariable("cbid")String cbid, @PathVariable("page")String page) {
		getRequest().setAttribute("cbid", cbid);
		logger.info("cbid-->" + cbid);
		//根据cbid获取授权流水信息
		BaseResult<WechatAuthLog> walResult = authenticationService.selectWechatAuthLogById(cbid);
		if (!walResult.isSuccess()) {
			getRequest().setAttribute("exceptionMsg", "认证内容加载出错，请稍后再试！");
			return authentication + "/error";
		}
		WechatAuthLog wal = walResult.getData();
		
		//根据设备号查询授权公众号信息
		BaseResult<WechatAuth> waResult = authenticationService.selectWechatAuth(wal.getDevNo());
		if (!waResult.isSuccess()) {
			getRequest().setAttribute("exceptionMsg", "认证公众号加载出错，请稍后再试！");
			return authentication + "/error";
		}
		WechatAuth wa = waResult.getData();
		getRequest().setAttribute("wa", wa);
		switch (page) {
			case "pc":
				return authentication + "/auth_wechat_pc";
			case "mb":
				//认证流水信息，用户临时放行
				getRequest().setAttribute("wal", wal);
				//判断门店是否已经获取了secretKey  否则调接口获取
				if (StringUtils.isBlank(wa.getSecretKey())) {
					//前辰服务号开发中密钥
					/*
					 *	开发者ID：wx3fd8d6dc48581862
					 *	开发者密码AppSecret：bf2661fc75a07210a1cabbb172822442 
					 */
					
					String secretKey = weChatService.getSecretKey(wa.getShopId(), wal.getSsid());
					logger.info("secretKey-->" + secretKey);
					
					//更新secretKey数据库
					wa.setSecretKey(secretKey);
					authenticationService.updateSecretKeyById(wa);
				}
				getRequest().setAttribute("secretkey", wa.getSecretKey());
				return authentication + "/auth_wechat_mb";
			default :
				getRequest().setAttribute("exceptionMsg", "内容加载出错，请稍后再试！");
				return authentication + "/error";
		}
	}
	
	
	/**
	 * 微信公众号认证成功回调前辰服务器方法告知认证状态
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/wechat/callBack/{cbid}", method = RequestMethod.GET)
	public String callBack(@PathVariable("cbid")String cbid, HttpServletResponse response) throws IOException {
		logger.info("回调cbid-->" + cbid);
		//获取当前认证流水id的流水信息
		BaseResult<WechatAuthLog> result = authenticationService.selectWechatAuthLogById(cbid);
		if (!result.isSuccess()) {
			getRequest().setAttribute("exceptionMsg", result.getMsg());
			logger.info(result.getMsg());
			return authentication + "/error";
		}
		WechatAuthLog wal = result.getData();
		
		String routerUrl = "http://{0}:{1}/qcdog/auth?id={2}&token={3}";
//		String routerUrl = "http://{0}:{1}/rapp/auththentication/test2?id={2}&token={3}";
		String token = MD5Utils.getMD5(cbid + wal.getGw() + wal.getMac());
		routerUrl = MessageFormat.format(routerUrl, wal.getGw(), wal.getPort().toString(), cbid, token);
		logger.info(cbid + "路由器访问链接为：" + routerUrl);
		
		response.sendRedirect(routerUrl);
		return null;
	}
	
//	@RequestMapping(value = "/test2", method = RequestMethod.GET)
//	public String test2(HttpServletResponse response) throws IOException {
//		String id = getRequest().getParameter("id");
//		String token = getRequest().getParameter("token");
//		response.sendRedirect("http://www.minihu.qcwifi.ltd:8080/rapp/authentication/wechat/confim/"+id+"/"+token);
//		return null;
//	}
	
	/**
	 * 路由器请求后台验证下发token是否正确
	 * @return	Auth: code
	 * 	code枚举  1：成功 	0：错误	r：如果后台配置了需要认证成功后跳转到指定的页面   就返回一个Auth: r&portal=http://......
	 */
	@RequestMapping(value = "/wechat/confim/{id}/{token}", method = RequestMethod.GET)
	@ResponseBody
	public String routerConfimToken(@PathVariable("id")String id, @PathVariable("token")String token) {
		BaseResult<WechatAuthLog> result = authenticationService.selectWechatAuthLogById(id);
		if (!result.isSuccess()) {
			logger.info("不存在流水信息：{}", id);
			return "Auth: 0";
		}
		WechatAuthLog wal = result.getData();
		String serverToken = MD5Utils.getMD5(id + wal.getGw() + wal.getMac());
		if (!serverToken.equals(token)) {
			logger.info("token 校验未通过：{}", token);
			return "Auth: 0";
		}
		return "Auth: 1";
	}
	
	public static void main(String[] args) {
		System.out.println( MD5Utils.getMD5("20170703224703131740134.227.227.1f4:b7:e2:a6:43:86"));
	}
}
