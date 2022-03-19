package com.fanfan.interfaces.model;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

public class ServerResult<T> implements Serializable {
    private Boolean success;
    private String msg;
    private T data;

    public ServerResult(T data) {
        this.success = true;
        this.data = data;
    }

    public ServerResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public static <T> ServerResult<T> success(T data) {
        return new ServerResult<>(data);
    }

    public static <T> ServerResult<T> fail(String msg) {
        return new ServerResult<>(false, msg);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
