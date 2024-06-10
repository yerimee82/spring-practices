package com.poscodx.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    @Before("execution(com.poscodx.aoptest.vo.ProductVo com.poscodx.aoptest.service.ProductService.find(String))")
    public void adviceBefore() {
        System.out.println("--- Before Advice ---");
    }
    @After("execution(com.poscodx.aoptest.vo.ProductVo com.poscodx.aoptest.service.ProductService.find(String))")
    public void adviceAfter() {
        System.out.println("--- After Advice ---");
    }

    @AfterReturning("execution(* *..*.ProductService.find(String))")
    public void adviceAfterReturning() {
        System.out.println("--- AfterReturning Advice ---");
    }

    @AfterThrowing(pointcut = "execution(* *..*.ProductService.*(..))", throwing = "ex")
    public void adviceAfterThrowing(Throwable ex) {
        System.out.println("--- AfterThrowing Advice " + ex + "---");
    }

    @Around("execution(* *..*.ProductService.*(..))")
    public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable{
        /* Before */
        System.out.println("--- Around(Before) --- ");

        /* Point Cut Method 실행 */
//        Object[] params = {"Camera"};
//        Object result = pjp.proceed(params);
        Object result = pjp.proceed();

        /* After */
        System.out.println("--- Aroung(After) ---");

        return result;
    }

}
