package org.jayne.crawler.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.jayne.crawler.data.proxy.ProxyContent;
import org.jayne.crawler.data.proxy.ProxyResponse;
import org.jayne.crawler.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by jayne on 2018/5/9.
 */
@Service
public class SesameProxyServiceImpl implements SesameProxyService {

    private static String HTTP_URL = "http://webapi.http.zhimacangku.com/getip?type=2&pro=0&city=0&yys=0&port=1&pack=19284&ts=1&ys=1&cs=1&lb=1&sb=0&pb=45&mr=2&regions=&num=";
    private static String HTTPS_URL = "http://webapi.http.zhimacangku.com/getip?type=2&pro=0&city=0&yys=0&port=11&pack=19284&ts=1&ys=1&cs=1&lb=1&sb=0&pb=45&mr=2&regions=&num=";

    private static final HttpClient CLIENT;
    static {
        MultiThreadedHttpConnectionManager mgr = new MultiThreadedHttpConnectionManager();
        mgr.getParams().setDefaultMaxConnectionsPerHost(10);
        mgr.getParams().setMaxTotalConnections(10);
        mgr.getParams().setConnectionTimeout(5000);
        mgr.getParams().setSoTimeout(10000);
        HttpClientParams params = new HttpClientParams();
        params.setContentCharset("utf-8");
        CLIENT = new HttpClient(params, mgr);
    }

    @Override
    public List<ProxyContent> getHttpProxy(int size) {
        String url = HTTP_URL + size;
        ProxyResponse proxyResponse = getAntProxy(url);
        if(proxyResponse != null && proxyResponse.isSuccess()){
            return proxyResponse.getData();
        }
        return null;
    }

    @Override
    public List<ProxyContent> getHttpsProxy(int size) {
        String url = HTTPS_URL + size;
        ProxyResponse proxyResponse = getAntProxy(url);
        if(proxyResponse != null && proxyResponse.isSuccess()){
            return proxyResponse.getData();
        }
        return null;
    }

    /**
     * 通过API获取芝麻代理IP
     * @param url
     * @return
     */
    private ProxyResponse getAntProxy(String url){
        GetMethod httpMethod = new GetMethod(url);
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            int statusCode = CLIENT.executeMethod(httpMethod);
            if (200 != statusCode) {
                throw new IOException("SesameProxyServiceImpl: HttpUtils status code error! [" + statusCode + "] " + httpMethod.getURI().toString());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpMethod.getResponseBodyAsStream()));
            String str = "";
            while ((str = reader.readLine()) != null) {
                stringBuffer.append(str);
            }
        }catch (Exception e){
//            LogUtils.info("SesameProxyServiceImpl: getAntProxy, GetMethod: " + e);
        }finally {
            httpMethod.releaseConnection();
        }
//        LogUtils.info("SesameProxyServiceImpl: getAntProxy, response: " + stringBuffer.toString());
        ProxyResponse response = JsonUtils.fromJson(stringBuffer.toString(), ProxyResponse.class);
        return response;
    }
}
