package org.rapp.pojo.entry.authentication.wechat;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信认证参数实体类
 * 
 * @author 张芳
 *
 */
public class WechatAuthLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5470477861256762590L;

	/**
	 * gw: 路由器IP port: qcdog端口 devNo：路由器ID ip: 用户手机IP mac: 用户手机MAC
	 * mobile_name：用户手机名 url：当前用户访问的URL token: md5（同RAPI）
	 */

	private String id;
	/**
	 * 路由器IP
	 */
	private String gw;
	/**
	 * 路由器端口
	 */
	private Integer port;
	/**
	 * 当前路由器在前辰的设备编码
	 */
	private String devNo;
	/**
	 * 手机IP
	 */
	private String ip;
	/**
	 * 手机mac
	 */
	private String mac;
	/**
	 * 手机名称
	 */
	private String mobileName;
	/**
	 * 当前用户访问的URL
	 */
	private String url;
	/**
	 * 校验设备合法性的token devNo+mac+key-->md5
	 */
	private String token;
	/**
	 * 路由器的wifi名称
	 */
	private String ssid;
	/**
	 * 路由器的mac地址
	 */
	private String bssid;
	/**
	 * 请求认证时间
	 */
	private Date authTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGw() {
		return gw;
	}

	public void setGw(String gw) {
		this.gw = gw;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

}
