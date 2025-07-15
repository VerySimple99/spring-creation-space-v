package org.kosa.myproject.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Component // bean 으로 스프링 컨테이너가 관리하게 함 
@Aspect // AOP 공통관심사 Cross Cutting Concern 로직 정의한 클래스임을 스프링컨테이너에 알린다
public class CommonAspect {
	/*
	 	Before Advice: target 대상 메서드 실행전에 공통 기능이 적용 
	 	execution Pointcut : AOP 공통기능 적용 대상 지정 
	 	public : 대상 메서드 접근 제어자 
	 	* : 리턴타입 void 포함 모든 것을 대상
	 	org.kosta.myproject.model.* : 지정 패키지 하위를 대상 
	 	*Service : 서비스로 마치는 인터페이스 및 클래스를 대상 
	 	find* : find 로 시작되는 메서드를 대상 
	 	(..) : 매개변수 0~N 까지 모두를 대상으로 AOP 적용 대상을 선택한다 
	 */
	@Before("execution(public * org.kosa.myproject.model.*Service.find*(..))")
	public void execute(JoinPoint point) { // JoinPoint : 실제 수행되는 core 대상 정보를 저장  
		String className=point.getTarget().getClass().getName();
		String methodName=point.getSignature().getName();
		System.out.println("**AOP 방식 공통관심사항: 특정요구사항을 수행**"+className+" "+methodName);
	}
}






