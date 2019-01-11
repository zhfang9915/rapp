package org.rapp.pojo.wechat.api;

import java.io.Serializable;

/**
 * 获取门店的secretKey
 * @author 张芳
 *
 */
public class SecretKeyData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7379293399770427458L;

	private String secretkey;

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

}
