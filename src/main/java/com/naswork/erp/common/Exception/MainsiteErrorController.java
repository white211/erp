package com.naswork.erp.common.Exception;

import com.naswork.erp.common.Constants;
import com.naswork.erp.common.Result;
import com.naswork.erp.common.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.naswork.erp.common.Constants.ERROR_PATH;

/**
 * @Program: MainsiteErrorController
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-25 22:59:17
 **/
@Controller
public class MainsiteErrorController implements  ErrorController {

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Result handleError() {
        return Result.requestByError(ResultCode.NOT_FOUND.getCode());
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }


}
