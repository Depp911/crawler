package org.jayne.crawler.data.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jayne on 2018/4/24.
 */
public class ProxyResponse {

    @JsonProperty("code")
    private int code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private List<ProxyContent> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ProxyContent> getData() {
        return data;
    }

    public void setData(List<ProxyContent> data) {
        this.data = data;
    }
}
