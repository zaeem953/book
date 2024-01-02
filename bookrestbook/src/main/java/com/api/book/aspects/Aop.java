package com.api.book.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {

    @Pointcut("execution(* com.api.book.controllers.*.*(..))")
    public void loggingPointCut(){}

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("Before Posting : " + joinPoint.getSignature());
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("After Posting : "  + joinPoint.getSignature());
    }
}
