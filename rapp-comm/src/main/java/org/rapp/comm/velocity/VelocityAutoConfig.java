package org.rapp.comm.velocity;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VelocityAutoConfig {

	private static final Logger logger = LoggerFactory.getLogger(VelocityAutoConfig.class);
	
	@Resource
	Velocity velocity;

	/**
	 * 目录配置信息读取
	 */
	@PostConstruct
	public void autoInIt() {
		try {
			
			velocity.setAdUnder(new VelocityTemplate("velocity/ad_under.vm"));
			velocity.setAdCenter(new VelocityTemplate("velocity/ad_center.vm"));
			velocity.setAdUnderJS(new VelocityTemplate("velocity/ad_under_js.vm"));
			velocity.setAdCenterJS(new VelocityTemplate("velocity/ad_center_js.vm"));
			velocity.setAdUploadJS(new VelocityTemplate("velocity/ad_upload_js.vm"));
			
			velocity.setEmailRegister(new VelocityTemplate("velocity/email_register.vm"));
			velocity.setEmialFindpwd(new VelocityTemplate("velocity/email_findpwd.vm"));
			
		} catch (Exception e) {
			logger.error("VelocityAutoConfig 加载Velocity模版出错了");
		}
	}
}
