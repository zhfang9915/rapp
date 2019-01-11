package org.rapp.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rapp.comm.annotation.NotLogin;
import org.rapp.comm.annotation.Permission;
import org.rapp.comm.exception.BaseControllerException;
import org.rapp.comm.util.FileMD5Util;
import org.rapp.comm.util.FileOperateUtil;
import org.rapp.comm.util.MD5Utils;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.dto.param.PluginUnitParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.FirmWare;
import org.rapp.pojo.entry.PluginUnit;
import org.rapp.service.FirmWareService;
import org.rapp.service.PluginUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 插件 Handler
 * @author 张芳
 */
@RequestMapping("/plugin")
@Controller
public class PluginUnitController extends BaseController {
	
	@Resource
	PluginUnitService puService;
	
	@Resource
	FirmWareService fwService;
	
	@Autowired
	FileOperateUtil fileOperateUtil;

	private final static String PLUGIN = "plugin/";

	/**
	 * 插件管理页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@Permission("/plugin/index")
	public String index () {
		
		return PLUGIN + "plugin";
	}
	
	@RequestMapping(value = "/index/add", method = RequestMethod.GET)
	@Permission("/plugin/index/add")
	public String addPluginUnitPage() {
		getRequest().setAttribute("title", "新增插件");
		getRequest().setAttribute("method", "c");
		BaseResult<List<FirmWare>> fws = fwService.queryFirmWareAll();
		getRequest().setAttribute("fws", fws);
		return PLUGIN + "plugin_cu";
	}
	
	@RequestMapping(value = "/index/update/{pluginId}", method = RequestMethod.GET)
	@Permission("/plugin/index/update")
	public String updatePluginUnitPage(@PathVariable("pluginId")String pluginId) {
		BaseResult<PluginUnit> result = puService.queryPluginUnitById(pluginId);
		getRequest().setAttribute("pu", result.getData());
		getRequest().setAttribute("title", "更新插件");
		getRequest().setAttribute("method", "u");
		BaseResult<List<FirmWare>> fws = fwService.queryFirmWareAll();
		getRequest().setAttribute("fws", fws);
		return PLUGIN + "plugin_cu";
	}
	
	/**
	 * 查询插件列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/plugin/list")
	public TableResult<PluginUnit> queryPluginUnits (@RequestBody PluginUnitParam param) { 
		return puService.queryPluginUnits(param);
	}
	
	/**
	 * 新增插件
	 * @param pu
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/addPluginUnit", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/plugin/addPluginUnit")
	public String addPluginUnit(PluginUnit pu, @RequestParam("plugin_file")MultipartFile file, HttpServletRequest request) throws IOException {
		BaseResult<PluginUnit> result = null;
		if (file.isEmpty()) {
			result = new BaseResult<PluginUnit>(false, "上传的插件为空！");
			getRequest().setAttribute("result", result);
			return PLUGIN + "plugin_result";
		}
		//获取文件前缀
		String seq = FileOperateUtil.getSeq();
		boolean uploadState = fileOperateUtil.upload(request, seq, "pg");//上传插件
		if (!uploadState) {
			result = new BaseResult<PluginUnit>(false, "插件上传失败，请重试！");
			getRequest().setAttribute("result", result);
			return PLUGIN + "plugin_result";
		}
		pu.setPluginName(file.getOriginalFilename());//插件名称
		pu.setPluginPath(fileOperateUtil.initFilePath(file.getOriginalFilename(), seq, "pg"));//插件存放目录
		//获取文件的MD5
		String md5 = FileMD5Util.getMd5ByStream(file.getInputStream());
//		String md5 = MD5Utils.getMD5(pu.getFw().getVersion());
		pu.setMd5(md5);
		//计算下载链接
		String downloadUrl = super.webAppPath() + "plugin/download/" + md5;
		pu.setDownloadUrl(downloadUrl);
		result = puService.addPluginUnit(pu);
		//获取固件信息
//		FirmWare fw = fwService.queryFirmWareById(pu.getFw().getFwId()).getData();
//		result.getData().setFw(fw);
		getRequest().setAttribute("result", result);
		return PLUGIN + "plugin_result";
	}
	
	/**
	 * 更新插件
	 * @param pu
	 * @return
	 */
	@RequestMapping(value = "/updatePluginUnit", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/plugin/updatePluginUnit")
	public String updatePluginUnit(PluginUnit pu) {
		BaseResult<PluginUnit> result = puService.editPluginUnit(pu);
		//获取更新后的完整数据
		PluginUnit pUnit = puService.queryPluginUnitById(pu.getPluginId()).getData();
		result.setData(pUnit);
		getRequest().setAttribute("result", result);
		return PLUGIN + "plugin_result";
	}
	
	/**
	 * 删除插件
	 * @param pu
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/pu/delete")
	public BaseResult<String> deleteAuth(@RequestBody List<PluginUnit> pus) {
		return puService.removePluginUnit(pus);
	}
	
	
	@RequestMapping(value = "/download/{md5}", method = RequestMethod.GET)
	@NotLogin
	public void downloadFirmware(@PathVariable("md5")String md5, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		PluginUnit pu = puService.queryPluginUnitByMD5(md5).getData();
		if (pu == null) {
			throw new BaseControllerException("未查询到下载资源");
		}
		String userAgent = request.getHeader("User-Agent");
		byte[] bytes = userAgent.contains("MSIE") ? pu.getPluginName().getBytes() : pu.getPluginName().getBytes("UTF-8");
		String fileName = new String(bytes, "ISO-8859-1");
		response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
		FileOperateUtil.download(pu.getPluginPath(), response.getOutputStream());
	}
	
}
