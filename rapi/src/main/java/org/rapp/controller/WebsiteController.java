package org.rapp.controller;

import java.util.List;

import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.entry.FirmWare;
import org.rapp.pojo.web.Rapp;
import org.rapp.service.FirmWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 官网服务
 * @author 张芳
 *
 */
@RequestMapping("/website")
@Controller
public class WebsiteController extends BaseController {

	@Autowired
	FirmWareService firmWareService;
	
	@Autowired
	Rapp rapp;
	
	@RequestMapping(value = "/firmware/list", method = RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String getFirmWares(String callbackparam) {
		BaseResult<List<FirmWare>> result = firmWareService.queryFirmWareDownload();
		if (result.isSuccess()) {
			for (FirmWare fw : result.getData()) {
				fw.setDownloadUrl(rapp.getRootPath() + "fw/download/" + fw.getFwId());
			}
		}
		return callbackparam + "(" + super.toJson(result) + ")";
	}
}
