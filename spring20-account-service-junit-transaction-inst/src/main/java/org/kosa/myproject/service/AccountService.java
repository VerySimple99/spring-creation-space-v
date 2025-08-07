// AccountService.java - 계좌 비즈니스 로직 서비스
package org.kosa.myproject.service;

import java.math.BigDecimal;
import java.util.List;

import org.kosa.myproject.domain.Account;
import org.kosa.myproject.exception.AccountNotFoundException;
import org.kosa.myproject.mapper.AccountMapper;
import org.kosa.myproject.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계좌 관련 비즈니스 로직 처리 서비스
 * - 계좌 등록 및 조회 기능 제공
 * - 고객 존재 여부 검증
 * - 비즈니스 규칙 적용
 */
@Service
@Transactional
public class AccountService {
    
    private final AccountMapper accountMapper;
    private final CustomerMapper customerMapper;
    
    // 생성자 주입으로 의존성 주입
      public AccountService(AccountMapper accountMapper, CustomerMapper customerMapper) {
        this.accountMapper = accountMapper;
        this.customerMapper = customerMapper;
    }
    
    /**
     * 신규 계좌 등록
     * - 고객 존재 여부 확인
     * - 계좌 정보 검증 후 등록
     * 
     * @param account 등록할 계좌 정보
     * @return 등록된 계좌 정보 (생성된 계좌번호 포함)
     * @throws IllegalArgumentException 입력값이 잘못된 경우
     * @throws RuntimeException 고객이 존재하지 않는 경우
     */
     ////////// 클래스 레벨에서 @Transactional 로 설정되어 트랜잭션 관리가 됨 ////////////
    public Account registerAccount(Account account) {
        // 입력값 검증
        validateAccountInput(account);
        
        // 고객 존재 여부 확인
        if (customerMapper.findById(account.getCustomerId()) == null) {
            throw new RuntimeException("존재하지 않는 고객입니다. 고객 ID: " + account.getCustomerId());
        }        
        // 계좌 등록 수행
        int result = accountMapper.register(account);        
        if (result != 1) {
            throw new RuntimeException("계좌 등록에 실패했습니다.");
        }        
        return account;  // 등록 후 생성된 계좌번호가 포함된 객체 반환
    }    
    /**
     * 계좌 입력값 검증 private 메서드
     * - 필수값 체크 및 비즈니스 규칙 검증
     */
    private void validateAccountInput(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("계좌 정보가 없습니다.");
        }        
        if (account.getCustomerId() == null || account.getCustomerId() <= 0) {
            throw new IllegalArgumentException("올바른 고객 ID를 입력해주세요.");
        }        
        if (account.getAccountType() == null || account.getAccountType().trim().isEmpty()) {
            throw new IllegalArgumentException("계좌 유형을 선택해주세요.");
        }        
        /*
         	BigDecimal 클래스의 compareTo(BigDecimal val) 메서드는 두 BigDecimal 객체의 수학적인 값을 비교하는 데 사용 			스케일(소수점 이하 자릿수)과 상관없이 오직 값 자체만을 비교
         	compareTo 메서드는 정수 값을 반환
         	0: 두 BigDecimal 객체의 값이 같을 때.
			1:  BigDecimal 객체의 값이 인자로 전달된 BigDecimal 객체보다 클 때.
			-1: BigDecimal 객체의 값이 인자로 전달된 BigDecimal 객체보다 작을 때.
         */
        if (account.getBalance() == null || account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("잔액은 0 이상이어야 합니다.");
        }
    }
    /**
     * 계좌 이체 기능
     * - 출금 계좌에서 입금 계좌로 금액 이체
     * - 트랜잭션으로 전체 과정을 하나의 단위로 처리
     * - 실패 시 자동 롤백으로 데이터 일관성 보장
     * 
     * @param fromAccountNumber 출금 계좌번호
     * @param toAccountNumber 입금 계좌번호  
     * @param transferAmount 이체 금액
     * @throws IllegalArgumentException 입력값이 잘못된 경우
     * @throws RuntimeException 계좌가 존재하지 않거나 잔액 부족한 경우
     */ 
    ////////// 클래스 레벨에서 @Transactional 로 설정되어 트랜잭션 관리가 됨 ////////////
    public void transferMoney(Long fromAccountNumber, Long toAccountNumber, BigDecimal transferAmount) {
        
        // 1. 입력값 검증
        validateTransferInput(fromAccountNumber, toAccountNumber, transferAmount);
        
        // 2. 출금 계좌 존재 여부 및 잔액 확인
        BigDecimal fromBalance = accountMapper.getBalanceByAccountNumber(fromAccountNumber);
        if (fromBalance == null) {
            throw new RuntimeException("출금 계좌가 존재하지 않습니다. 계좌번호: " + fromAccountNumber);
        }
        
        // 3. 입금 계좌 존재 여부 확인
        BigDecimal toBalance = accountMapper.getBalanceByAccountNumber(toAccountNumber);
        if (toBalance == null) {
            throw new AccountNotFoundException("입금 계좌가 존재하지 않습니다. 계좌번호: " + toAccountNumber);
        }
        
        // 4. 잔액 부족 확인
        if (fromBalance.compareTo(transferAmount) < 0) {
            throw new RuntimeException("잔액이 부족합니다. 현재잔액: " + fromBalance + ", 이체금액: " + transferAmount);
        }
        
        // 5. 이체 실행 (중요: 순서가 중요함 - 출금 먼저, 입금 나중에)
        // 출금 계좌에서 차감
        BigDecimal newFromBalance = fromBalance.subtract(transferAmount);
        int fromResult = accountMapper.updateBalance(fromAccountNumber, newFromBalance);
        System.out.println("========계좌 이체를 위한 출금 실행=========");
        if (fromResult != 1) {
            throw new RuntimeException("출금 처리에 실패했습니다.");
        }
        
        // 입금 계좌에 추가
        BigDecimal newToBalance = toBalance.add(transferAmount);
        /********************고의로 문제 발생시켜 롤백되는지 확인한다*********************/
        ////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////
        toAccountNumber = 999L;
        int toResult = accountMapper.updateBalance(toAccountNumber, newToBalance);
        System.out.println("========계좌 이체를 위한 입금 실행=========");
        if (toResult != 1) {
            throw new RuntimeException("입금 처리에 실패했습니다.");
        }
        
        // 이체 완료 로그 
        System.out.println("=== 이체 완료 ===");
        System.out.println("출금계좌(" + fromAccountNumber + "): " + fromBalance + " -> " + newFromBalance);
        System.out.println("입금계좌(" + toAccountNumber + "): " + toBalance + " -> " + newToBalance);
        System.out.println("이체금액: " + transferAmount);
    }

    /**
     * 이체 입력값 검증 private 메서드
     * - 계좌번호와 이체금액의 유효성 검사
     */
    private void validateTransferInput(Long fromAccountNumber, Long toAccountNumber, BigDecimal transferAmount) {
        if (fromAccountNumber == null || fromAccountNumber <= 0) {
            throw new IllegalArgumentException("유효한 출금 계좌번호를 입력해주세요.");
        }
        
        if (toAccountNumber == null || toAccountNumber <= 0) {
            throw new IllegalArgumentException("유효한 입금 계좌번호를 입력해주세요.");
        }

        if (transferAmount == null || transferAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("이체 금액은 0보다 커야 합니다.");
        }       
    }
    /**
     * 고객별 계좌 목록 조회
     * 고객의 모든 계좌를 고객 정보와 함께 조회   

     * @param customerId 
     * @return 해당 고객의 계좌 목록 (고객 정보 포함)
     */
    @Transactional(readOnly = true)
    public List<Account> getAccountListByCustomerId(Long customerId) {
        if (customerId == null || customerId <= 0) {
            throw new IllegalArgumentException("올바른 고객 ID를 입력해주세요.");
        }        
        // 고객 존재 여부 확인
        if (customerMapper.findById(customerId) == null) {
            throw new RuntimeException("존재하지 않는 고객입니다. 고객 ID: " + customerId);
        }        
        return accountMapper.findAccountListByCustomerId(customerId);
    }
    
    /**
     * 계좌번호로 특정 계좌 조회
     * 
     * @param accountNumber 조회할 계좌번호
     * @return 계좌 정보 (고객 정보 포함, 없으면 null)
     */
    @Transactional(readOnly = true)
    public Account getAccountByNumber(Long accountNumber) {
        if (accountNumber == null || accountNumber <= 0) {
            throw new IllegalArgumentException("올바른 계좌번호를 입력해주세요.");
        }
        /////////// 이렇게 수정해봅니다 ////////// 
        Account account = accountMapper.findByAccountNumber(accountNumber);
        if(account == null)
        	throw new RuntimeException("존재하지 않는 계좌입니다. 계좌 ID: " + accountNumber);
        return accountMapper.findByAccountNumber(accountNumber);
    }    
}