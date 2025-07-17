package org.kosa.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * SpringBoot 메인 애플리케이션 클래스
 * 
 * 핵심 개념: Spring Boot Auto Configuration
 * 
 * @SpringBootApplication 애노테이션은 다음 3개를 합친 것:
 * 1. @Configuration: 설정 클래스임을 명시
 * 2. @EnableAutoConfiguration: 자동 설정 활성화
 * 3. @ComponentScan: 컴포넌트 스캔 활성화
 * 
 * 자동 설정이 다음을 자동으로 구성합니다:
 * - DataSource (application.yml의 spring.datasource 설정 기반)
 * - SqlSessionFactory (MyBatis 핵심 객체)
 * - SqlSessionTemplate (MyBatis Spring 통합 객체)
 * - TransactionManager (트랜잭션 관리자)
 * - MyBatis Mapper 스캔 및 등록
 */
@SpringBootApplication
public class Spring15MybatisProductInstApplication {
	 /**
     * 애플리케이션 진입점
     * 
     * SpringApplication.run() 메서드가 하는 일:
     * 1. ApplicationContext 생성 및 초기화
     * 2. 자동 설정 적용
     * 3. 컴포넌트 스캔 및 빈 등록
     * 4. 내장 서버 시작 (Tomcat)
     * 5. CommandLineRunner 실행
     * 
     * @param args 명령행 인수
     */
	public static void main(String[] args) {
		SpringApplication.run(Spring15MybatisProductInstApplication.class, args);
	}

}
