package org.jayne.crawler.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created by jayne on 2018/5/10.
 */
public class ExecResult<T> implements ExecResponse  {
    private Date currentTime = new Date();
    private int code;
    private String apiErrorMessage;
    private T data;

    public ExecResult() {
    }

    public ExecResult(int code, String message, T data) {
        this.code = code;
        this.apiErrorMessage = message;
        this.data = data;
    }

    public static <T> ExecResult<T> success() {
        return new ExecResult(1, "", (Object)null);
    }

    public static <T> ExecResult<T> success(T value) {
        return new ExecResult(1, "", value);
    }

    public static <T> ExecResult<T> fail(String message) {
        return new ExecResult(520, message, (Object)null);
    }

    public static <T> ExecResult<T> fail(String message, T data) {
        return new ExecResult(520, message, data);
    }

    public static <T> ExecResult<T> fail(int errorCode, String message) {
        return new ExecResult(errorCode, message, (Object)null);
    }

    public static <T> ExecResult<T> fail(ExecResponse execResponse) {
        return new ExecResult(execResponse.getCode(), execResponse.getApiErrorMessage(), (Object)null);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
