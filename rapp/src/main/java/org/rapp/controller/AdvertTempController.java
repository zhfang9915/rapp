package org.rapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.rapp.comm.annotation.Permission;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.dto.param.AdvertTempParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.AdvertTemp;
import org.rapp.service.AdvertTempService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 广告模板管理控制器
 * @author 许延明
 */
@RequestMapping("/advertTemp")
@Controller
public class AdvertTempController extends BaseController {

	@Resource
	AdvertTempService tempService;
	
	private final static String ADVERT_TEMP = "apvert/";
	
	/**
	 * 广告模板管理页面
	 * @author 许延明
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@Permission("/advertTemp/index")
	public String index () {
		return ADVERT_TEMP + "advert_temp";
	}
	
	/**
	 * 查询模板列表
	 * @author 许延明
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/advertTemp/list")
	public TableResult<AdvertTemp> queryAdvertTemps (@RequestBody AdvertTempParam param) { 
		return tempService.queryAdvertTemps(param);
	}
	
	/**
	 * 新增模板页面
	 * @author 许延明
	 * @return
	 */
	@RequestMapping(value = "/index/add", method = RequestMethod.GET)
	@Permission("/advertTemp/index/add")
	public String addAdvertTempPage() {
		getRequest().setAttribute("title", "新增模板");
		getRequest().setAttribute("method", "c");
		return ADVERT_TEMP + "advert_temp_cu";
	}
	
	/**
	 * 新增
	 * @author 许延明
	 * @return
	 */
	@RequestMapping(value = "/addAdvertTemp", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/advertTemp/addAdvertTemp")
	public String addAdvertTemp(AdvertTemp temp) {
		BaseResult<AdvertTemp> result = tempService.addAdvertTemp(temp);
		getRequest().setAttribute("result", result);
		return ADVERT_TEMP + "advert_temp";
	}
	
	/**
	 * 删除
	 * @author 许延明
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/advertTemp/delete")
	public BaseResult<String> deleteAdvertTemps(@RequestBody List<AdvertTemp> temps) {
		return tempService.removeAdvertTemp(temps);
	}
	
	/**
	 * 更新模板页面
	 * @author 许延明
	 * @return
	 */
	@RequestMapping(value = "/index/update/{tempId}", method = RequestMethod.GET)
	@Permission("/advertTemp/index/update")
	public String updateAdvertTempPage(@PathVariable("tempId")int tempId) {
		BaseResult<AdvertTemp> result = tempService.queryAdvertTempById(tempId);
		getRequest().setAttribute("advertTemp", result.getData());
		getRequest().setAttribute("title", "更新模板");
		getRequest().setAttribute("method", "u");
		return ADVERT_TEMP + "advert_temp_cu";
	}
	
	/**
	 * 更新
	 * @author 许延明
	 * @return
	 */
	@RequestMapping(value = "/updateAdvertTemp", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/advertTemp/updateAdvertTemp")
	public String updateAdvertTemp(AdvertTemp temp) {
		BaseResult<AdvertTemp> result = tempService.editAdvertTemp(temp);
		getRequest().setAttribute("result", result);
		return ADVERT_TEMP + "advert_temp";
	}
	
}
