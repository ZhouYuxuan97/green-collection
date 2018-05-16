package com.jingling.orderservice.config;

import com.jingling.basic.enums.ReplyCode;
import com.jingling.basic.exceptions.ServiceException;
import com.jingling.basic.vo.ReplyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/4/2 21:37
 * @Description: 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 对全局异常进行处理
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandle(HttpServletRequest request, Exception exception) {
        if (exception instanceof ServiceException) {
            return ReplyResult.toJson(ReplyCode.CUSTOM.getCode(), exception.getMessage());
        }

        log.error("未定义异常",exception);
        return ReplyResult.toJson(ReplyCode.REQUEST_FAILED);
    }
}
