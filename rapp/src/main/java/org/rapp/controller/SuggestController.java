package org.rapp.controller;

import org.apache.commons.lang3.StringUtils;
import org.rapp.pojo.account.User;
import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.entry.Suggest;
import org.rapp.service.Keys;
import org.rapp.service.SuggestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 建议控制类
 * @author 张芳
 *
 */
@RequestMapping("/suggest")
@Controller
public class SuggestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String SUGGEST = "suggest";
	
	@Autowired SuggestService suggestService;
	
	/**
	 * 建议视图
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() 
	{
		return SUGGEST + "/index";
	}
	
	/**
	 * 建议结果
	 * @return
	 */
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result() 
	{
		return SUGGEST + "/result";
	}
	
	/**
	 * 提交建议内容
	 * @param suggest
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(Suggest suggest, @SessionAttribute(Keys.LOGIN_USER)User user, RedirectAttributes redirectAttributes) 
	{
		if (StringUtils.isBlank(suggest.getTitle()) || StringUtils.isBlank(suggest.getContext())) {
			return "redirect:/suggest/index";
		}
		suggest.setCreateBy(user.getUsername());
		BaseResult<Suggest> result = suggestService.saveSuggest(suggest);
		if (!result.isSuccess()) {
			redirectAttributes.addFlashAttribute("suggest", suggest);
			return "redirect:/suggest/index";
		}
		logger.debug(result.getData().toString());
		return "redirect:/suggest/result";
	}
	
}
