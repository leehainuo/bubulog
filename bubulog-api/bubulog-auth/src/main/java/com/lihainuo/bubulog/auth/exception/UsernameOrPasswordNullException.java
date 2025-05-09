package com.lihainuo.bubulog.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 *     自定义用户名、密码为空异常
 * </p>
 * @author lihainuo
 * @url www.lihainuo.com
 * @since 2025-05-08
 */

public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
