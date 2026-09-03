package com.example.manager_library.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LibraryLogger {
    private static  final Logger logger = LoggerFactory.getLogger(LibraryLogger.class);
    private static int visitorCount = 0;
    @AfterReturning(pointcut = "execution(* com.example.manager_library.service.BorrowingServiceImpl.confirmBorrowing(..)) || " +
                                "execution(* com.example.manager_library.service.BorrowingServiceImpl.returnBook(..))")
    public  void logBookStateChange(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        logger.info("The state book is changing. at method: " + methodName);
    }
    @Before("execution(* com.example.manager_library.controller.*.*(..))")
    public void countVisitors(){
        visitorCount++;
        logger.info("Have a visitor! current total visitor: " + visitorCount);
    }
}
