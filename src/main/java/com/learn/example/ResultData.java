package com.learn.example;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 41600483548885466L;

    private String status;

    private String msg;

    private T data;

    public ResultData() {
    }

    public ResultData(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultData(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
