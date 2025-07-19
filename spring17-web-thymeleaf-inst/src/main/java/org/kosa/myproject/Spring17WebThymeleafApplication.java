package org.kosa.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 메인 클래스
 * @SpringBootApplication: 자동 설정, 컴포넌트 스캔, 설정 클래스를 모두 포함
 * Thymeleaf 관련 자동 설정이 여기서 활성화됨
 */
@SpringBootApplication
public class Spring17WebThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring17WebThymeleafApplication.class, args);
	}

}
