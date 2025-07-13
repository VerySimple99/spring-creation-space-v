package org.kosta.myproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.kosta.myproject.model.InventoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 *  Around Advice 를 학습하기 위한 예제 
 *  공통관심사 Cross Cutting Concern 을 어느 시점에 적용할 것인가 => Advice 
 *  
 *  before Advice, after Advice, after-returning Advice, after-throwing Advice 
 *  위 4가지 Advice를 Around Advice 에서 모두 처리할 수 있다 
 */
@Aspect
@Component
public class AroundLoggingAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Around("execution(public * org.kosta.myproject.model.*Service.*(..))")
	public Object aroundLogging(ProceedingJoinPoint point) throws Throwable {
		Object retValue = null;
		logger.debug("**AOP Before**");
		try {
			retValue = point.proceed();// 실제 Core Target 을 실행
			if (retValue != null) {
				logger.debug("**AOP After Returning** return value {}", retValue);
				// 테스트 차원에서 리턴값을 조작
				// retValue+="불목";
			}
		} catch (InventoryException ie) {
			logger.error("**AOP After-Throwing 재고부족=>공급처에 연락 **", ie);
		} finally {
			logger.debug("**AOP After**");
		}
		return retValue;
	}
}
