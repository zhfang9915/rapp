package org.rapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.entry.Router;
import org.rapp.pojo.online.router.OnlineRouter;
import org.rapp.pojo.online.router.OnlineRouterList;
import org.rapp.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 在线设备统计
 * @author 张芳
 *
 */
@RequestMapping("/router")
@Controller
public class RouterOnlineController{
	
	@Autowired
	RouterService routerService;

	/**
	 * 在线设备统计
	 * @return
	 */
	@RequestMapping(value = "/online/{devNo}", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> online(@PathVariable("devNo")String devNo, HttpServletRequest request) {
		if (devNo == null) {
			return new BaseResult<String>(false, "参数错误");
		}
		//验证设备号是否存在
		Router router = routerService.queryRouterByDevNo(devNo).getData();
		if (router == null) {
			return new BaseResult<String>(false, "无效设备");
		}
		//保存session
		OnlineRouter olr = new OnlineRouter(devNo);
		request.getSession().setAttribute("OnlineRouter", olr);
		OnlineRouter or = (OnlineRouter) request.getSession().getAttribute("OnlineRouter");
		System.out.println(or.getDevNo());
		OnlineRouterList orl=OnlineRouterList.getInstance();
		System.out.println(orl.getRouterCount());
		return new BaseResult<String>("成功");
	}
}
