package org.rapp.comm.wechat.service;

/**
 * 微信常量
 * 
 * @author 张芳
 *
 */
public class WeChatConstant {

	/* Access_token在Redis缓存中的键 */
	public static final String REDIS_KEY_ACCESS_TOKEN = "WX_WT_ACCESS_TOKEN";

	/* Access_token在Redis缓存时间 */
	public static final long ACCESS_TOKEN_TIMEOUT = 7000;
}
