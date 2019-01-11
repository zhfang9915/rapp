package org.rapp.pojo.wechat.api;

import com.alibaba.fastjson.JSONObject;

public class AuthUrlResponse extends BaseWeChatResponse {

    /**
     * <p>Discription:[字段功能描述]</p>
     */
    private static final long serialVersionUID = 6646481755449178286L;

    /*Access Token*/
    private String redirect;

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
