package com.lihainuo.bubulog.common.exception;

import com.lihainuo.bubulog.common.Result;
import com.lihainuo.bubulog.common.enums.ResultEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @date 2025/5/6 13:01
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     * @return
     */
    @ExceptionHandler({ BusinessException.class })
    @ResponseBody
    public Result<Object> handleBusinessException(HttpServletRequest req, BusinessException e) {
        log.warn("{} 请求失败, errorCode: {}, errorMessage: {}",
                req.getRequestURI(), e.getErrorCode(), e.getErrorMessage()
        );
        return Result.fail(e);
    }

    /**
     * 捕获其他类型异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public Result<Object> handleOtherException(HttpServletRequest req, Exception e) {
        log.warn("{} 请求错误", req.getRequestURI(), e);
        return Result.fail(ResultEnum.SYSTEM_ERROR);
    }
}
