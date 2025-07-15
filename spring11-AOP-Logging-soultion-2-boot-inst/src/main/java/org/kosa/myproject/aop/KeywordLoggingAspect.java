package org.kosa.myproject.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class KeywordLoggingAspect {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@After("execution(public * org.kosta.myproject.model.*Service.find*(..))")
	public void logging(JoinPoint point) {
		String className=point.getTarget().getClass().getName();
		String methodName=point.getSignature().getName();
		//core에 매개변수로 전달된 검색 키워드 정보를 받아온다 
		Object params[]=point.getArgs();
		String keyword="";
		for(int i=0;i<params.length;i++) {
			keyword+=params[i]+" ";
		}		
		logger.debug("AOP Cross Cutting Concern 로깅처리 core class {} method {} keyword {}",className,methodName,keyword);
	}
}










