package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    //@Before("execution(public void add*())")

    //@Before("execution(* add*(com.luv2code.aopdemo.Account,..))")

    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice(){
        System.out.println("\n=====>>> Executing @Before method");
    }

}
