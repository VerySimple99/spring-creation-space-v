package org.kosa.myproject.domain;

import java.time.LocalDate;
import java.time.Period;

/**
 *  * 핵심 개념: Domain-Driven Design (DDD)
 * - 비즈니스 도메인의 핵심 개념을 코드로 표현
 * 
 * 실무에 적합한 사용자 클래스
 * 변하지 않는 데이터(생년월일)를 저장하고 필요시 계산
 */
public class User {
    private String name;
    private String email;
    private LocalDate birthDate;  // 나이 대신 생년월일 저장
    
    public User(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
    
    // Getter들
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDate getBirthDate() { return birthDate; }
    
    /**
     * 나이는 실시간 계산으로 제공
     * 항상 정확한 나이를 반환
     */
    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    
    /**
     * 성인 여부 판단 메소드
     * 비즈니스 로직을 객체 내부에 캡슐화
     */
    public boolean isAdult() {
        return getAge() >= 20;  // 한국 기준
    }
}
