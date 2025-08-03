// CustomerService.java - 고객 비즈니스 로직 서비스
package org.kosa.myproject.service;

import org.kosa.myproject.domain.Customer;
import org.kosa.myproject.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
@Controller: 웹 요청을 처리하는 역할
@Service: 여러 리포지토리를 조합하여 하나의 비즈니스 기능을 완성하는 역할
@Repository: 데이터베이스에 직접 접근하는 역할

@Service 메소드에 @Transactional을 붙이면, 여러 데이터베이스 작업이 하나의 트랜잭션으로 묶이게 됩니다.
만약 중간에 오류가 발생하면 전체 작업이 롤백되어 데이터의 일관성을 완벽하게 유지할 수 있습니다

 * 고객 관련 비즈니스 로직 처리 서비스
 * - 고객 등록 기능 제공
 * - 트랜잭션 관리
 * - 비즈니스 규칙 검증
 * 
 * 클래스 레벨에 @Transactional을 붙이면 * 
 * 해당 클래스 내의 모든 public 메소드에 트랜잭션이 적용되는 것이 핵심적인 의미
 * 
 *@Transactional(readOnly = true)
     *  "나는 이 트랜잭션에서 데이터를 변경하지 않을 거야"라고 명시함으로써, 
     *  영속성 프레임워크와 데이터베이스가 쓰기 작업에 필요한 모든 부가적인 작업을 건너뛰고 
     *  오직 읽기 작업에만 집중하게 만들어 성능을 향상시키는 효과
     *  또한 읽기 전용이라는 명확한 의도를 전달로 가독성 향상 
 * 
 * 
 * 
 * 
 * 또한 단일 DML 에도 @Transactional 을 사용하는 것을 강력 권장함 
 * 자동 롤백(Automatic Rollback) 보장 🛡️DML 쿼리가 성공적으로 실행되었더라도, 
 * 그 이후에 발생하는 비즈니스 로직에서 예상치 못한 예외가 발생하면 트랜잭션이 자동으로 롤백됩니다.
만약 트랜잭션을 사용하지 않았다면, DML 쿼리 결과는 데이터베이스에 영구적으로 반영된 채로 남아 데이터 불일치(inconsistent data) 상태가 발생할 수 있습니다. @Transactional은 이런 위험을 막아주는 안전장치 역할을 합니다.
예시: 사용자 정보를 UPDATE하는 쿼리 실행 후, 사용자에게 알림을 보내는 코드에서 오류가 발생한 경우, UPDATE 쿼리까지도 원상복구되어 데이터의 정합성을 지킬 수 있어요.

일관성 유지 및 동시성 제어 🤝
비록 단일 쿼리라도, 트랜잭션은 해당 작업이 다른 동시성 요청으로부터 격리(isolation)되도록 보장합니다. 이를 통해 데이터의 일관성을 유지하고, 예상치 못한 동시성 문제를 방지할 수 있습니다.
 * 
 * 
 ****AOP를 통한 트랜잭션 동작 방식****
 * 스프링 프레임워크는 **프록시(Proxy)**라는 기술을 이용해 AOP 방식으로 트랜잭션을 관리해요.

AOP를 통한 트랜잭션 동작 방식
프록시 객체 생성: 스프링은 @Transactional 어노테이션이 붙은 클래스에 대해 프록시(가짜) 객체를 만듭니다. 이 프록시 객체가 실제 비즈니스 로직을 담고 있는 객체를 대신하여 클라이언트의 요청을 받아요.

프록시를 통한 요청 가로채기: 클라이언트가 @Transactional 메소드를 호출하면, 요청은 실제 객체가 아닌 프록시 객체로 먼저 전달됩니다.

트랜잭션 부가 기능 적용: 프록시 객체는 요청을 가로챈 후, 스프링이 제공하는 **PlatformTransactionManager**를 사용하여 트랜잭션 시작, 커밋(commit), 롤백(rollback)과 같은 부가 기능을 먼저 수행합니다.

실제 비즈니스 로직 호출: 트랜잭션이 성공적으로 시작되면, 프록시는 그제서야 실제 객체의 메소드를 호출하여 비즈니스 로직을 실행해요.

트랜잭션 종료: 비즈니스 로직이 모두 성공적으로 완료되면 프록시는 트랜잭션을 커밋하고, 만약 예외가 발생하면 트랜잭션을 롤백합니다.

이러한 방식 덕분에 우리는 비즈니스 로직 코드에 트랜잭션 처리 코드를 직접 작성하지 않아도, @Transactional 어노테이션 하나만으로 깔끔하게 트랜잭션을 관리할 수 있습니다. 이것이 바로 관점 지향 프로그래밍(AOP)의 핵심이죠. 😊
 * 
 */
@Service
@Transactional  // 클래스 레벨에서 트랜잭션 관리
public class CustomerService {
    
    private final CustomerMapper customerMapper;
    
    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }
    
    /**
     * 신규 고객 등록
     * - 이메일 중복 체크는 DB 제약조건으로 처리
     * - 입력값 검증 후 등록 수행
     * 
     * @param customer 등록할 고객 정보
     * @return 등록된 고객 정보 (생성된 ID 포함)
     * @throws IllegalArgumentException 입력값이 잘못된 경우
     */
    public Customer registerCustomer(Customer customer) {
        // 입력값 검증
        validateCustomerInput(customer);
        
        // 고객 등록 수행
        int result = customerMapper.register(customer);
        
        if (result != 1) {
            throw new RuntimeException("고객 등록에 실패했습니다.");
        }
        
        return customer;  // 등록 후 생성된 ID가 포함된 객체 반환
    }
    
    /**
     * 고객 ID로 고객 정보 조회
     * 
     * @param customerId 조회할 고객 ID
     * @return 고객 정보 (없으면 null)
     * 
     * @Transactional(readOnly = true)
     *  "나는 이 트랜잭션에서 데이터를 변경하지 않을 거야"라고 명시함으로써, 
     *  영속성 프레임워크와 데이터베이스가 쓰기 작업에 필요한 모든 부가적인 작업을 건너뛰고 
     *  오직 읽기 작업에만 집중하게 만들어 성능을 향상시키는 효과
     *  또한 읽기 전용이라는 명확한 의도를 전달로 가독성 향상 
     * 
     * 
     * 
     */
    @Transactional(readOnly = true)  // 조회 전용 트랜잭션 (성능 최적화)
    public Customer getCustomerById(Long customerId) {
        if (customerId == null || customerId <= 0) {
            throw new IllegalArgumentException("올바른 고객 ID를 입력해주세요.");
        }
        
        return customerMapper.findById(customerId);
    }
    
    /**
     * 고객 입력값 검증 private 메서드
     * - 이름과 이메일 필수값 체크
     * - 이메일 형식 간단 검증
     */
    private void validateCustomerInput(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("고객 정보가 없습니다.");
        }
        
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("고객명을 입력해주세요.");
        }
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일을 입력해주세요.");
        }
        
        // 간단한 이메일 형식 검증
        if (!customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("올바른 이메일 형식을 입력해주세요.");
        }
    }
}