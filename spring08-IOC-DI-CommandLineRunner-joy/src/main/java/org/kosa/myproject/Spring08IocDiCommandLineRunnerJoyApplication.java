package org.kosa.myproject;

import org.kosa.myproject.model.WorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring08IocDiCommandLineRunnerJoyApplication implements CommandLineRunner {
	private final WorkerService workerService;

    public Spring08IocDiCommandLineRunnerJoyApplication(WorkerService workerService) {
        super();
        this.workerService = workerService;
    }

    public static void main(String[] args) {
       // System.out.println("📱 Spring Boot 애플리케이션 부팅 중...\n");
        SpringApplication.run(Spring08IocDiCommandLineRunnerJoyApplication.class, args);
       // System.out.println("\n📱 애플리케이션 정상 종료!");
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("🌟 Spring Boot IoC/DI 데모 시작!");
        System.out.println("=".repeat(60));
        System.out.println("🎯 CommandLineRunner 실행!");
        System.out.println("=".repeat(60));

        workerService.service();  
        
        printLearningPoints();

        System.out.println("\n✅ CommandLineRunner 실행 완료!");
        System.out.println("=".repeat(60));
    }

    private void printLearningPoints() {
        System.out.println("\n🎓 학습 포인트 정리:");
        System.out.println("1. 🔄 IoC (제어의 역전):");
        System.out.println("   - Spring Container가 객체 생성과 생명주기 관리");
        System.out.println("   - 개발자는 'new' 키워드 사용하지 않음");

        System.out.println("\n2. 💉 DI (의존성 주입):");
        System.out.println("   - 생성자를 통해 필요한 의존성 자동 주입");
        System.out.println("   - @Primary로 기본 구현체 설정");
        System.out.println("   - List<Tool>로 모든 구현체 한번에 주입");

        System.out.println("\n3. 📱 어노테이션 활용:");
        System.out.println("   - @Component: Spring Bean 등록");
        System.out.println("   - @Service: 비즈니스 로직 계층");
        System.out.println("   - @Primary: 다중 구현체 중 기본값 설정");

        System.out.println("\n4. 🚀 CommandLineRunner 장점:");
        System.out.println("   - 웹 환경 없이 Spring 기능 학습");
        System.out.println("   - 즉시 실행하여 결과 확인 가능");
        System.out.println("   - 콘솔 출력으로 직관적 이해");
    }
}
