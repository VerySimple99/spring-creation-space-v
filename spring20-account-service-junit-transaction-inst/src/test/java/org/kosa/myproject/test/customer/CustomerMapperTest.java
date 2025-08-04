// CustomerMapperTest.java - 고객 매퍼 테스트
package org.kosa.myproject.test.customer;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.kosa.myproject.domain.Customer;
import org.kosa.myproject.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * CustomerMapper 기능 테스트
 * - 실제 DB 연결하여 테스트 수행
 * - @Transactional로 테스트 후 롤백 (DB 상태 유지)
 * 
 * 
 * 
@SpringBootTest의 역할 
이 어노테이션은 테스트를 위한 Spring ApplicationContext를 통째로 로딩함
애플리케이션의 모든 빈(Bean)들을 자동으로 스캔하고 설정해서, 실제 애플리케이션이 구동되는 것과 동일한 환경에서 테스트를 실행할 수 있게 해줌 

@Transactional의 역할 (테스트 환경에서) 🔄
일반적으로 이 어노테이션은 비즈니스 로직에 트랜잭션을 적용하여 
데이터의 일관성을 유지하는 데 사용됩니다. 
하지만 테스트 클래스에 사용될 때는 특별한 역할을 수행합니다.
각 테스트 메서드가 실행될 때마다 하나의 트랜잭션을 시작합니다.
그리고 테스트 메서드가 종료되면, 그 트랜잭션을 자동으로 롤백(Rollback) 시킴
이러한 자동 롤백 덕분에 각 테스트는 독립적으로 실행될 수 있으며, 
테스트 실행 중에 데이터베이스에 추가되거나 변경된 데이터가 
다음 테스트에 영향을 주지 않음

 */

@SpringBootTest
@Transactional  // 테스트 후 자동 롤백으로 DB 깔끔하게 유지
class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;
    
    /**
     * 고객 등록 기능 테스트
     * - 새 고객 등록 후 생성된 ID 확인
     */
    @Test
    void testRegister() {
        // Given: 테스트용 고객 데이터 준비
        Customer customer = new Customer("테스트고객", "test@example.com");
        System.out.println("등록 전: " + customer);
        
        // When: 고객 등록 실행
        int result = customerMapper.register(customer);
        // import static org.junit.jupiter.api.Assertions.assertEquals;
        // Then: 결과 검증
        assertEquals(1, result, "등록된 행의 수는 1이어야 함");
        assertNotNull(customer.getCustomerId(), "등록 후 고객 ID가 생성되어야 함");
        assertTrue(customer.getCustomerId() > 0, "생성된 고객 ID는 양수여야 함");
        
        System.out.println("등록 후: " + customer);
    }
    
    /**
     * 고객 조회 기능 테스트
     * - 고객 등록 후 ID로 조회하여 데이터 일치 확인
     * 
     "Given, When, Then"은 주로 소프트웨어 테스트에 사용되는 패턴
     테스트 시나리오를 작성할 때, 어떤 상황에서 어떤 행동을 했을 때 
       어떤 결과가 나타나는지를 명확하고 구조적으로 나타내는 방법
     
      1. Given (주어진 상황) 🎁
의미: 테스트를 시작하기 위한 초기 상태를 설정합니다. 어떤 데이터나 조건이 이미 준비되어 있어야 하는지를 정의해요.
예시:
"고객 '홍길동'이 존재하고, 잔액이 10,000원인 계좌가 있을 때"
"로그인된 사용자가 있을 때"

2. When (행동) ⏰
의미: 테스트하고자 하는 특정 행동을 정의합니다. 시스템에 어떤 이벤트나 액션이 발생하는지를 나타내요.
예시:
"해당 계좌에서 5,000원을 출금했을 때"
"사용자가 '로그아웃' 버튼을 클릭했을 때"

3. Then (결과) 🎯
의미: When에서 정의된 행동이 일어난 후 기대하는 결과를 정의합니다. 시스템의 상태가 어떻게 바뀌어야 하는지를 검증해요.
예시:
"계좌의 잔액이 5,000원으로 감소해야 한다."
"사용자의 상태가 '로그아웃'으로 변경되어야 한다."

Given  : 잔액이 10,000원인 계좌가 있다.
When   : 5,000원을 출금한다.
Then   : 계좌의 잔액은 5,000원이 되어야 한다.
And    : 출금 성공 메시지가 표시되어야 한다
     */
    @Test
    void testFindById() {
        // Given: 고객 등록
        Customer originalCustomer = new Customer("조회테스트", "find@example.com");
        customerMapper.register(originalCustomer);
        Long customerId = originalCustomer.getCustomerId();
        
        // When: 등록된 고객 조회
        Customer foundCustomer = customerMapper.findById(customerId);
        
        // Then: 조회 결과 검증
        assertNotNull(foundCustomer, "조회된 고객이 존재해야 함");
        assertEquals(customerId, foundCustomer.getCustomerId(), "고객 ID가 일치해야 함");
        assertEquals("조회테스트", foundCustomer.getName(), "고객명이 일치해야 함");
        assertEquals("find@example.com", foundCustomer.getEmail(), "이메일이 일치해야 함");
        
        System.out.println("조회된 고객: " + foundCustomer);
    }
}
