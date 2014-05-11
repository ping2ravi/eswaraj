package com.eswaraj.base.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is aspects for all repository save method and work with TestObjectContextManager to keep
 * track of all objects being saved and then remove them after test is finished
 * 
 * @author ravi
 *
 */
@Aspect
@Component
public class RepositorySaveAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public RepositorySaveAspect(){
	}
	
	@Autowired
	private TestObjectContextManager testObjectContextManager;

	//@Before("execution(* com.rapzzy.domain.repo.*.save(..))")
	public void beforeRepoSave() {
	}
	
	@Around("execution(* com.eswaraj.domain.repo.*.save(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		logger.trace("{}, with Argument {}",pjp.getSignature(),pjp.getArgs()[0]);
		Object retVal = pjp.proceed();
		logger.trace("Method Returned  {}" , retVal);
		testObjectContextManager.addSavedObject(retVal);
		return retVal;
	}
}
