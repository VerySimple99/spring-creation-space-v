package org.kosa.myproject.test.customer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.kosa.myproject.domain.Customer;
import org.kosa.myproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * CustomerService 비즈니스 로직 테스트
 * - 실제 Spring 컨텍스트와 DB를 사용한 통합 테스트
 * - 비즈니스 로직과 예외 처리 검증
 */
@SpringBootTest
@Transactional  // 테스트 후 롤백으로 DB 상태 보존
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    
    /**
     * 정상적인 고객 등록 테스트
     * - 유효한 데이터로 고객 등록 후 결과 확인
     */
    @Test
    void testRegisterCustomer_Success() {
        // Given: 유효한 고객 정보
        Customer customer = new Customer("서비스테스트", "service@test.com");
        
        // When: 고객 등록 실행
        Customer registeredCustomer = customerService.registerCustomer(customer);
        
        // Then: 등록 결과 검증
        assertNotNull(registeredCustomer, "등록된 고객 정보가 반환되어야 함");
        assertNotNull(registeredCustomer.getCustomerId(), "고객 ID가 생성되어야 함");
        assertTrue(registeredCustomer.getCustomerId() > 0, "생성된 고객 ID는 양수여야 함");
        assertEquals("서비스테스트", registeredCustomer.getName(), "고객명이 일치해야 함");
        assertEquals("service@test.com", registeredCustomer.getEmail(), "이메일이 일치해야 함");
        
        System.out.println("등록된 고객: " + registeredCustomer);
    }
    
    /**
     * 고객명 누락 시 예외 발생 테스트
     * - 필수 입력값 검증 로직 확인
     */
    @Test
    void testRegisterCustomer_NameNull() {
        // Given: 이름이 null인 고객
        Customer customer = new Customer(null, "test@email.com");
        
        // When & Then: 예외 발생 확인
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> customerService.registerCustomer(customer),
            "이름이 null일 때 예외가 발생해야 함"
        );
        
        assertEquals("고객명을 입력해주세요.", exception.getMessage());
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
    
    /**
     * 이메일 누락 시 예외 발생 테스트
     */
    @Test
    void testRegisterCustomer_EmailNull() {
        // Given: 이메일이 null인 고객
        Customer customer = new Customer("테스트고객", null);
        
        // When & Then: 예외 발생 확인
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> customerService.registerCustomer(customer)
        );
        
        assertEquals("이메일을 입력해주세요.", exception.getMessage());
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
    
    /**
     * 잘못된 이메일 형식 테스트
     */
    @Test
    void testRegisterCustomer_InvalidEmail() {
        // Given: @ 없는 잘못된 이메일
        Customer customer = new Customer("테스트고객", "invalidemail");
        
        // When & Then: 예외 발생 확인
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> customerService.registerCustomer(customer)
        );
        
        assertEquals("올바른 이메일 형식을 입력해주세요.", exception.getMessage());
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
    
    /**
     * 고객 조회 기능 테스트
     * - 등록 후 조회하여 데이터 일치 확인
     */
    @Test
    void testGetCustomerById_Success() {
        // Given: 고객 등록
        Customer customer = new Customer("조회테스트", "find@service.com");
        Customer registeredCustomer = customerService.registerCustomer(customer);
        Long customerId = registeredCustomer.getCustomerId();
        
        // When: 고객 조회
        Customer foundCustomer = customerService.getCustomerById(customerId);
        
        // Then: 조회 결과 검증
        assertNotNull(foundCustomer, "고객이 조회되어야 함");
        assertEquals(customerId, foundCustomer.getCustomerId(), "고객 ID가 일치해야 함");
        assertEquals("조회테스트", foundCustomer.getName(), "고객명이 일치해야 함");
        assertEquals("find@service.com", foundCustomer.getEmail(), "이메일이 일치해야 함");
        
        System.out.println("조회된 고객: " + foundCustomer);
    }
    
    /**
     * 잘못된 고객 ID로 조회 시 예외 테스트
     */
    @Test
    void testGetCustomerById_InvalidId() {
        // When & Then: 잘못된 ID로 조회 시 예외 발생 확인
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> customerService.getCustomerById(-1L)
        );
        
        assertEquals("올바른 고객 ID를 입력해주세요.", exception.getMessage());
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
}