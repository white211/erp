package com.naswork.erp.utils.framework.base;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.naswork.erp.utils.framework.enums.EnvType;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Program: Result
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-09 15:56:25
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Result {

    private Integer code = -1;

    private String error;

    private String remind;

    @JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss.SSS",
        timezone = "GMT+8"
    )
    private Date requestTime;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss.SSS",
            timezone = "GMT+8"
    )
    private Date responeTime;

    private Long costTime;

    private Object data;

    public Result(){
        if(Sys.CURRENT_ENV != EnvType.PROD){
            this.requestTime = new Date();
        }
    }
    public Result(Object data){
        this.code = 1;
        this.data = data;
    }

    public Result setOk(){
        calcCostTime(this);
        this.data = true;
        this.code = 1;
        return  this;
    }

    public Result setMoreError(String[] error){
        calcCostTime(this);
        this.code = Integer.valueOf(error[0]);
        this.error = error[1];
        return this;
    }

    public Result setMoreRemind(String[] remind){
        calcCostTime(this);
        this.code = Integer.valueOf(remind[0]);
        this.remind = remind[1];
        return this;
    }

    private static String concatStr(String[] msgs){
        StringBuffer stringBuffer = new StringBuffer();
        String[] var2 = msgs;
        int var3 = msgs.length;

        for(int var4 = 0;var4<var3;++var3){
            String rem = var2[var4];
            stringBuffer.append(rem);
            stringBuffer.append(";");
        }
        String msg = stringBuffer.toString();
        if(msg.length()>0){
            msg = msg.substring(0,msg.length() - 1);
        }
        return msg;
    }

    public static boolean responseError(HttpServletResponse response, Result result){
        try{
            result.setRequestTime(null);
            result.setResponeTime(null);
            result.setCostTime(null);
            String string = JSONObject.toJSONString(result);
            response.setHeader("Content_Type","application/json;charset=UTF-8");
            response.getWriter().print(string);
            response.getWriter().close();
            return false;
        }catch (JsonProcessingException var3){
            var3.printStackTrace();
            return false;
        }catch (IOException var4){
            var4.printStackTrace();
            return false;
        }
    }

    private static void calcCostTime(Result result){
        if(Sys.CURRENT_ENV != EnvType.PROD){
            result.setResponeTime(new Date());
            if(result.getRequestTime() != null){
                result.setCostTime(result.getResponeTime().getTime()-result.getRequestTime().getTime());
            }
        }
    }



}


