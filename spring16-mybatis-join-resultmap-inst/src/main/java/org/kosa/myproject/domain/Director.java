package org.kosa.myproject.domain;

import java.time.LocalDateTime;

/**
 * 감독 정보를 담는 도메인 모델
 * 
 * 핵심 개념: Domain-Driven Design (DDD)
 * - 비즈니스 도메인의 핵심 개념을 코드로 표현
 * - 감독(Director)은 영화 산업에서 중요한 비즈니스 개체
 */
public class Director {
    
    // 감독 고유 식별자
    private Long directorId;
    
    // 감독명 - 비즈니스에서 가장 중요한 식별 정보
    private String directorName;
    
    // 감독 소개 - 감독의 특징이나 대표작을 설명
    private String intro;
    
    // 등록일시 - 데이터의 생명주기 추적 (선택사항)
    private LocalDateTime createdAt;
    
    // 기본 생성자 - MyBatis가 객체를 생성할 때 필요
    public Director() {
        super();
    }
    
    // 비즈니스 생성자 - 새로운 감독 등록시 사용 (ID 제외)
    public Director(String directorName, String intro) {
        super();
        this.directorName = directorName;
        this.intro = intro;
    }
    
    // 전체 필드 생성자 - 완전한 객체 생성시 사용
    public Director(Long directorId, String directorName, String intro) {
        super();
        this.directorId = directorId;
        this.directorName = directorName;
        this.intro = intro;
    }
    
    // === Getter & Setter 메서드들 ===
    // MyBatis가 결과를 매핑할 때 이 메서드들을 사용
    
    public Long getDirectorId() {
        return directorId;
    }
    
    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
    
    public String getDirectorName() {
        return directorName;
    }
    
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    public String getIntro() {
        return intro;
    }
    
    public void setIntro(String intro) {
        this.intro = intro;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // === 비즈니스 메서드들 ===
    
    /**
     * 감독명의 유효성을 검증하는 비즈니스 로직
     * @return 유효하면 true, 아니면 false
     */
    public boolean isValidDirectorName() {
        return directorName != null && 
               !directorName.trim().isEmpty() && 
               directorName.length() <= 100;
    }
    
    /**
     * 소개글의 유효성을 검증하는 비즈니스 로직
     * @return 유효하면 true, 아니면 false
     */
    public boolean isValidIntro() {
        return intro != null && 
               !intro.trim().isEmpty() && 
               intro.length() <= 100;
    }
    
    // toString 메서드 - 디버깅과 로깅에 유용
    @Override
    public String toString() {
        return "Director{" +
                "directorId=" + directorId +
                ", directorName='" + directorName + '\'' +
                ", intro='" + intro + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
    
    // equals & hashCode - 컬렉션에서 중복 제거나 비교시 필요
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Director director = (Director) obj;
        return directorId != null ? directorId.equals(director.directorId) : director.directorId == null;
    }
    
    @Override
    public int hashCode() {
        return directorId != null ? directorId.hashCode() : 0;
    }
}
