package org.kosa.myproject.test.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.kosa.myproject.domain.Account;
import org.kosa.myproject.domain.Customer;
import org.kosa.myproject.service.AccountService;
import org.kosa.myproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AccountService 비즈니스 로직 테스트
 * - 계좌 관련 비즈니스 로직과 예외 처리 검증
 * - 고객-계좌 관계 로직 테스트
 */
@SpringBootTest
// @Transactional : 서비스 계층의 트랜잭션  테스트를 위해 주석 처리  
class AccountServiceTransactionTest {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CustomerService customerService;
    
    
    /**
     * 테스트용 고객 생성 , 실제 고객과 계좌 db 저장
     */
    @Test
    void registerCustomerAndAccount() {
        Customer testCustomer = new Customer("송강호", "joy@google.com");
        Customer registeredCustomer = customerService.registerCustomer(testCustomer);
        assertNotNull(registeredCustomer);
        Long testCustomerId = registeredCustomer.getCustomerId();        
    
        Account fromAccount = new Account(testCustomerId, "입출금계좌", new BigDecimal("100000"));
        Account registeredFromAccount = accountService.registerAccount(fromAccount);       
        assertNotNull(registeredFromAccount);
        Long accountNumber = registeredFromAccount.getAccountNumber();        
      
        System.out.println("테스트 고객 아이디: "+testCustomerId+" 테스트 계좌번호:"+accountNumber);
        // 테스트 고객 아이디: 31 테스트 계좌번호:37
    }   
    /**
     * 존재하지 않는 입금 계좌로 이체 시도 테스트
     * 입금 계좌가 존재하지 않습니다 예외 발생 
     * 
     * 만약 AccountService에 트랜잭션 처리가 되지 않은 상태이고 
     * 계좌 검증 작업에 문제가 생겨서 예외 처리를 제대로 못했다면 
     * ===> 서비스를 수정(고의로 문제발생 로직 추가와 @Transactional 주석 처리 )하고 테스트 해본다 
     * 입금처리에 실패했어도 출금계좌에서 돈이 빠져나갔음 -> 무결성 보장 안되는 사례 
     * 
     * 다시 트랜잭션 처리해서 확인 
     */
    @Test
    void testTransferMoneyToAccountNotFound() {      
    	// Given:  출금 계좌 번호 
        Long fromAccountNumber = 37L; // 현재 8만원
        Long toAccountNumber = 1L; // 현재 천원 
        
        // When & Then: 존재하지 않는 입금 계좌 999L로 이체 시도
        // Service 의 계좌 이체 메세드 내부에서 고의로 에러를 발생시켜 롤백되는 지 확인한다 
       accountService.transferMoney(fromAccountNumber, toAccountNumber, new BigDecimal("10000"));
       assertEquals(90000, accountService.getAccountByNumber(fromAccountNumber).getBalance());
    }

    /**
     * 정상적인 계좌 이체 테스트
     * - 충분한 잔액으로 이체 실행 후 결과 확인
     */
    @Test
    void testTransferMoneySuccess() {
        // Given: 출금용 계좌와 입금용 계좌 번호   
          
        Long fromAccountNumber = 37L; // 현재 8만원
        Long toAccountNumber = 1L; // 현재 천원 
        
        BigDecimal transferAmount = new BigDecimal("10000"); // 1만원 이체
        
        // When: 이체 실행
        accountService.transferMoney(fromAccountNumber, toAccountNumber, transferAmount);
        
        // Then: 이체 후 잔액 확인
        BigDecimal fromBalance = accountService.getAccountByNumber(fromAccountNumber).getBalance();
        BigDecimal toBalance = accountService.getAccountByNumber(toAccountNumber).getBalance();
        
        assertEquals(new BigDecimal("90000.00"), fromBalance, "출금 계좌 잔액이 정확해야 함");
        assertEquals(new BigDecimal("11000.00"), toBalance, "입금 계좌 잔액이 정확해야 함");
        
        System.out.println("=== 이체 성공 테스트 ===");
        System.out.println("출금계좌 최종잔액: " + fromBalance);
        System.out.println("입금계좌 최종잔액: " + toBalance);
    }
}