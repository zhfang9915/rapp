package org.rapp.service;

import org.rapp.pojo.dto.result.BaseResult;
import org.rapp.pojo.entry.authentication.wechat.WechatAuth;
import org.rapp.pojo.entry.authentication.wechat.WechatAuthLog;

public interface AuthenticationService {

	/*-----------------------------微信WIFI认证流水信息--------------------------------------*/
	/**
	 * 保存认证流水信息,并返回认证流水号
	 * @param wal
	 * @return
	 */
	BaseResult<String> saveWechatAuthLog(WechatAuthLog wal);
	
	/**
	 * 根据流水ID获取认证流水信息
	 * @param id
	 * @return
	 */
	BaseResult<WechatAuthLog> selectWechatAuthLogById(String id);
	
	
	/*-----------------------------微信WIFI认证信息--------------------------------------*/
	/**
	 * 保存微信公众号认证信息
	 * @param wa
	 * @return
	 */
	BaseResult<String> saveWechatAuth(WechatAuth wa);
	
	/**
	 * 根据渠道查询微信公众号信息
	 * @param channelId
	 * @return
	 */
	BaseResult<WechatAuth> selectWechatAuth(String devNo);
	
	/**
	 * 更新门店开发者密钥
	 * @param wa
	 * @return
	 */
	BaseResult<Boolean> updateSecretKeyById(WechatAuth wa);
}
