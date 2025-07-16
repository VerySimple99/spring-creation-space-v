package org.kosa.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	요구사항 
	기존 회원 및 게시판 서비스 제공하는 시스템의 성능을 체크 
	서비스를 제공하는 각 메서드의 실행 소요시간을 레벨별로 체크해서 리포트를 제공해야 한다 

리포트는 0.5초 이상 1초 미만 소요시에는 log.warn ( core class name과 method name , 실행 소요시간 ) 
1초 이상시에는 log.error ( core class name과 method name , 실행 소요시간 ) 
로 report.log 파일에 로깅하도록 한다 
위와 같은 로그는 예외 발생 여부와 관계없이 모두 로깅되어야 한다 

개발단계 
1. 로그  단위테스트 

2. 소요시간체크 : Spring Framework에서 제공하는 StopWatch util class를 이용 -> 단위테스트 test.unit.TestStopWatch 

3. AOP Around Advice Unit Test : spring11-AOP-AroundAdvice

4. 위의 1. 2 의 기능을 서비스 각 메서드에 직접 코딩을 하는 것이 아니라 
    AOP를 적용해서 효율적인 개발과 운영을 하도록 한다 
    Aspect (관점 ) -> Core Concern ( 회원 및 게시판 서비스의 각 메서드 ) -> 기존 시스템 
    					Cross Cutting Concern ( 각 메서드의 실행 소요시간을 리포트 ) 
 	단위테스트 : Cross Cutting Concern 공통로직을  대상 메서드 시작 지점과 종료 지점에 적용을 해야 한다 
 	  			  => Around Advice Unit Test 개발단계 3 으로 지정함 	
    					
    					
    Cross Cutting Concern 정의한 클래스를 구현 
    PerformanceReportAspect  timeLogging(ProceedingJoinPoint) 
    AOP pointcut( 적용대상을 지정 ) 
    advice ( 공통기능 + 적용시점 ) -> around Advice 
    					
*/
@SpringBootApplication
public class Spring13AopPerformanceCheckBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring13AopPerformanceCheckBootApplication.class, args);
	}

}
