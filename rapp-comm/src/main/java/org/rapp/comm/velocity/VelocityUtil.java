package org.rapp.comm.velocity;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.rapp.pojo.email.EmailFindPwd;
import org.rapp.pojo.email.EmailRegister;
import org.rapp.pojo.entry.Advert;
import org.rapp.pojo.entry.AdvertItem;
import org.rapp.pojo.entry.AdvertTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VelocityUtil {

	@Autowired
	Velocity velocity;
	
	/**
	 * 广告模版
	 * @param advert
	 * @return
	 */
	public Map<String, String> createAdvertTemplate(Advert advert,String logid) {
//		VelocityContext context = new VelocityContext();
//		context.put("logid", logid);
		
		Map<String, String> map = new HashMap<>();
		// 流水上传JS
//		map.put("upload", velocity.getAdUploadJS().merge(context));
		
		AdvertTemp temp = advert.getTemp();
		//控制JS
		map.put("js", temp.getTempJs());
		String tfors = "";
		for (AdvertItem item : advert.getItems()) {
			tfors += temp.getTempFor().replace("$toUrl$", item.getToUrl()).replace("$advertUrl$", item.getAdvertUrl());
		}
		//广告主体
		map.put("data", temp.getTemp().replace("$advert$", tfors).replace("$logid$", logid));
		
		return map;
	}
	
	/**
	 * 邮件模版
	 * @param type
	 * @param object
	 * @return
	 */
	public String createEmailTemplate(String type, Object object) {
		VelocityContext context = new VelocityContext();
		switch (type) {
		case "register":
			//注册模版
			EmailRegister er = (EmailRegister)object;
			context.put("er", er);
			return velocity.getEmailRegister().merge(context);
		case "findPassword":
			//注册模版
			EmailFindPwd ef = (EmailFindPwd)object;
			context.put("ef", ef);
			return velocity.getEmialFindpwd().merge(context);
		default:
			return null;
		}
	}
}
