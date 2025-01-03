package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
  
    @AfterReturning("execution(* rs..*Bean.*(..))")
	public void logAfterServiceAccess(JoinPoint joinPoint) {
		System.out.println("Completed: " + joinPoint);
	}
    
    @Before("execution(* rs..*Bean.*(..))")
	public void logBeforeServiceAccess(JoinPoint joinPoint) {
		System.out.println("Started: " + joinPoint);
	}
}
