package com.naswork.erp.common.Exception;

import com.naswork.erp.common.Result;
import lombok.Data;

/**
 * @Program: CommonException
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-25 22:42:00
 **/

@Data
public class CommonException extends RuntimeException{

    private Result result;

    public CommonException(Result result){
        this.result = result;
    }


}
