package org.rapp.comm.wechat.service;

/**
 * 微信接口服务接口
 * @author 张芳
 *
 */
public interface WeChatService {

    /**
     * 获取access_token
     * @param url
     * @return
     */
    public String getAccessToken();

    /**
     * 获取门店的secretKey
     * @param shopId
     * @param ssid
     * @return
     */
    public String getSecretKey(String shopId, String ssid);
    
}
