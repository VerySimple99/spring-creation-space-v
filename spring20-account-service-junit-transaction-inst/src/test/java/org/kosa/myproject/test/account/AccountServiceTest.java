package org.kosa.myproject.test.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kosa.myproject.domain.Account;
import org.kosa.myproject.domain.Customer;
import org.kosa.myproject.service.AccountService;
import org.kosa.myproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountService 비즈니스 로직 테스트
 * - 계좌 관련 비즈니스 로직과 예외 처리 검증
 * - 고객-계좌 관계 로직 테스트
 */
@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CustomerService customerService;
    
    private Long testCustomerId;  // 테스트용 고객 ID
    
    /**
     * 각 테스트 실행 전 테스트용 고객 생성
     */
    @BeforeEach
    void setUp() {
        Customer testCustomer = new Customer("서비스테스터", "servicetester@example.com");
        Customer registeredCustomer = customerService.registerCustomer(testCustomer);
        testCustomerId = registeredCustomer.getCustomerId();
        
        System.out.println("테스트용 고객 생성 완료 - ID: " + testCustomerId);
    }
    
    /**
     * 정상적인 계좌 등록 테스트
     */
    @Test
    void testRegisterAccount_Success() {
        // Given: 유효한 계좌 정보
        Account account = new Account(testCustomerId, "입출금계좌", new BigDecimal("100000"));
        
        // When: 계좌 등록 실행
        Account registeredAccount = accountService.registerAccount(account);
        
        // Then: 등록 결과 검증
        assertNotNull(registeredAccount, "등록된 계좌 정보가 반환되어야 함");
        assertNotNull(registeredAccount.getAccountNumber(), "계좌번호가 생성되어야 함");
        assertTrue(registeredAccount.getAccountNumber() > 0, "생성된 계좌번호는 양수여야 함");
        assertEquals(testCustomerId, registeredAccount.getCustomerId(), "고객 ID가 일치해야 함");
        assertEquals("입출금계좌", registeredAccount.getAccountType(), "계좌 유형이 일치해야 함");
        assertEquals(new BigDecimal("100000"), registeredAccount.getBalance(), "잔액이 일치해야 함");
        
        System.out.println("등록된 계좌: " + registeredAccount);
    }
    
    /**
     * 존재하지 않는 고객으로 계좌 등록 시 예외 테스트
     */
    @Test
    void testRegisterAccount_CustomerNotFound() {
        // Given: 존재하지 않는 고객 ID로 계좌 생성
        Account account = new Account(99999L, "입출금계좌", new BigDecimal("50000"));
        
        // When & Then: 예외 발생 확인
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> accountService.registerAccount(account)
        );
        
        assertTrue(exception.getMessage().contains("존재하지 않는 고객입니다"));
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
    
    /**
     * 잘못된 계좌 유형으로 등록 시 예외 테스트
     */
    @Test
    void testRegisterAccount_InvalidAccountType() {
        // Given: 잘못된 계좌 유형
        Account account = new Account(testCustomerId, "잘못된계좌", new BigDecimal("50000"));
        
        // When & Then: 예외 발생 확인
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> accountService.registerAccount(account)
        );
        
        assertEquals("올바른 계좌 유형을 선택해주세요. (입출금계좌 또는 예적금계좌)", exception.getMessage());
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
    
    /**
     * 음수 잔액으로 계좌 등록 시 예외 테스트
     */
    @Test
    void testRegisterAccount_NegativeBalance() {
        // Given: 음수 잔액
        Account account = new Account(testCustomerId, "입출금계좌", new BigDecimal("-1000"));
        
        // When & Then: 예외 발생 확인
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> accountService.registerAccount(account)
        );
        
        assertEquals("잔액은 0 이상이어야 합니다.", exception.getMessage());
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
    
    /**
     * 고객별 계좌 목록 조회 테스트
     * - 여러 계좌 등록 후 목록 조회 및 Has-a 관계 확인
     */
    @Test
    void testGetAccountListByCustomerId_Success() {
        // Given: 테스트용 계좌 2개 등록
        Account account1 = new Account(testCustomerId, "입출금계좌", new BigDecimal("200000"));
        Account account2 = new Account(testCustomerId, "예적금계좌", new BigDecimal("800000"));
        
        accountService.registerAccount(account1);
        accountService.registerAccount(account2);
        
        // When: 고객 ID로 계좌 목록 조회
        List<Account> accountList = accountService.getAccountListByCustomerId(testCustomerId);
        
        // Then: 조회 결과 검증
        assertNotNull(accountList, "계좌 목록이 조회되어야 함");
        assertEquals(2, accountList.size(), "등록한 계좌 2개가 조회되어야 함");
        
        // Has-a 관계 확인
        Account firstAccount = accountList.get(0);
        assertNotNull(firstAccount.getCustomer(), "계좌에 고객 정보가 포함되어야 함");
        assertEquals("서비스테스터", firstAccount.getCustomer().getName(), "고객명이 일치해야 함");
        assertEquals("servicetester@example.com", firstAccount.getCustomer().getEmail(), "이메일이 일치해야 함");
        
        System.out.println("=== 조회된 계좌 목록 ===");
        for (Account account : accountList) {
            System.out.println("계좌: " + account.getAccountNumber() + 
                             ", 유형: " + account.getAccountType() + 
                             ", 잔액: " + account.getBalance() + 
                             ", 고객: " + account.getCustomer().getName());
        }
    }
    
    /**
     * 존재하지 않는 고객으로 계좌 목록 조회 시 예외 테스트
     */
    @Test
    void testGetAccountListByCustomerId_CustomerNotFound() {
        // When & Then: 존재하지 않는 고객 ID로 조회 시 예외 발생 확인
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> accountService.getAccountListByCustomerId(99999L)
        );
        
        assertTrue(exception.getMessage().contains("존재하지 않는 고객입니다"));
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
    
    /**
     * 특정 계좌 조회 테스트
     */
    @Test
    void testGetAccountByNumber_Success() {
        // Given: 계좌 등록
        Account account = new Account(testCustomerId, "예적금계좌", new BigDecimal("1500000"));
        Account registeredAccount = accountService.registerAccount(account);
        Long accountNumber = registeredAccount.getAccountNumber();
        
        // When: 계좌번호로 조회
        Account foundAccount = accountService.getAccountByNumber(accountNumber);
        
        // Then: 조회 결과 검증
        assertNotNull(foundAccount, "계좌가 조회되어야 함");
        assertEquals(accountNumber, foundAccount.getAccountNumber(), "계좌번호가 일치해야 함");
        assertEquals("예적금계좌", foundAccount.getAccountType(), "계좌 유형이 일치해야 함");
        assertEquals(new BigDecimal("1500000.00"), foundAccount.getBalance(), "잔액이 일치해야 함");
        
        // Has-a 관계 확인
        assertNotNull(foundAccount.getCustomer(), "고객 정보가 포함되어야 함");
        assertEquals("서비스테스터", foundAccount.getCustomer().getName(), "고객명이 일치해야 함");
        
        System.out.println("조회된 계좌: " + foundAccount);
    }
    /**
     * 정상적인 계좌 이체 테스트
     * - 충분한 잔액으로 이체 실행 후 결과 확인
     */
    @Test
    void testTransferMoney_Success() {
        // Given: 출금용 계좌와 입금용 계좌 생성
        Account fromAccount = new Account(testCustomerId, "입출금계좌", new BigDecimal("100000"));
        Account toAccount = new Account(testCustomerId, "예적금계좌", new BigDecimal("50000"));
        
        Account registeredFromAccount = accountService.registerAccount(fromAccount);
        Account registeredToAccount = accountService.registerAccount(toAccount);
        
        Long fromAccountNumber = registeredFromAccount.getAccountNumber();
        Long toAccountNumber = registeredToAccount.getAccountNumber();
        BigDecimal transferAmount = new BigDecimal("30000");
        
        // When: 이체 실행
        accountService.transferMoney(fromAccountNumber, toAccountNumber, transferAmount);
        
        // Then: 이체 후 잔액 확인
        BigDecimal fromBalance = accountService.getAccountByNumber(fromAccountNumber).getBalance();
        BigDecimal toBalance = accountService.getAccountByNumber(toAccountNumber).getBalance();
        
        assertEquals(new BigDecimal("70000.00"), fromBalance, "출금 계좌 잔액이 정확해야 함");
        assertEquals(new BigDecimal("80000.00"), toBalance, "입금 계좌 잔액이 정확해야 함");
        
        System.out.println("=== 이체 성공 테스트 ===");
        System.out.println("출금계좌 최종잔액: " + fromBalance);
        System.out.println("입금계좌 최종잔액: " + toBalance);
    }

    /**
     * 잔액 부족으로 이체 실패 테스트
     * - 잔액보다 큰 금액 이체 시도하여 예외 발생 확인
     */
    @Test
    void testTransferMoney_InsufficientBalance() {
        // Given: 잔액이 부족한 출금 계좌
        Account fromAccount = new Account(testCustomerId, "입출금계좌", new BigDecimal("10000"));
        Account toAccount = new Account(testCustomerId, "예적금계좌", new BigDecimal("50000"));
        
        Account registeredFromAccount = accountService.registerAccount(fromAccount);
        Account registeredToAccount = accountService.registerAccount(toAccount);
        
        Long fromAccountNumber = registeredFromAccount.getAccountNumber();
        Long toAccountNumber = registeredToAccount.getAccountNumber();
        BigDecimal transferAmount = new BigDecimal("20000");  // 잔액보다 큰 금액
        
        // When & Then: 잔액 부족으로 예외 발생 확인
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> accountService.transferMoney(fromAccountNumber, toAccountNumber, transferAmount)
        );
        
        assertTrue(exception.getMessage().contains("잔액이 부족합니다"), "잔액 부족 메시지가 포함되어야 함");
        
        // 이체 실패 후 잔액이 변경되지 않았는지 확인 (트랜잭션 롤백 확인)
        BigDecimal fromBalance = accountService.getAccountByNumber(fromAccountNumber).getBalance();
        BigDecimal toBalance = accountService.getAccountByNumber(toAccountNumber).getBalance();
        
        assertEquals(new BigDecimal("10000.00"), fromBalance, "출금 계좌 잔액이 변경되지 않아야 함");
        assertEquals(new BigDecimal("50000.00"), toBalance, "입금 계좌 잔액이 변경되지 않아야 함");
        
        System.out.println("예상된 예외 발생: " + exception.getMessage());
        System.out.println("트랜잭션 롤백 확인 - 출금계좌: " + fromBalance + ", 입금계좌: " + toBalance);
    }

    /**
     * 존재하지 않는 출금 계좌로 이체 시도 테스트
     */
    @Test
    void testTransferMoney_FromAccountNotFound() {
        // Given: 입금 계좌만 생성
        Account toAccount = new Account(testCustomerId, "예적금계좌", new BigDecimal("50000"));
        Account registeredToAccount = accountService.registerAccount(toAccount);
        Long toAccountNumber = registeredToAccount.getAccountNumber();
        
        // When & Then: 존재하지 않는 출금 계좌로 이체 시도
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> accountService.transferMoney(99999L, toAccountNumber, new BigDecimal("10000"))
        );
        
        assertTrue(exception.getMessage().contains("출금 계좌가 존재하지 않습니다"), "출금 계좌 없음 메시지가 포함되어야 함");
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }

    /**
     * 존재하지 않는 입금 계좌로 이체 시도 테스트
     */
    @Test
    void testTransferMoney_ToAccountNotFound() {
        // Given: 출금 계좌만 생성
        Account fromAccount = new Account(testCustomerId, "입출금계좌", new BigDecimal("100000"));
        Account registeredFromAccount = accountService.registerAccount(fromAccount);
        Long fromAccountNumber = registeredFromAccount.getAccountNumber();
        
        // When & Then: 존재하지 않는 입금 계좌로 이체 시도
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> accountService.transferMoney(fromAccountNumber, 99999L, new BigDecimal("10000"))
        );
        
        assertTrue(exception.getMessage().contains("입금 계좌가 존재하지 않습니다"), "입금 계좌 없음 메시지가 포함되어야 함");
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }

    /**
     * 동일한 계좌로 이체 시도 테스트
     */
    @Test
    void testTransferMoney_SameAccount() {
        // Given: 계좌 하나 생성
        Account account = new Account(testCustomerId, "입출금계좌", new BigDecimal("100000"));
        Account registeredAccount = accountService.registerAccount(account);
        Long accountNumber = registeredAccount.getAccountNumber();
        
        // When & Then: 동일한 계좌로 이체 시도
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> accountService.transferMoney(accountNumber, accountNumber, new BigDecimal("10000"))
        );
        
        assertEquals("출금 계좌와 입금 계좌가 같을 수 없습니다.", exception.getMessage());
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }

    /**
     * 음수 금액으로 이체 시도 테스트
     */
    @Test
    void testTransferMoney_NegativeAmount() {
        // Given: 출금, 입금 계좌 생성
        Account fromAccount = new Account(testCustomerId, "입출금계좌", new BigDecimal("100000"));
        Account toAccount = new Account(testCustomerId, "예적금계좌", new BigDecimal("50000"));
        
        Account registeredFromAccount = accountService.registerAccount(fromAccount);
        Account registeredToAccount = accountService.registerAccount(toAccount);
        
        // When & Then: 음수 금액으로 이체 시도
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> accountService.transferMoney(registeredFromAccount.getAccountNumber(), 
                                             registeredToAccount.getAccountNumber(), 
                                             new BigDecimal("-1000"))
        );
        
        assertEquals("이체 금액은 0보다 커야 합니다.", exception.getMessage());
        System.out.println("예상된 예외 발생: " + exception.getMessage());
    }
}