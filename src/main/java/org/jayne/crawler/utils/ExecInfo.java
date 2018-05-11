package org.jayne.crawler.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created by jayne on 2018/5/10.
 */
public class ExecInfo implements ExecResponse {
    private Date currentTime = new Date();
    private int code;
    private String apiErrorMessage;

    public ExecInfo() {
    }

    public ExecInfo(int code, String apiErrorMessage) {
        this.code = code;
        this.apiErrorMessage = apiErrorMessage;
    }

    public static ExecInfo fail(String message) {
        return new ExecInfo(520, message);
    }

    public static ExecInfo fail(int code, String message) {
        return new ExecInfo(code, message);
    }

    public static ExecInfo fail(ExecResponse execResponse) {
        return new ExecInfo(execResponse.getCode(), execResponse.getApiErrorMessage());
    }

    public static ExecInfo success() {
        return new ExecInfo(1, "");
    }

    public static ExecInfo success(String message) {
        return new ExecInfo(1, message);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return 1 == this.code;
    }

    @Override
    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getApiErrorMessage() {
        return apiErrorMessage;
    }

    public void setApiErrorMessage(String apiErrorMessage) {
        this.apiErrorMessage = apiErrorMessage;
    }
}
