package org.rapp.comm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.rapp.comm.exception.WeChatApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * HttpClient 请求
 * @author 张芳
 *
 */
public class HttpClientHelper {
    protected static Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);

    /**
     * 发起Get请求
     * @param url
     * @param params
     * @return
     * @throws WeChatApiException
     */
    public static String execGet(String url, Map<String, String> params) throws WeChatApiException{
        return HttpClientHelper.execGet(url, params, 5);
    }

    /**
     * 发起Get请求
     * @param url
     * @param params
     * @param seconds
     * @return
     * @throws WeChatApiException
     */
    @SuppressWarnings("deprecation")
    public static String execGet(String url, Map<String, String> params, int seconds) throws WeChatApiException{
        String result = null;
        HttpClient client = new HttpClient();
        client.setConnectionTimeout(seconds * 1000);
        GetMethod method = new GetMethod(url);
        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
        // Set parameters
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                paramList.add(new NameValuePair(key, params.get(key)));
            }
            method.setQueryString(paramList.toArray(new NameValuePair[0]));
            logger.info("request Parameters: " + params.toString());
        }
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                byte[] responseBody = method.getResponseBody();
                result = new String(responseBody, "utf-8");
                logger.info("responseBody: " + result);
            }
        } catch (Exception e) {
            logger.error("Fatal error: " + e.getMessage());
            e.printStackTrace();
            throw new WeChatApiException("HTTP-501", "Fatal error: " + e.getMessage());
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return result;
    }

    /**
     * 发起POST请求
     * @param url
     * @param params
     * @return
     * @throws WeChatApiException
     */
    public static String execPost(String url, Map<String, String> params) throws WeChatApiException{
        return HttpClientHelper.execPost(url, params, 5);
    }

    /**
     * 发起POST请求
     * @param url
     * @param params
     * @param seconds
     * @return
     * @throws WeChatApiException
     */
    @SuppressWarnings("deprecation")
    public static String execPost(String url, Map<String, String> params, int seconds) throws WeChatApiException{
        String result = null;
        HttpClient client = new HttpClient();
        client.setConnectionTimeout(seconds * 1000);
        PostMethod method = new PostMethod(url);
        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
        // Set parameters
        if (params != null) {
            List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                paramsList.add(new NameValuePair(key, params.get(key)));
            }
            logger.info("request Parameters: " + params.toString());
            method.setRequestBody(paramsList.toArray(new NameValuePair[0]));
        }
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                byte[] responseBody = method.getResponseBody();
                result = new String(responseBody, "utf-8");
                logger.info("responseBody: " + result);
            }
        } catch (Exception e) {
            logger.error("Fatal error: " + e.getMessage());
            e.printStackTrace();
            throw new WeChatApiException("HTTP-501", "Fatal error: " + e.getMessage());
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return result;
    }
    /**
     * 发起POST JSON请求
     * @param url
     * @param params
     * @param seconds
     * @return
     * @throws WeChatApiException
     */
    @SuppressWarnings("deprecation")
	public static String execPostJson(String url, Map<String, Object> params, int seconds) throws WeChatApiException{
        String result = null;
        HttpClient client = new HttpClient();
        client.setConnectionTimeout(seconds * 1000);
        PostMethod method = new PostMethod(url);
        method.setRequestHeader("Content-Type", "application/json;charset=utf-8");
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
        // Set parameters
        try {
	        if (params != null) {
	            logger.info("request Parameters: " + params.toString());
	            RequestEntity requestEntity = new StringRequestEntity(JSONObject.toJSONString(params), "application/json", "utf-8");
	            method.setRequestEntity(requestEntity);
	        }
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                byte[] responseBody = method.getResponseBody();
                result = new String(responseBody, "utf-8");
                logger.info("responseBody: " + result);
            }
        } catch (Exception e) {
            logger.error("Fatal error: " + e.getMessage());
            e.printStackTrace();
            throw new WeChatApiException("HTTP-501", "Fatal error: " + e.getMessage());
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return result;
    }

    
}
