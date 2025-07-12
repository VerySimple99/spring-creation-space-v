package org.kosta.myproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// xml 기반으로 설정한 spring02의 applicationContext.xml 과 동일한 역할을 한다 
// 애너테이션,어노테이션 : 코드상에 메타데이터를 기술 ( 메타데이터 : 데이터의 데이터 => 설정정보 )
@Configuration
/*
       org.kosta.myproject 패키지 하위의 클래스들을 스캔해서 컴포넌트 계열( @Component .. )
	   의 애너테이션이 명시된 클래스들을 bean으로 생성하고 
	   의존관계 설정 즉 Dependency Injection(DI) 처리함  
	   즉 IOC , DI 를 적용시킴 
 */
@ComponentScan("org.kosta.myproject")
public class AppConfig {
	
}






