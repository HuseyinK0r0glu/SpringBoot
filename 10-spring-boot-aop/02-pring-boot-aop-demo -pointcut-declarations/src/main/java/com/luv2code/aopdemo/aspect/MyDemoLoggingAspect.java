package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Advice on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute the method
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        }catch (Exception exception){
            // log the exception
            //System.out.println(exception.getMessage());


            // rethrow exception
            throw exception;

            // give user a custom message
            //result = "Major accident! But no worries, your private AOP helicopter is on the way!";
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // calculate the duration and display it
        long duration = end - begin;

        System.out.println("\n=====>>>Duration: " + duration/1000 + " seconds");

        return result;
    }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){

        // printing out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",throwing = "exc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,Throwable exc){

        // printing out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("Exception:" + exc);
    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))" ,
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint,List<Account> result){

        // printing out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call,
        System.out.println("\n=====>>> result is: " + result);

        // post process the data,modify it

        // convert the account names to uppercase
        convertTheAccountNamesToUppercase(result);

        System.out.println("\n=====>>> result is: " + result);
    }

    private void convertTheAccountNamesToUppercase(List<Account> result) {

        // loop through accounts
        for(Account account : result) {
            // get uppercase version of name
            String theUpperName = account.getName().toUpperCase();

            // update the name on the account
            account.setName(theUpperName);
        }


    }


    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n=====>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Signature: "+methodSignature);

        // display the method arguments
        Object[] args = joinPoint.getArgs();
        for(Object tempArg : args){
            System.out.println(tempArg);

            if(tempArg instanceof Account){

                // downcast and print account's specific stuff
                Account account = (Account) tempArg;

                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }

}
