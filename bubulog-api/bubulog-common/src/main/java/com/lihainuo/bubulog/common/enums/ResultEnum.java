package com.lihainuo.bubulog.common.enums;

import com.lihainuo.bubulog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-06
 */

@Getter
@AllArgsConstructor
public enum ResultEnum implements BaseExceptionInterface {
    // 系统异常状态码
    SYSTEM_ERROR("500", "系统异常..."),
    // 业务异常状态码
    PRODUCT_ERROR("400","业务异常..."),
    UNAUTHORIZED("401", "无访问权限，请先登录！"),
    LOW_AUTHORITY("405", "权限低级"),

    ;
    // 异常码
    private final String errorCode;
    // 错误信息
    private final String errorMessage;
}
