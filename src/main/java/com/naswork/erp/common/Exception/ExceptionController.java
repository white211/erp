package com.naswork.erp.common.Exception;

import com.naswork.erp.common.Result;
import com.naswork.erp.common.ResultCode;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @Program: ExceptionController
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-25 22:27:13
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        Result result = new Result();
        result.setStatus(ResultCode.ERROR.getCode());
        result.setMsg(ResultCode.ERROR.getDesc());
        result.setData("errorType "+ e.toString() + " errorLocation:" + errorPosition);
        logger.error("异常", e);
        return result;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodHandler() throws Exception {
        return Result.requestByError(ResultCode.INTERNAL_SERVER_ERROR.getCode(),"please check the request method");
    }

    @ExceptionHandler(CommonException.class)
    public Result commonException(Result result){
        return result;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result unauthorizedExceptionHandler(){
        return Result.requestByError(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getDesc());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public Result unauthenticatedException(){
        return Result.requestByError(ResultCode.ERROR.getDesc());
    }

}
