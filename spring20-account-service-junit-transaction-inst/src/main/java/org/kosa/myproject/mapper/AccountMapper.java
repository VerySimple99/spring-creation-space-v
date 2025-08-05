// AccountMapper.java - 계좌 데이터 접근 인터페이스  
package org.kosa.myproject.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.myproject.domain.Account;


@Mapper
public interface AccountMapper {
    
    /**
     * 신규 계좌 등록
     * @param account 등록할 계좌 정보 (customerId, accountType, balance 필수)
     * @return 등록된 행의 수 (1: 성공, 0: 실패)
     */
    int register(Account account);
    
    /**
     * 고객 ID로 해당 고객의 모든 계좌 목록 조회
     * @param customerId 조회할 고객 ID
     * @return 해당 고객의 계좌 목록 (고객 정보 포함)
     */
    List<Account> findAccountListByCustomerId(Long customerId);
    
    /**
     * 계좌번호로 특정 계좌 조회 (향후 입출금, 계좌이체 기능에서 사용 예정)
     * @param accountNumber 조회할 계좌번호
     * @return 계좌 정보 (고객 정보 포함, 없으면 null)
     */
    Account findByAccountNumber(Long accountNumber);
    
    /**
     * 계좌 잔액 업데이트 (계좌 이체용)
     * - 특정 계좌의 잔액을 새로운 값으로 변경
     * - 트랜잭션 내에서 출금계좌 차감, 입금계좌 증액에 사용
     * 
     * @param accountNumber 업데이트할 계좌번호
     * @param newBalance 새로운 잔액
     * @return 업데이트된 행의 수 (1: 성공, 0: 실패)
     */
    int updateBalance(@Param("accountNumber") Long accountNumber, 
                      @Param("newBalance") BigDecimal newBalance);

    /**
     * 계좌 잔액만 조회 (이체 전 잔액 확인용)
     * - 계좌 이체 시 현재 잔액 확인용 경량 조회
     * 
     * @param accountNumber 조회할 계좌번호
     * @return 현재 잔액 (계좌가 없으면 null)
     */
    BigDecimal getBalanceByAccountNumber(Long accountNumber);
}