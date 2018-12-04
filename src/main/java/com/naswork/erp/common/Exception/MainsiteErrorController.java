package com.naswork.erp.common.Exception;

import com.naswork.erp.common.Constants;
import com.naswork.erp.common.Result;
import com.naswork.erp.common.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.naswork.erp.common.Constants.ERROR_PATH;
import static com.naswork.erp.common.Constants.UNAUTHORIZED_PATH;

/**
 * @Program: MainsiteErrorController
 * @Description: 系统错误拦截, 主要是针对404的错误
 * @Author: White
 * @DateTime: 2018-11-25 22:59:17
 **/
@Controller
public class MainsiteErrorController implements  ErrorController {

    /*
     * @Author: Create by white
     * @Datetime: 2018/11/26 9:31
     * @Descrition: handleError
     *              主要是登陆后的各种错误路径  404页面改为返回此json
     *              未登录的情况下,大部分接口都已经被shiro拦截,返回让用户登录了
     * @Params: []
     * @Return: com.naswork.erp.common.Result
     * @Throws:
     */
//    @RequestMapping(value = ERROR_PATH)
//    @ResponseBody
//    public Result handleError() {
//        return Result.requestByError(ResultCode.NOT_FOUND.getCode(),ResultCode.NOT_FOUND.getDesc());
//    }

    @RequestMapping(value = UNAUTHORIZED_PATH)
    @ResponseBody
    public Result handleUnauthorized(){
        return Result.requestByError(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getDesc());
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}





