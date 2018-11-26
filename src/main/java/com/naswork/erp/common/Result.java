package com.naswork.erp.common;

import lombok.Data;

import java.io.Serializable;
import java.rmi.server.RemoteServer;

/**
 * @Program: Result
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-21 16:05:49
 **/
@Data
public class Result<T> implements Serializable {

    private Integer status;

    private String msg;

    private T data;

    public Result(){
        super();
    }

    public Result(Integer status,String msg,T data){
        this.data = data;
        this.msg = msg;
        this.status = status;
    }

    public Result(Integer status,String msg){
        this.status = status;
        this.msg = msg;
    }

    public Result(Integer status,T data){
        this.status = status;
        this.data = data;
    }

    public Result(T data){
        this.data = data;
    }

    public Result(Integer status){
        this.status = status;
    }

    public Result(String msg){
        this.msg = msg;
    }

    public static <T> Result<T> requestBySuccess(){
        return new Result<T>(ResultCode.SUCCESS.getCode());
    }

    public static <T> Result<T> requestBySuccessMessags(String msg){
        return new Result<T>(ResultCode.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> requestBySuccess(T data){
        return new Result<T>(ResultCode.SUCCESS.getCode(),data);
    }

    public static <T> Result<T> requestBySuccess(String msg,T data){
        return new Result<T>(ResultCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> Result<T> requestBySuccess(int code,String msg,T data){
        return new Result<T>(code,msg,data);
    }

    public static <T> Result<T> requestBySuccess(int code,String msg){
        return new Result<T>(code,msg);
    }

    public static <T> Result<T> requestBySuccess(int code,T data){
        return new Result<T>(code,data);
    }

    public static <T> Result<T> requestByError(){
        return new Result<T>(ResultCode.ERROR.getCode());
    }

    public static <T> Result<T> requestByErrorMessags(String msg){
        return new Result<T>(ResultCode.ERROR.getCode(),msg);
    }

    public static <T> Result<T> requestByError(T data){
        return new Result<T>(ResultCode.ERROR.getCode(),data);
    }

    public static <T> Result<T> requestByError(String msg,T data){
        return new Result<T>(ResultCode.ERROR.getCode(),msg,data);
    }

    public static <T> Result<T> requestByError(int code,String msg,T data){
        return new Result<T>(code,msg,data);
    }

    public static <T> Result<T> requestByError(int code,String msg){
        return new Result<T>(code,msg);
    }

    public static <T> Result<T> requestByError(int code,T data){
        return new Result<T>(code,data);
    }

}
