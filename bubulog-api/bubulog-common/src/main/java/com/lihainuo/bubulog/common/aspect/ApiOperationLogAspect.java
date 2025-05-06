package com.lihainuo.bubulog.common.aspect;

import com.lihainuo.bubulog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    /**
     * 获取注解的描述信息
     * @param joinPoint
     * @return
     */
    private String getApiOperationLoDescription(ProceedingJoinPoint joinPoint) {
        // 从JoinPont中获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 从方法签名取目标方法
        Method method = signature.getMethod();
        // 从目标方法中提取注解
        ApiOperationLog annotation = method.getAnnotation(ApiOperationLog.class);
        return annotation.description();
    }

    /**
     * 转JSON字符串方法
     * @return
     */
    private Function<Object,  String> toJsonStr() {
        return arg -> JsonUtil.toJsonString(arg);
    }

    /**
     * 以自定义的@ApiOperationLog注解为切点
     */
    @Pointcut("@annotation(com.lihainuo.bubulog.common.aspect.ApiOperationLog)")
    public void ApiOperationLog() {}

    /**
     * 环绕通知
     * @param joinPoint
     * @throws  Throwable
     */
    @Around("ApiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 请求开始时间
            long startTime = System.currentTimeMillis();
            // MDC
            MDC.put("traceId", UUID.randomUUID().toString());
            // 获取被请求的类和方法
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            // 请求入参
            Object[] args = joinPoint.getArgs();
            // 入参转JSON字符串
            String argsJsonStr = Arrays.stream(args).map(Object::toString).collect(Collectors.joining(","));
            // 获取Api功能描述
            String description = getApiOperationLoDescription(joinPoint);
            // 打印 -> 开始
            log.info("请求开始: [{}], 请求参数: {}, 请求类: {}, 请求方法: {}",
                    description, argsJsonStr, className, methodName);

            // 执行切点方法
            Object res = joinPoint.proceed(args);

            // 切点方法耗时
            long costTime = System.currentTimeMillis() - startTime;
            // 打印 -> 结束
            log.info("请求结束: [{}], 耗时: {}ms, 响应参数: {}",
                    description, costTime, JsonUtil.toJsonString(res));

            return res;


        } finally {
            MDC.clear();
        }
    }
}
