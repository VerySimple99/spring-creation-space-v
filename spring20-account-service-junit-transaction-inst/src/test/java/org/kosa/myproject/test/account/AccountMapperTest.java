package org.kosa.myproject.test.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kosa.myproject.domain.Account;
import org.kosa.myproject.domain.Customer;
import org.kosa.myproject.mapper.AccountMapper;
import org.kosa.myproject.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional  // 테스트 후 자동 롤백
class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;    
    @Autowired
    private CustomerMapper customerMapper;
    
    private Long testCustomerId;  // 테스트용 고객 ID
    
    /**
     * 각 테스트 실행 전 공통 준비 작업
     * 테스트용 고객 생성
     * @BeforeEach 설정 의미 :  각 테스트 메소드 실행 전에 호출되는 메소드
     * 										   계좌(자식테이블) 기능 테스트를 위해서는 계좌주 정보(부모테이블)가 반드시 존재해야 함
     * 										    @BeforeEach 설정을 이용하면 각 계좌 기능 테스트 실행 전에 @BeforeEach 가 명시된 
     * 											메서드를 먼저 실행하여 테스트를 위한 계좌주 정보를 우선적으로 저장한 후 
     *                                         그 계좌주 정보의 생성된 customerId 를 인스턴스 변수에 할당하고 이를 기반으로 
     *                                         계좌의 각 기능을 테스트 할 수 있도록 지원한다  	
     */
    ////////여기에 설정/////////
    @BeforeEach
    void setUp() {
        // 테스트용 고객 생성
        Customer testCustomer = new Customer("김테스터", "tester@example.com");
        customerMapper.register(testCustomer);
        testCustomerId = testCustomer.getCustomerId();
        
        System.out.println("테스트용 고객 생성 완료 - ID: " + testCustomerId);
    }    
    
    /**
     * 고객별 계좌 목록 조회 테스트
     * - 여러 계좌 등록 후 고객 ID로 목록 조회
     * - Has-a 관계 (고객 정보 포함) 확인
     */
    @Test
    void testFindAccountListByCustomerId() {
        // Given: 테스트용 계좌 2개 등록
        Account account1 = new Account(testCustomerId, "입출금계좌", new BigDecimal("100000"));
        Account account2 = new Account(testCustomerId, "예적금계좌", new BigDecimal("500000"));
        
        accountMapper.register(account1);
        accountMapper.register(account2);
        
        // When: 고객 ID로 계좌 목록 조회
        List<Account> accountList = accountMapper.findAccountListByCustomerId(testCustomerId);
        
        // Then: 조회 결과 검증
        assertNotNull(accountList, "계좌 목록이 존재해야 함");
        assertEquals(2, accountList.size(), "등록한 계좌 2개가 조회되어야 함");
                
        System.out.println("=== 조회된 계좌 목록 ===");
        for (Account account : accountList) {
            System.out.println("계좌: " + account.getAccountNumber() + 
                             ", 유형: " + account.getAccountType() + 
                             ", 잔액: " + account.getBalance() + 
                             ", 고객: " + account.getCustomer().getName());
        }
    }
 

    /**
     * 계좌 잔액 조회 기능 테스트
     * - 계좌번호로 현재 잔액만 조회
     */
    @Test
    void testGetBalanceByAccountNumber() {
        // Given: 테스트용 계좌 등록
        BigDecimal originalBalance = new BigDecimal("123456.78");
        Account account = new Account(testCustomerId, "예적금계좌", originalBalance);
        accountMapper.register(account);
        Long accountNumber = account.getAccountNumber();
        
        // When: 잔액 조회
        BigDecimal balance = accountMapper.getBalanceByAccountNumber(accountNumber);
        
        // Then: 조회 결과 확인
        assertNotNull(balance, "잔액이 조회되어야 함");
        assertEquals(originalBalance, balance, "원래 잔액과 일치해야 함");        
        System.out.println("조회된 잔액: " + accountNumber + " -> " + balance);
    }
    // 이후 각 계좌 기능을 테스트 해본다 
       
}