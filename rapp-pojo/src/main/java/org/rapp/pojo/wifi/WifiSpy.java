package org.rapp.pojo.wifi;

import java.util.ArrayList;
import java.util.List;

import org.rapp.pojo.account.BaseEntity;

public class WifiSpy extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6342553647201946184L;
	
	private String spyId;
	/**
	 * FCS检测
	 */
	private int fcs;
	/**
	 * 符号全为6个！，用于区分非法数据帧
	 */
	private String mark;
	/**
	 * 路由器设备的ID，16位字符
	 */
	private String devId;
	/**
	 * 路由器的mac地址
	 */
	private String devMac;
	/**
	 * 携带的客户信息
	 */
	private List<FrameData> datas;

	public String getSpyId() {
		return spyId;
	}

	public void setSpyId(String spyId) {
		this.spyId = spyId;
	}

	public int getFcs() {
		return fcs;
	}

	public void setFcs(int fcs) {
		this.fcs = fcs;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevMac() {
		return devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}

	public List<FrameData> getDatas() {
		return datas;
	}

	public void setDatas(List<FrameData> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "WifiSpy [fcs=" + fcs + ", mark=" + mark + ", devid=" + devId + ", devmac=" + devMac + ", datas=" + datas
				+ "]";
	}

	public void parse(byte[] data) {
		int i = 0;
		int offset = 0;

		// ------------------开始解析data_frame_header--------------
		// 获取fcs 4个字节
		int fcs = Integer.valueOf(data[offset++]) << 24 | Integer.valueOf(data[offset++]) << 16
				| Integer.valueOf(data[offset++]) << 8 | Integer.valueOf(data[offset++]) << 0;

		this.fcs = fcs;

		// 获取mark
		byte[] mark = new byte[6];
		for (i = 0; i < 6; i++) {
			mark[i] = data[offset++];
		}
		this.mark = new String(mark);

		// 获取devid
		byte[] devid = new byte[16];
		for (i = 0; i < 16; i++) {
			devid[i] = data[offset++];
		}
		this.devId = new String(devid);

		// 获取devmac
		byte[] devmac = new byte[6];
		for (i = 0; i < 6; i++) {
			devmac[i] = data[offset++];
		}
		this.devMac = new String(devmac);

		// 获取fram_data结构的数量
		int data_len = Integer.valueOf(data[offset++]) << 24 | Integer.valueOf(data[offset++]) << 16
				| Integer.valueOf(data[offset++]) << 8 | Integer.valueOf(data[offset++]) << 0;

		// -----------开始解析frame_data-----------
		List<FrameData> datas = new ArrayList<>();
		int j = 0;
		for (j = 0; j < data_len; j++) {
			FrameData fd = new FrameData();
			// 获取sta_mac
			byte[] sta_mac = new byte[6];
			for (i = 0; i < 6; i++) {
				sta_mac[i] = data[offset++];
			}
			fd.setStaMac(new String(sta_mac));
			// 获取ap_mac
			byte[] ap_mac = new byte[6];
			for (i = 0; i < 6; i++) {
				ap_mac[i] = data[offset++];
			}
			fd.setApMac(new String(ap_mac));
			// 获取ssid
			byte[] ssid = new byte[34];
			for (i = 0; i < 34; i++) {
				ssid[i] = data[offset++];
			}
			fd.setSsId(new String(ssid));
			// 获取pwr
			int pwr = Integer.valueOf(data[offset++]) << 8 | Integer.valueOf(data[offset++]) << 0;
			fd.setPwr(pwr);
			// 获取channel
			int channel = Integer.valueOf(data[offset++]) << 24 | Integer.valueOf(data[offset++]) << 16
					| Integer.valueOf(data[offset++]) << 8 | Integer.valueOf(data[offset++]) << 0;
			fd.setChannel(channel);
			datas.add(fd);
		}
		this.datas = datas;
	}
}
