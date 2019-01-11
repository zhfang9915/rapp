package org.rapp.pojo.online.router;

import java.util.Vector;
import java.io.Serializable;
import java.util.Enumeration;

public class OnlineRouterList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1313526387330335058L;
	
	private static final OnlineRouterList userList = new OnlineRouterList();
	private Vector<String> v;

	private OnlineRouterList() {
		v = new Vector<String>();
	}

	public static OnlineRouterList getInstance() {
		return userList;
	}

	public void addRouter(String devNo) {
		if (devNo != null)
			v.addElement(devNo);
	}

	public void removeRouter(String devNo) {
		if (devNo != null)
			v.remove(devNo);
	}

	public Enumeration<String> getRouterList() {
		return v.elements();
	}

	public int getRouterCount() {
		return v.size();
	}
}
