package org.kosa.myproject.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 상품 정보를 담는 DTO 클래스
 * - 데이터베이스와 화면 간 데이터 전송 역할
 * - MyBatis의 결과 매핑에 사용
 */
public class Product {
    
    private Long productId;        // 상품번호 (PK)
    private String productName;    // 상품명
    private String maker;          // 제조사
    private BigDecimal price;      // 가격
    private Integer stockQuantity; // 재고수량
    private LocalDateTime createdAt; // 등록일시
    
    // 기본 생성자 (MyBatis 매핑을 위해 필수)
    public Product() {}
    
    // 상품 등록용 생성자
    public Product(String productName, String maker, BigDecimal price) {
        this.productName = productName;
        this.maker = maker;
        this.price = price;
        this.stockQuantity = 0; // 기본값
    }
    
    // Getter/Setter 메서드들
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
    
    public String getMaker() {
        return maker;
    }
    
    public void setMaker(String maker) {
        this.maker = maker;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // toString 메서드 (디버깅용)
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", maker='" + maker + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", createdAt=" + createdAt +
                '}';
    }
}
