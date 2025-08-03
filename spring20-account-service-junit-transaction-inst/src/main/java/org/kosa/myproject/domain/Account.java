// Account.java - 계좌 도메인 클래스  
package org.kosa.myproject.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 계좌 정보를 담는 도메인 클래스
 * - Has-a Customer: 계좌는 고객 정보를 포함
 * - 계좌의 핵심 정보와 비즈니스 로직 포함
 */
public class Account {
    
    private Long accountNumber;      // 계좌번호 (기본키)
    private Long customerId;         // 고객 ID (외래키)
    private String accountType;      // 계좌 유형
    private BigDecimal balance;      // 잔액 : 금융 도메인 특성 이해 
    private LocalDateTime createdAt; // 계좌 개설일시
    
    // Has-a 관계: Account가 Customer 정보를 포함
    private Customer customer;       // 계좌 소유자 정보 // 객체지향 설계 학습 (Has-a 관계)
    
    // 기본 생성자 - MyBatis에서 필요
    public Account() {}
    
    // 생성자 - 새 계좌 개설 시 사용
    public Account(Long customerId, String accountType, BigDecimal balance) {
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
    }
    
    // 전체 생성자
    public Account(Long accountNumber, Long customerId, String accountType, 
                   BigDecimal balance, LocalDateTime createdAt) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.createdAt = createdAt;
    }
    
    // 비즈니스 메서드 - 입금 : 도메인 주도 설계 DDD 
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("입금액은 0보다 커야 합니다.");
        }
        this.balance = this.balance.add(amount);
    }
    
    // 비즈니스 메서드 - 출금
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("출금액은 0보다 커야 합니다.");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        this.balance = this.balance.subtract(amount);
    }
    
    // Getter/Setter 메서드들
    public Long getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public Long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    // toString() - 디버깅과 로깅에 유용
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                '}';
    }
}