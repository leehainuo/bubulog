package com.lihainuo.bubulog.common;



import com.lihainuo.bubulog.common.exception.BaseExceptionInterface;
import com.lihainuo.bubulog.common.exception.BusinessException;
import lombok.Data;

/**
 * @author lihainuo * @description: 统一响应结果类
 */
@Data
public class Result<T> {
    // 状态信息
    private boolean success;
    // 响应消息
    private String message;
    // 状态码
    private Integer code;
    // 响应数据
    private T data;

    // 成功响应
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setData(data);
        return result;
    }

    // 失败响应
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> fail(String errorMessage) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setMessage(errorMessage);
        return result;
    }

    public static <T> Result<T> fail(Integer errorCode, String errorMessage) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(errorCode);
        result.setMessage(errorMessage);
        return result;
    }


    public static <T> Result<T> fail(BusinessException e) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(Integer.valueOf(e.getErrorCode()));
        result.setMessage(e.getErrorMessage());
        return result;
    }

    public static <T> Result<T> fail(BaseExceptionInterface instance) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(Integer.valueOf(instance.getErrorCode()));
        result.setMessage(instance.getErrorMessage());
        return result;
    }

}
