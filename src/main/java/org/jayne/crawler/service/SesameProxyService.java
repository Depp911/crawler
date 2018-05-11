package org.jayne.crawler.service;

import org.jayne.crawler.data.proxy.ProxyContent;

import java.util.List;

/**
 * Created by jayne on 2018/5/9.
 */
public interface SesameProxyService {

    /**
     * 获取代理IP(HTTP)
     * @param size,个数
     * @return
     */
    List<ProxyContent> getHttpProxy(int size);

    /**
     * 获取代理IP(HTTPS)
     * @param size,个数
     * @return
     */
    List<ProxyContent> getHttpsProxy(int size);
}
