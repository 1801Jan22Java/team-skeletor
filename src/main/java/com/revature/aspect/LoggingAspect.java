package com.revature.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component(value="loggingAspect")
@Aspect

public class LoggingAspect {
	private static Logger log = Logger.getRootLogger();
	
	@AfterThrowing(pointcut="within(com.revature.*.*)")
	public void logAfterThrow(JoinPoint jp) {
		log.error(jp.getSignature() + "Throwing");
	}
	
	@AfterReturning(pointcut="execution(* com.revature.*.*.*(..))")
	public void logAfterRet(JoinPoint jp) {
		log.info(jp.getSignature());
	}
	
	
	
}