package com.example.mylogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingDemoApplication implements CommandLineRunner {
    /*
     * 메모리 효율성: 클래스당 하나의 Logger만 생성
		성능: 인스턴스 생성시마다 Logger를 만들지 않음
		관례: 거의 모든 Java 프로젝트에서 사용하는 표준 패턴
		단순함: DI 설정이나 주입 과정이 불필요
		
		DI 하지 않음 
		불필요한 복잡성: Logger는 단순한 도구인데 DI 설정이 과함
        성능 오버헤드: Spring Container가 관리할 필요 없음
        
        비즈니스 로직 의존성 → DI 사용 (Repository, Service 등)
        기술적 도구 → static final 사용 (Logger, Utility 등)
     */
    // Logger 생성 - SLF4J 인터페이스 사용
    private static final Logger logger = LoggerFactory.getLogger(LoggingDemoApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(LoggingDemoApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("애플리케이션이 시작되었습니다");
        
        // 로깅 레벨별 실습
        demonstrateLoggingLevels();
        
        // 실무 예제: 사용자 서비스 시뮬레이션
        simulateUserService();
        
        logger.info("애플리케이션이 종료됩니다");
    }
    
    /**
     * 로깅 레벨별 사용법 시연
     */
    private void demonstrateLoggingLevels() {
        logger.trace("TRACE: 매우 상세한 실행 과정"); // 가장 낮은 레벨
        logger.debug("DEBUG: 디버깅을 위한 정보"); // 개발 시에만 주로 사용
        logger.info("INFO: 일반적인 정보"); // 운영환경에서도 중요한 정보
        logger.warn("WARN: 주의가 필요한 상황"); // 잠재적 문제
        logger.error("ERROR: 오류가 발생했습니다"); // 심각한 문제
    }
    
    /**
     * 실무에서 사용하는 로깅 패턴
     */
    private void simulateUserService() {
        String username = "덴빠";
        String userRole = "강사";
        
        // {} 를 사용한 파라미터 바인딩 - 성능상 유리
        /*
         {}를 사용하는 것은 로그가 실제로 출력될 때만 비용을 지불하는 지연 평가(Lazy Evaluation) 방식이며, 이 과정에서 StringBuilder와 같은 효율적인 방법을 통해 문자열을 결합합니다. 따라서 성능상 훨씬 유리
         */
        logger.info("사용자 로그인 시도: {}", username);
        
        // 비즈니스 로직 실행
        boolean loginSuccess = processLogin(username);
        
        if (loginSuccess) {
            // 성공 시 INFO 레벨로 기록
            logger.info("사용자 {} (역할: {}) 로그인 성공", username, userRole);
        } else {
            // 실패 시 WARN 레벨로 기록
            logger.warn("사용자 {} 로그인 실패", username);
        }
        
        // 예외 처리 예제
        try {
            riskyOperation();
        } catch (Exception e) {
            // 예외 발생 시 ERROR 레벨로 스택트레이스와 함께 기록
            logger.error("위험한 작업 중 오류 발생: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 로그인 처리 시뮬레이션
     */
    private boolean processLogin(String username) {
        logger.debug("로그인 처리 시작: {}", username); // 디버깅 정보
        
        // 실제로는 데이터베이스 조회, 비밀번호 검증 등의 로직이 들어감
        if ("덴빠".equals(username)) {
            logger.debug("사용자 인증 성공: {}", username);
            return true;
        } else {
            logger.debug("사용자 인증 실패: {}", username);
            return false;
        }
    }
    
    /**
     * 예외 발생 시뮬레이션
     */
    private void riskyOperation() throws Exception {
        logger.debug("위험한 작업 시작");
        // 의도적으로 예외 발생
        throw new RuntimeException("예상치 못한 오류가 발생했습니다");
    }
}