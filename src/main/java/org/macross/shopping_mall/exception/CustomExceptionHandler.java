package org.macross.shopping_mall.exception;

import org.macross.shopping_mall.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;




/**
 * 异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData ExceptionHandler(Exception e){

        logger.error("[ 系统异常 ]"+e);
        logger.error("",e);

        if (e instanceof RunTimeException){
            RunTimeException runTimeException = (RunTimeException) e;
            return JsonData.buildError(runTimeException.getCode(),runTimeException.getMsg());

        }
        return JsonData.buildError("全局异常，未知错误");

    }
}
