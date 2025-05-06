package com.lihainuo.bubulog.common.exception;

import lombok.Getter;

/**
 * @author: lihainuo
 * @url: www.lihainuo.com
 * @date: 2025/5/6 12:56
 * @description: 业务异常
 */

@Getter
public class BusinessException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    // 构造函数
    public BusinessException(BaseExceptionInterface instance) {
        this.errorCode = instance.getErrorCode();
        this.errorMessage = instance.getErrorMessage();
    }
}
