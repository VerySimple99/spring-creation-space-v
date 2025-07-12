package org.kosa.myproject;

import org.kosa.myproject.model.GreetingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring082BootstudyCommandLineRunnerJoyApplication implements CommandLineRunner {

    // 의존성 주입 받기
    private final GreetingService greetingService;

    public Spring082BootstudyCommandLineRunnerJoyApplication(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void run(String... args) throws Exception {
        // 주입받은 서비스 사용
        String message = greetingService.getGreeting();
        System.out.println(message);
        System.out.println("✅ DI가 정상적으로 작동했습니다!");
    }

	public static void main(String[] args) {
		SpringApplication.run(Spring082BootstudyCommandLineRunnerJoyApplication.class, args);
	}

}
