package org.kosa.myproject.test.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.kosa.myproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AccountService 비즈니스 로직 테스트
 * - 계좌 관련 비즈니스 로직과 예외 처리 검증
 * - 고객-계좌 관계 로직 테스트
 */
@SpringBootTest
// @Transactional : 실제 DB  연동되는 서비스 계층 AccountService의 계좌 이체시 트랜잭션 처리 테스트를 위해 주석 처리  
class AccountServiceTransactionTest {
    @Autowired
    private AccountService accountService;
    
    /**
     *  계좌 이체 테스트
     *  0. 실제 DB 의 account table 의 정보를 조회해서 account_number 1 과 3 의 정보를 확인한다  
           	계좌 번호 1 번 손흥민 입출금계좌 1000원
           	계좌 번호 3 번 이강인 입출금계좌 750원
     *  
     *  1. AccountService 의 transferMoney 메서드 133 라인에서 문제를 고의로 발생시켜 트랜잭션 처리가 되어 롤백되는지 확인한다
     *     출금 실행 후 입금시 예외 발생 ==> 출금 작업 ROLLBACK 되어 DB에서 확인하면 여전히 기존 금액이 유지됨 
     *      
     *  2. AccountService 의 transferMoney 메서드의 고의로 문제를 발생 시킨 133 라인 주석 해제 후 정상 수행되는 지 확인한다 
     *     정상 실행되고 COMMIT 되는 것을 확인한다 
     *     === 이체 성공 테스트 ===
			출금계좌 최종잔액: 900.00
			입금계좌 최종잔액: 850.00
     *  
     *  3. AccountService class 의 @Transactional 설정을 제외시키고 고의로 문제를 발생 시킨 후 트랜잭션 처리의 중요성을 확인한다
     *     133 라인 다시 추가해 입금 과정에서 문제 발생시킴
     *     
     *     이체 과정에서 출금은 처리되고 입금되지 않는 심각한 문제가 발생됨! 
     *     출금계좌 최종잔액: 900.00   ===> 100원 출금됨 
			입금계좌 최종잔액: 850.00   ===> 이체되지 않아 그대로임   
     */
    @Test
    void testTransferMoney() {
        // Given: 출금용 계좌와 입금용 계좌 번호             
        Long fromAccountNumber = 1L; //계좌 번호 1 번 손흥민 입출금계좌 1000원
        Long toAccountNumber = 3L; //계좌 번호 3 번 이강인 입출금계좌 750원
        
        BigDecimal transferAmount = new BigDecimal("100"); // 100원 이체
        
        // When: 이체 실행
        accountService.transferMoney(fromAccountNumber, toAccountNumber, transferAmount);
        
        // Then: 이체 후 잔액 확인
        BigDecimal fromBalance = accountService.getAccountByNumber(fromAccountNumber).getBalance();
        BigDecimal toBalance = accountService.getAccountByNumber(toAccountNumber).getBalance();
        
       assertEquals(new BigDecimal("900.00"), fromBalance, "이체한(출금) 계좌 잔액이 정확해야 함");
       assertEquals(new BigDecimal("850.00"), toBalance, "이체받는(입금) 계좌 잔액이 정확해야 함");
        
        System.out.println("=== 이체 성공 테스트 ===");
        System.out.println("출금계좌 최종잔액: " + fromBalance);
        System.out.println("입금계좌 최종잔액: " + toBalance);
    }
}