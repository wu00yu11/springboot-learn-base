package com.learn.example;

import lombok.Data;

@Data
public class AppException extends Exception {

    private String status;
    private String msg;

    public AppException(String status,String msg) {
        super(msg);
        this.status = status;
        this.msg = msg;
    }

    public AppException(String status,String msg, Throwable cause) {
        super(msg, cause);
        this.status = status;
        this.msg = msg;
    }

}
