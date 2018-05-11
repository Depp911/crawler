package org.jayne.crawler.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * Created by jayne on 2018/5/11.
 */
public class HttpClientUtils {
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

    public static HttpClient getClient(){
        return CLIENT;
    }
}
