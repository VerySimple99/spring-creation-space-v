package org.kosta.myproject; // component scan base package 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 스프링 부트 프로젝트 설정 : 현 클래스가 있어야 스프링 부트 프로젝트가 실행할 수 있음 
// 이 어노테이션이 명시된 클래스의 패키지가 component scan 의 base package 가 되므로 
// ( component scan : bean 생성 관리 및 DI , 설정 로딩 ) 
// 이 클래스의 패키지 하위에서 프로그램 작업을 해야 한다 
@SpringBootApplication
public class Springbootstudy3MybatisThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springbootstudy3MybatisThymeleafApplication.class, args);
	}

}
