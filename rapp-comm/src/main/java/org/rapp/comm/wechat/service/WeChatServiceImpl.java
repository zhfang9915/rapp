package org.rapp.comm.wechat.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.rapp.comm.redis.utils.JedisHelper;
import org.rapp.comm.util.HttpClientHelper;
import org.rapp.pojo.wechat.api.AccessTokenResponse;
import org.rapp.pojo.wechat.api.SecretKeyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component("weChatService")
public class WeChatServiceImpl implements WeChatService {
	
	Logger Logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private JedisHelper jedisHelper;
    
    private static final String APPID = "wx3fd8d6dc48581862";
    
    private static final String SECRET = "bf2661fc75a07210a1cabbb172822442";

    @Override
    public String getAccessToken() {
        String accessToken =  jedisHelper.getString(WeChatConstant.REDIS_KEY_ACCESS_TOKEN);
        if (StringUtils.isBlank(accessToken)) {
        	String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
    		accessTokenUrl = MessageFormat.format(accessTokenUrl, APPID,SECRET);
            String jsonResult = HttpClientHelper.execGet(accessTokenUrl, null);
            Logger.info("--->"+jsonResult);
            if (StringUtils.isNotBlank(jsonResult)) {
                AccessTokenResponse accessTokenResp = JSONObject.parseObject(jsonResult, AccessTokenResponse.class);
                if(accessTokenResp != null){
                    accessToken = accessTokenResp.getAccess_token();
                    jedisHelper.setString(WeChatConstant.REDIS_KEY_ACCESS_TOKEN, accessToken, WeChatConstant.ACCESS_TOKEN_TIMEOUT);
                }
            }
        }
        return accessToken;
    }
    
    
    @Override
    public String getSecretKey(String shopId, String ssid) {
    	String secretKey = null;
		String accessToken = getAccessToken();
		String secretKeyUrl = "https://api.weixin.qq.com/bizwifi/apportal/register?access_token={0}";
		secretKeyUrl = MessageFormat.format(secretKeyUrl, accessToken);
		Map<String, Object> params = new HashMap<>();
		params.put("shop_id", shopId);
		params.put("ssid", ssid);
		params.put("reset", false);
		String jsonResult = HttpClientHelper.execPostJson(secretKeyUrl, params, 5);
		SecretKeyResponse response = JSONObject.parseObject(jsonResult, SecretKeyResponse.class);
		if (response.getErrcode() == 0) {
			secretKey = response.getData().getSecretkey();
		}
    	return secretKey;
    }
    
    
}
