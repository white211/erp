//package com.naswork.erp.common.Exception;
//
//import com.naswork.erp.common.Result;
//import com.naswork.erp.common.ResultCode;
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
//
///**
// * @Program: ExceptionController
// * @Description: 统一异常拦截
// * @Author: White
// * @DateTime: 2018-11-25 22:27:13
// **/
//@ControllerAdvice
//@ResponseBody
//public class ExceptionController {
//
//    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
//
//    @ExceptionHandler(value = Exception.class)
//    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        String errorPosition = "";
//        //如果错误堆栈信息存在
//        if (e.getStackTrace().length > 0) {
//            StackTraceElement element = e.getStackTrace()[0];
//            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
//            int lineNumber = element.getLineNumber();
//            errorPosition = fileName + ":" + lineNumber;
//        }
//        Result result = new Result();
//        result.setStatus(ResultCode.ERROR.getCode());
//        result.setMsg(ResultCode.ERROR.getDesc());
//        result.setData("errorType "+ e.toString() + " errorLocation:" + errorPosition);
//        logger.error("异常", e);
//        return result;
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public Result httpRequestMethodHandler() throws Exception {
//        /*
//        * @Author: Create by white
//        * @Datetime: 2018/11/26 9:32
//        * @Descrition: httpRequestMethodHandler
//    *                  GET/POST请求方法错误的拦截器
//     *                 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
//         *             所以定义了这个拦截器
//        * @Params: []
//        * @Return: com.naswork.erp.common.Result
//        * @Throws:
//        */
//        return Result.requestByError(ResultCode.INTERNAL_SERVER_ERROR.getCode(),"please check the request method");
//    }
//
//    /*
//     * @Author: Create by white
//     * @Datetime: 2018/11/26 9:32
//     * @Descrition: commonException
//     *               本系统自定义错误的拦截器
//     *              拦截到此错误之后,就返回这个类里面的json给前端
//     *              常见使用场景是参数校验失败,抛出此错,返回错误信息给前端
//     * @Params: [result]
//     * @Return: com.naswork.erp.common.Result
//     * @Throws:
//     */
//    @ExceptionHandler(CommonException.class)
//    public Result commonException(Result result){
//        return result;
//    }
//
//    /*
//     * @Author: Create by white
//     * @Datetime: 2018/11/26 9:33
//     * @Descrition: unauthorizedExceptionHandler
//     *               权限不足报错拦截
//     * @Params: []
//     * @Return: com.naswork.erp.common.Result
//     * @Throws:
//     */
//    @ExceptionHandler(UnauthorizedException.class)
//    public Result unauthorizedExceptionHandler(){
//        return Result.requestByError(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getDesc());
//    }
//
//    /*
//     * @Author: Create by white
//     * @Datetime: 2018/11/26 9:33
//     * @Descrition: unauthenticatedException
//     *               未登录报错拦截
//     *              在请求需要权限的接口,而连登录都还没登录的时候,会报此错
//     * @Params: []
//     * @Return: com.naswork.erp.common.Result
//     * @Throws:
//     */
//    @ExceptionHandler(UnauthenticatedException.class)
//    public Result unauthenticatedException(){
//        return Result.requestByError(ResultCode.ERROR.getCode(),"you have not permission");
//    }
//
//
//}
//
