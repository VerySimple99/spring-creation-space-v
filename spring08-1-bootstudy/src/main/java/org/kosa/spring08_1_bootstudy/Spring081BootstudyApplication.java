package org.kosa.spring08_1_bootstudy;

import org.kosa.spring08_1_bootstudy.model.FoodService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
🌱 Spring Boot란 무엇인가?
실생활 비유: 원룸 vs 일반 아파트
전통적인 Spring (일반 아파트):
입주할 때 가구, 가전제품, 인터넷, 전기, 수도 등을 일일이 설치
모든 설정을 개발자가 직접 해야 함
복잡하고 시간이 많이 소요
Spring Boot (원룸 풀옵션):
입주하자마자 모든 것이 준비되어 있음
필요한 설정들이 자동으로 완료
바로 사용 가능!

 ## **Spring Boot의 핵심 철학**

### **1. Convention over Configuration (관례 우선 설정)**

**실생활 비유**: 자동차 운전

- 모든 자동차의 액셀러레이터는 오른쪽, 브레이크는 왼쪽
- 매번 새로 배울 필요가 없음
- **관례**를 따르면 **설정**이 불필요

**Spring Boot에서:**

- `src/main/java`: Java 소스 코드 위치 (관례)
- `application.properties`: 설정 파일 위치 (관례)
- `8080` 포트: 기본 웹 서버 포트 (관례)
 ### **2. Auto Configuration (자동 설정)**

```java
// Spring Boot가 자동으로 해주는 일들
// 1. 웹 서버 설정 (Tomcat)
// 2. 데이터베이스 연결 설정
// 3. JSON 변환기 설정
// 4. 로깅 설정
// 5. 보안 기본 설정
// ... 수백 가지 설정들을 자동화!

```

### **3. Standalone (독립 실행)**
**이전 방식:**
- WAR 파일 생성 → 톰캣 서버에 배포 → 실행
**Spring Boot:**
```bash
java -jar myapp.jar  # 이것만으로 웹 서버까지 함께 실행!

```
 
 
🔍 @SpringBootApplication 어노테이션 해부하기     
 // @SpringBootApplication은 실제로 이것과 같습니다:
@SpringBootConfiguration  // = @Configuration : 역할: "이 클래스는 설정 클래스입니다"
@EnableAutoConfiguration // 필요한 설정들을 자동으로 해줘!
@ComponentScan // 이 패키지부터 시작해서 모든 컴포넌트를 찾아줘   
     
     
 @SpringBootConfiguration  // = @Configuration : 역할: "이 클래스는 설정 클래스입니다"    
// 예를 들어 이런 설정들을 할 수 있어요
@SpringBootApplication
public class MyApplication {

    @Bean  // 수동으로 Bean을 등록할 수 있음
    public String welcomeMessage() {
        return "Spring Boot에 오신 것을 환영합니다!";
    }     
     
  실생활 비유: 아파트 관리사무소
아파트(애플리케이션)의 모든 설정을 관리하는 곳   
     
     
 ### **2 @EnableAutoConfiguration**
**역할**: "Spring Boot야, 필요한 설정들을 자동으로 해줘!"    
 // Spring Boot가 자동으로 감지하고 설정하는 것들:
// - classpath에 web 라이브러리가 있으면 → 웹 서버 자동 설정
// - classpath에 H2 데이터베이스가 있으면 → DB 자동 설정
// - classpath에 JPA가 있으면 → JPA 자동 설정    
  **실생활 비유**: 스마트홈 시스템
- 새로운 기기를 연결하면 자동으로 인식하고 설정   
### **3. @ComponentScan**

**역할**: "이 패키지부터 시작해서 모든 컴포넌트를 찾아줘!"

```java
// 자동으로 찾아서 Bean으로 등록하는 것들:
@Component    // 일반적인 컴포넌트
@Service      // 비즈니스 로직
@Repository   // 데이터 접근
@Controller   // 웹 컨트롤러

```
**실생활 비유**: 아파트 입주자 명단 작성
- 각 호수를 돌아다니며 거주자 정보를 자동으로 수집     
     
  
     
Spring Boot App으로 실행할 경우, Spring Boot 1.3 버전부터는 
JMX(Java Management Extensions) 빈을 통해 STS가 애플리케이션에게 
"정중하게 종료해달라(graceful shutdown)"고 요청할 수 있습니다.

이는 IDE에서 정지(Stop) 버튼을 클릭했을 때, 애플리케이션이 데이터베이스 연결을 깔끔하게 끊거나, 
열려 있는 리소스를 정리하는 등 정상적인 종료 절차를 밟을 수 있도록 해줍니다.     
 
 */
@SpringBootApplication
public class Spring081BootstudyApplication implements CommandLineRunner {
	private final FoodService foodService;
	

	public Spring081BootstudyApplication(FoodService foodService) {
		super();
		this.foodService = foodService;
	}
	public static void main(String[] args) {
		 System.out.println("1. main 메서드 시작");
		SpringApplication.run(Spring081BootstudyApplication.class, args);
		System.out.println("3. main 메서드 종료");
		
	}
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("2. CommandLineRunner 실행!");
        System.out.println("   - Spring 컨테이너 준비 완료");
        System.out.println("   - 모든 Bean 생성 완료");
        System.out.println("   - 이제 우리 코드 실행 차례!");
        foodService.eat();
    }

}
