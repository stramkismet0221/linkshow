package com.ddzhuan.manage.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class UserServiceAdvice {
	
	@Pointcut("execution(* com.loki.service.impl.UserServiceImpl.*(..))")
	public void pointcut() {
	 }
			
	@Before(value="pointcut()")
	public void before(JoinPoint joinPoint) {

		System.out.println("Before :" + joinPoint.getArgs()[0]);
	}

	@After(value="pointcut()")
	public void after(JoinPoint joinPoint) {
		System.out.println("After ");
	}

	@AfterThrowing(value = "pointcut()", throwing = "ex")
	public void afterThrowing(Exception ex) {
		System.out.println("After Throwing : " + ex.getMessage());
	}

	@AfterReturning(value = "pointcut()", returning="rtv")
	public void afterReturning(JoinPoint jp,Object rtv) {
		System.out.println("After Returning : " + rtv);
	}
}

