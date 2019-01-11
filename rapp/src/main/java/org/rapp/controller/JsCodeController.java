package org.rapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.rapp.comm.annotation.Permission;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.dto.param.JSCodeParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.JSCode;
import org.rapp.service.JSCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * JS源码 Handler
 * @author 张芳
 */
@RequestMapping("/code")
@Controller
public class JsCodeController extends BaseController {
	
	@Resource
	JSCodeService codeService;

	private final static String JSCODE = "jscode/";

	/**
	 * JS源码管理页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@Permission("/code/index")
	public String index () {
		
		return JSCODE + "js_code";
	}
	
	@RequestMapping(value = "/index/add", method = RequestMethod.GET)
	@Permission("/code/index/add")
	public String addJSCodePage() {
		getRequest().setAttribute("title", "新增JS源码");
		getRequest().setAttribute("method", "c");
		return JSCODE + "js_code_cu";
	}
	
	@RequestMapping(value = "/index/update/{codeId}", method = RequestMethod.GET)
	@Permission("/code/index/update")
	public String updateJSCodePage(@PathVariable("codeId")String codeId) {
		BaseResult<JSCode> result = codeService.queryJSCodeById(codeId);
		getRequest().setAttribute("code", result.getData());
		getRequest().setAttribute("title", "更新JS源码");
		getRequest().setAttribute("method", "u");
		return JSCODE + "js_code_cu";
	}
	
	/**
	 * 查询JS源码列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/code/list")
	public TableResult<JSCode> queryJSCodes (@RequestBody JSCodeParam param) { 
		return codeService.queryJSCodes(param);
	}
	
	/**
	 * 新增JS源码
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/addJSCode", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/code/addJSCode")
	public String addJSCode(JSCode code) {
		BaseResult<JSCode> result = codeService.addJSCode(code);
		getRequest().setAttribute("result", result);
		return JSCODE + "js_code_result";
	}
	
	/**
	 * 更新JS源码
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/updateJSCode", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/code/updateJSCode")
	public String updateJSCode(JSCode code) {
		BaseResult<JSCode> result = codeService.editJSCode(code);
		getRequest().setAttribute("result", result);
		return JSCODE + "js_code_result";
	}
	
	/**
	 * 删除JS源码
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/code/delete")
	public BaseResult<String> deleteAuth(@RequestBody List<JSCode> codes) {
		return codeService.removeJSCode(codes);
	}
	
	
}
