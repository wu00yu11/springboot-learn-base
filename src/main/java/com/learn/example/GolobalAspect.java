package com.learn.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @describe:
 * @create 2018-06-14 14:59
 **/
@Aspect
@Component
@Order(1)
public class GolobalAspect {
    private static Logger LOG = LoggerFactory.getLogger(GolobalAspect.class);
    @Pointcut("execution(public * com.learn.example.controller.*.*(..))")
    public void printRequestInfo(){

    }

    @Before("printRequestInfo()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();

        String queryParams = JSONArray.toJSONString(joinPoint.getArgs());
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"()";
        LOG.info("****** REQUEST INFO, URL: {}, METHOD: {}, PARAMS: {}", url, method, queryParams);

    }

    /**
     * 环绕处理:没有进行逻辑处理,保持了数据流正常流转,否则before不起作用
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("printRequestInfo()")
    public Object around(JoinPoint joinPoint) throws Throwable {
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
        return proceedingJoinPoint.proceed();
    }

    @After("printRequestInfo()")
    public void after(JoinPoint joinPoint) {

    }


    @AfterReturning(pointcut = "printRequestInfo()",
            returning="returnValue")
    public void afterReturning(JoinPoint point, Object returnValue){
        returnValue = (returnValue == null ? "":returnValue);
        String method = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() +"()";
        String result = JSON.toJSONString(returnValue);
        LOG.info("****** RESPONSE RESULT, METHOD: {}, RESULT: {}",method,result);
    }

    @AfterThrowing("printRequestInfo()")
    public void afterThrowing(JoinPoint joinPoint) {

    }
}
