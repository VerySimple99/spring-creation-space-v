package org.kosa.myproject.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 상품 정보를 담는 도메인 모델
 * 
 * 핵심 개념: Domain Model Pattern
 * - 비즈니스 로직과 데이터를 함께 담는 객체
 * - 실제 비즈니스 도메인의 개념을 코드로 표현
 */
public class Product {
    
    // 상품 고유 식별자// 인스턴스 변수에서는 null 가능성을 고려하여 Long을 선택
    private Long productId;
    
    // 상품명 - 비즈니스에서 가장 중요한 식별 정보
    private String productName;
    
    // 제조사 - 상품의 출처와 품질을 나타내는 중요한 속성
    private String manufacturer;
    
    // 가격 - BigDecimal 사용으로 정확한 금액 계산 보장
    private BigDecimal price;
    
    // 등록일시 - 시간 정보로 데이터의 생명주기 추적
    private LocalDateTime createdAt;
    
    // 기본 생성자 - MyBatis가 객체를 생성할 때 필요
    public Product() {}
    
    // 전체 필드 생성자 - 객체 생성시 모든 값을 한번에 설정
    public Product(Long productId, String productName, String manufacturer, 
                   BigDecimal price, LocalDateTime createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.price = price;
        this.createdAt = createdAt;
    }
    
    // 비즈니스 생성자 - 새로운 상품 등록시 사용 (ID와 생성시간 제외)
    public Product(String productName, String manufacturer, BigDecimal price) {
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.price = price;
    }
    
    // Getter & Setter 메서드들
    // MyBatis가 결과를 매핑할 때 이 메서드들을 사용합니다
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // toString 메서드 - 디버깅과 로깅에 유용
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}