package org.rapp.pojo.online.router;

import javax.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.http.HttpSessionBindingEvent;

public class OnlineRouter implements HttpSessionBindingListener,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3866131360433833014L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String devNo;
	private OnlineRouterList rl = OnlineRouterList.getInstance();

	public OnlineRouter() {
	}

	public OnlineRouter(String devNo) {
		this.devNo = devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getDevNo() {
		return devNo;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		Enumeration<String> devs = rl.getRouterList();
		boolean flag = true;
		while (devs.hasMoreElements()) {
			if (devNo.equals(devs.nextElement())) {
				flag = false;
				break;
			}
		}
		if (flag) {
			rl.addRouter(devNo);
			logger.info("上线设备号：" + devNo);
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		rl.removeRouter(devNo);
		logger.info("下线设备号：" + devNo);
	}
}
