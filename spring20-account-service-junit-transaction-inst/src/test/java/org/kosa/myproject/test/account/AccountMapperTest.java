package org.kosa.myproject.test.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

/**
 * AccountMapper 기능 테스트
 * - 실제 DB 연결하여 테스트 수행
 * - 고객 등록 후 계좌 관련 기능 테스트
 */
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
     * - 테스트용 고객 생성
    @BeforeEach
    각 테스트 메소드가 실행되기 전에 해당 어노테이션이 붙은 메소드를 반드시 실행하도록 지정하는 역할
    간단 정리 -> 각 테스트 메소드 실행 전에 호출되는 메소드
     */
    @BeforeEach
    void setUp() {
        // 테스트용 고객 생성
        Customer testCustomer = new Customer("김테스터", "tester@example.com");
        customerMapper.register(testCustomer);
        testCustomerId = testCustomer.getCustomerId();
        
        System.out.println("테스트용 고객 생성 완료 - ID: " + testCustomerId);
    }
    
    /**
     * 계좌 등록 기능 테스트
     * - 새 계좌 등록 후 생성된 계좌번호 확인
     */
    @Test
    void testRegister() {
        // Given: 테스트용 계좌 데이터 준비
        Account account = new Account(testCustomerId, "입출금계좌", new BigDecimal("50000"));
        System.out.println("등록 전: " + account);
        
        // When: 계좌 등록 실행
        int result = accountMapper.register(account);
        
        // Then: 결과 검증
        assertEquals(1, result, "등록된 행의 수는 1이어야 함");
        assertNotNull(account.getAccountNumber(), "등록 후 계좌번호가 생성되어야 함");
        assertTrue(account.getAccountNumber() > 0, "생성된 계좌번호는 양수여야 함");
        
        System.out.println("등록 후: " + account);
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
        
        // 첫 번째 계좌 검증 (Has-a 관계 확인)
        Account firstAccount = accountList.get(0);
        assertNotNull(firstAccount.getCustomer(), "계좌에 고객 정보가 포함되어야 함");
        assertEquals("김테스터", firstAccount.getCustomer().getName(), "고객명이 일치해야 함");
        assertEquals("tester@example.com", firstAccount.getCustomer().getEmail(), "이메일이 일치해야 함");
        
        System.out.println("=== 조회된 계좌 목록 ===");
        for (Account account : accountList) {
            System.out.println("계좌: " + account.getAccountNumber() + 
                             ", 유형: " + account.getAccountType() + 
                             ", 잔액: " + account.getBalance() + 
                             ", 고객: " + account.getCustomer().getName());
        }
    }
    
    /**
     * 특정 계좌 조회 테스트
     * - 계좌번호로 단일 계좌 조회
     */
    @Test
    void testFindByAccountNumber() {
        // Given: 테스트용 계좌 등록
        Account originalAccount = new Account(testCustomerId, "예적금계좌", new BigDecimal("1000000"));
        accountMapper.register(originalAccount);
        Long accountNumber = originalAccount.getAccountNumber();
        
        // When: 계좌번호로 조회
        Account foundAccount = accountMapper.findByAccountNumber(accountNumber);
        
        // Then: 조회 결과 검증
        assertNotNull(foundAccount, "조회된 계좌가 존재해야 함");
        assertEquals(accountNumber, foundAccount.getAccountNumber(), "계좌번호가 일치해야 함");
        assertEquals("예적금계좌", foundAccount.getAccountType(), "계좌 유형이 일치해야 함");
        assertEquals(new BigDecimal("1000000.00"), foundAccount.getBalance(), "잔액이 일치해야 함");
        
        // Has-a 관계 확인
        assertNotNull(foundAccount.getCustomer(), "고객 정보가 포함되어야 함");
        assertEquals("김테스터", foundAccount.getCustomer().getName(), "고객명이 일치해야 함");
        
        System.out.println("조회된 계좌: " + foundAccount);
    }
 // AccountMapperTest.java에 추가할 테스트 메서드들

    /**
     * 계좌 잔액 업데이트 기능 테스트
     * - 특정 계좌의 잔액 변경 후 확인
     */
    @Test
    void testUpdateBalance() {
        // Given: 테스트용 계좌 등록
        Account account = new Account(testCustomerId, "입출금계좌", new BigDecimal("50000"));
        accountMapper.register(account);
        Long accountNumber = account.getAccountNumber();
        
        // When: 잔액 업데이트
        BigDecimal newBalance = new BigDecimal("75000.00");
        int result = accountMapper.updateBalance(accountNumber, newBalance);
        
        // Then: 업데이트 결과 확인
        assertEquals(1, result, "업데이트된 행의 수는 1이어야 함");
        
        // 실제 업데이트 확인
        BigDecimal updatedBalance = accountMapper.getBalanceByAccountNumber(accountNumber);
        assertEquals(newBalance, updatedBalance, "잔액이 정확히 업데이트되어야 함");
        
        System.out.println("잔액 업데이트 완료: " + accountNumber + " -> " + updatedBalance);
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

    /**
     * 존재하지 않는 계좌의 잔액 조회 테스트
     * - 없는 계좌번호로 조회 시 null 반환 확인
     */
    @Test
    void testGetBalanceByAccountNumber_NotFound() {
        // When: 존재하지 않는 계좌번호로 잔액 조회
        BigDecimal balance = accountMapper.getBalanceByAccountNumber(99999L);
        
        // Then: null 반환 확인
        assertNull(balance, "존재하지 않는 계좌의 잔액은 null이어야 함");
        
        System.out.println("존재하지 않는 계좌 조회 결과: null");
    }
}