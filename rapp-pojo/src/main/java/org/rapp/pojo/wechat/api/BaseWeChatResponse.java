package org.rapp.pojo.wechat.api;

import java.io.Serializable;

public class BaseWeChatResponse implements Serializable{
    /**
     * <p>Discription:[字段功能描述]</p>
     */
    private static final long serialVersionUID = 8988950993999433735L;

    /*请求状态码 200：请求成功，参照HttpStatus.SC_OK*/
    protected int status;

    /*错误信息*/
    protected String errMsg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
