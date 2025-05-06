package com.lihainuo.bubulog.common.enums;

import com.lihainuo.bubulog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: lihainuo
 * @url: www.lihainuo.com
 * @date: 2025/5/6 12:50
 */

@Getter
@AllArgsConstructor
public enum ResultEnum implements BaseExceptionInterface {
    // 系统异常状态码
    SYSTEM_ERROR("500", "系统异常..."),
    // 业务异常状态码
    PRODUCT_ERROR("400","业务异常..."),
    ;
    // 异常码
    private final String errorCode;
    // 错误信息
    private final String errorMessage;
}
