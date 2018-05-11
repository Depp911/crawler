package org.jayne.crawler.data.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jayne on 2018/4/24.
 */
public class ProxyContent {

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("port")
    private int port;

    @JsonProperty("isp")
    private String isp;

    @JsonProperty("city")
    private String city;

    @JsonProperty("expire_time")
    private String expireTime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
