package org.rapp.repository;

import org.apache.ibatis.annotations.Param;
import org.rapp.pojo.entry.authentication.wechat.WechatAuth;
import org.rapp.pojo.entry.authentication.wechat.WechatAuthLog;

/**
 * 认证持久化
 * @author 张芳
 *
 */
public interface AuthenticationDao {

	/*-----------------------------微信WIFI认证流水信息--------------------------------------*/
	/**
	 * 保存认证流水信息
	 * @param auth
	 * @return
	 */
	int insertWechatAuthLog(WechatAuthLog wal);
	
	/**
	 * 根据流水ID获取认证流水信息
	 * @param id
	 * @return
	 */
	WechatAuthLog queryWechatAuthLogById(@Param("id")String id);
	
	
	
	
	/*-----------------------------微信WIFI认证信息--------------------------------------*/
	/**
	 * 保存微信公众号认证信息
	 * @param wa
	 * @return
	 */
	int insertWechatAuth(WechatAuth wa);
	
	/**
	 * 根据渠道查询微信公众号信息
	 * @param channelId
	 * @return
	 */
	WechatAuth queryWechatAuthByChannelId(@Param("channelId")String channelId);
	
	/**
	 * 更新门店开发中密钥
	 * @param wa
	 * @return
	 */
	int updateSecretKeyById(WechatAuth wa);
	
}
