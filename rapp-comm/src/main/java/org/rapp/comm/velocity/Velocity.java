package org.rapp.comm.velocity;

import org.springframework.stereotype.Component;

@Component
public final class Velocity {

	/**
	 * 底部显示广告模版
	 */
	private VelocityTemplate adUnder;
	private VelocityTemplate adUnderJS;

	/**
	 * 屏幕中间显示广告
	 */
	private VelocityTemplate adCenter;
	private VelocityTemplate adCenterJS;

	/**
	 * 广告模版公用JS
	 */
	private VelocityTemplate adUploadJS;

	/**
	 * 注册邮件模版
	 */
	private VelocityTemplate emailRegister;
	/**
	 * 找回密码邮件模版
	 */
	private VelocityTemplate emialFindpwd;

	private Velocity() {
		super();
	}

	public VelocityTemplate getAdUnder() {
		return adUnder;
	}

	public void setAdUnder(VelocityTemplate adUnder) {
		this.adUnder = adUnder;
	}

	public VelocityTemplate getAdCenter() {
		return adCenter;
	}

	public void setAdCenter(VelocityTemplate adCenter) {
		this.adCenter = adCenter;
	}

	public VelocityTemplate getEmailRegister() {
		return emailRegister;
	}

	public void setEmailRegister(VelocityTemplate emailRegister) {
		this.emailRegister = emailRegister;
	}

	public VelocityTemplate getEmialFindpwd() {
		return emialFindpwd;
	}

	public void setEmialFindpwd(VelocityTemplate emialFindpwd) {
		this.emialFindpwd = emialFindpwd;
	}

	public VelocityTemplate getAdUnderJS() {
		return adUnderJS;
	}

	public void setAdUnderJS(VelocityTemplate adUnderJS) {
		this.adUnderJS = adUnderJS;
	}

	public VelocityTemplate getAdCenterJS() {
		return adCenterJS;
	}

	public void setAdCenterJS(VelocityTemplate adCenterJS) {
		this.adCenterJS = adCenterJS;
	}

	public VelocityTemplate getAdUploadJS() {
		return adUploadJS;
	}

	public void setAdUploadJS(VelocityTemplate adUploadJS) {
		this.adUploadJS = adUploadJS;
	}

}
