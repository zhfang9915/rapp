package org.rapp.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rapp.comm.annotation.NotLogin;
import org.rapp.comm.annotation.Permission;
import org.rapp.comm.exception.BaseControllerException;
import org.rapp.comm.util.FileOperateUtil;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.dto.param.FirmWareParam;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.dto.result.TableResult;
import org.rapp.pojo.entry.FirmWare;
import org.rapp.service.FirmWareService;
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
 * 固件 Handler
 * @author 张芳
 */
@RequestMapping("/fw")
@Controller
public class FirmWareController extends BaseController {
	
	@Resource
	FirmWareService fwService;

	@Autowired
	FileOperateUtil fileOperateUtil;
	
	private final static String FIRMWARE = "farmware/";

	/**
	 * 固件管理页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@Permission("/fw/index")
	public String index () {
		
		return FIRMWARE + "farmware";
	}
	
	@RequestMapping(value = "/index/add", method = RequestMethod.GET)
	@Permission("/fw/index/add")
	public String addFirmWarePage() {
		getRequest().setAttribute("title", "新增固件");
		getRequest().setAttribute("method", "c");
		return FIRMWARE + "farmware_cu";
	}
	
	@RequestMapping(value = "/index/update/{fwId}", method = RequestMethod.GET)
	@Permission("/fw/index/update")
	public String updateFirmWarePage(@PathVariable("fwId")String fwId) {
		BaseResult<FirmWare> result = fwService.queryFirmWareById(fwId);
		getRequest().setAttribute("fw", result.getData());
		getRequest().setAttribute("title", "更新固件");
		getRequest().setAttribute("method", "u");
		return FIRMWARE + "farmware_cu";
	}
	
	/**
	 * 查询固件列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/fw/list")
	public TableResult<FirmWare> queryFirmWares (@RequestBody FirmWareParam param) { 
		return fwService.queryFirmWares(param);
	}
	
	/**
	 * 新增固件
	 * @param fw
	 * @return
	 */
	@RequestMapping(value = "/addFirmWare", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/fw/addFirmWare")
	public String addFirmWare(FirmWare fw, @RequestParam("farmware_file")MultipartFile file, HttpServletRequest request) {
		BaseResult<FirmWare> result = null;
		if (file.isEmpty()) {
			result = new BaseResult<FirmWare>(false, "上传的固件为空！");
			getRequest().setAttribute("result", result);
			return FIRMWARE + "farmware_result";
		}
		//获取文件前缀
		String seq = FileOperateUtil.getSeq();
		boolean uploadState = fileOperateUtil.upload(request, seq, "fw");//上传固件
		if (!uploadState) {
			result = new BaseResult<FirmWare>(false, "固件上传失败，请重试！");
			getRequest().setAttribute("result", result);
			return FIRMWARE + "farmware_result";
		}
		fw.setFwName(file.getOriginalFilename());//固件名称
		fw.setFwPath(fileOperateUtil.initFilePath(file.getOriginalFilename(), seq, "fw"));//固件存放目录
		result = fwService.addFirmWare(fw);
		getRequest().setAttribute("result", result);
		return FIRMWARE + "farmware_result";
	}
	
	/**
	 * 更新固件
	 * @param fw
	 * @return
	 */
	@RequestMapping(value = "/updateFirmWare", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@Permission("/fw/updateFirmWare")
	public String updateFirmWare(FirmWare fw) {
		BaseResult<FirmWare> result = fwService.editFirmWare(fw);
		getRequest().setAttribute("result", result);
		return FIRMWARE + "farmware_result";
	}
	
	/**
	 * 删除固件
	 * @param fw
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@Permission("/fw/delete")
	public BaseResult<String> deleteFirmWare(@RequestBody List<FirmWare> fws) {
		return fwService.removeFirmWare(fws);
	}
	
	
	@RequestMapping(value = "/download/{fwId}", method = RequestMethod.GET)
	@NotLogin
	public void downloadFirmware(@PathVariable("fwId")String fwId, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		FirmWare fw = fwService.queryFirmWareById(fwId).getData();
		if (fw == null) {
			throw new BaseControllerException("未查询到下载资源");
		}
		String userAgent = request.getHeader("User-Agent");
		byte[] bytes = userAgent.contains("MSIE") ? fw.getFwName().getBytes() : fw.getFwName().getBytes("UTF-8");
		String fileName = new String(bytes, "ISO-8859-1");
		response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
		FileOperateUtil.download(fw.getFwPath(), response.getOutputStream());
	}
	
}
