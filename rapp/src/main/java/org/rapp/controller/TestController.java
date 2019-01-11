package org.rapp.controller;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.rapp.comm.exception.BaseControllerException;
import org.rapp.comm.util.FileOperateUtil;
import org.rapp.controller.base.BaseController;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.web.Rapp;
import org.rapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	
	@Resource
	UserService userService;

	@RequestMapping("/index")
	public String index() {
		if (true) {
			throw new BaseControllerException("我的名字叫吴思嘉");
		}
		
		return "test";
	}
	
	@RequestMapping("user")
	@ResponseBody
	public User user(@RequestParam("username") String username, HttpServletRequest request) {
		
		request.getSession().setAttribute("username", username);
		
		return userService.queryUserByName(username);
	}
	
	
	@RequestMapping("/set")
	@ResponseBody
	public String testSetHttpSession() {
		
		getSession().setAttribute("testKey", "testValue");
		
		return "set testKey=testValue";
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public String testGetHttpSession() {
		
		return "get testKey=" + getSession().getAttribute("testKey");
	}
	
	/**
	 * 文件上传
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload1.html", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("title", "文件上传");
		return "fileupload";
	}

	
	@Autowired
	FileOperateUtil fileOperateUtil;
	
	@Autowired
	Rapp rapp;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public BaseResult<String> uploadAdvertFile(@RequestParam("file")MultipartFile file, HttpServletRequest request){
		BaseResult<String> result = null;
		//判断文件是否选择
		if (file.isEmpty()) {
			result = new BaseResult<String>(false, "请选择文件后上传");
			return result;
		}
		String newName = request.getParameter("fileName");//当前广告的ID即设置为当前本地资源的名称
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
	
	
	
	
}
