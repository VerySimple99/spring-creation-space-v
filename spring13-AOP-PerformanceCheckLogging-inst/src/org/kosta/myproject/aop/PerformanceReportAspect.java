package org.kosta.myproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/*
 *   현 시스템의 서비스 메서드의 소요 시간을 체크해 로깅하는 Cross Cutting Concern 로직 정의한 AOP 클래스 
 */
@Aspect
@Component
public class PerformanceReportAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	//pointcut : AOP 적용 대상을 지정 model..  하위 패키지(board , member 와 같은 패키지 안의 자원)까지 모두 포함 
	//									   만약 model. 과 같이 명시하면 model 패키지 아래의 자원만 대상으로 한다
	//Around Advice : before , after, after-returning , after-throwing  네가지 advice 가 다 가능 
	@Around("execution(public * org.kosta.myproject.model..*Service.*(..))")
	public Object timeLogging(ProceedingJoinPoint point) throws Throwable{
		Object retValue=null;
		StopWatch watch=null;
		try {
			watch = new StopWatch();
			watch.start();// 시간 측정 시작
			retValue=point.proceed();//Target core 를 실행 
		}finally {
			watch.stop(); // 측정 종료
			long time = watch.getTotalTimeMillis();
			if(time>=500&&time<1000) {
				String className=point.getTarget().getClass().getName();
				String methodName=point.getSignature().getName();
				logger.warn("메서드 실행 소요시간 {} core class {}  method {}",time,className,methodName);
			}else if(time>=1000) {
				String className=point.getTarget().getClass().getName();
				String methodName=point.getSignature().getName();
				logger.error("메서드 실행 소요시간 {} core class {}  method {} ",time,className,methodName);
			}
		}
		return retValue;
	}
}























