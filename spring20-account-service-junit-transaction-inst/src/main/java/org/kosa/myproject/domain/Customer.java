package org.kosa.myproject.domain;

/**
 * 고객 정보를 담는 도메인 클래스
 * - 은행의 고객 정보를 표현
 * - 기본적인 고객 식별 정보만 포함
 */
public class Customer {
    
    private Long customerId;    // 고객 ID (기본키)
    private String name;        // 고객명
    private String email;       // 이메일
    
    // 기본 생성자 - MyBatis에서 필요
    public Customer() {}
    
    // 생성자 - 새 고객 등록 시 사용 (ID는 DB에서 자동 생성)
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // 전체 생성자 - DB 조회 결과 매핑 시 사용
    public Customer(Long customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }
    
    // Getter/Setter 메서드들
    public Long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    // toString() - 디버깅과 로깅에 유용
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}