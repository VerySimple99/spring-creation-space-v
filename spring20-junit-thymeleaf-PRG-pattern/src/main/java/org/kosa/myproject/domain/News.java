// 1. News.java - 도메인 클래스
// org/kosa/myproject/domain/News.java
package org.kosa.myproject.domain;

import java.time.LocalDateTime;

/**
 * Post-Redirect-Get 패턴 학습용 News 도메인 클래스
 * 뉴스 정보를 담는 간단한 데이터 객체
 */
public class News {
    
    // 뉴스 고유번호 (Primary Key)
    private Long newsId;    
    // 뉴스 제목
    private String newsTitle;    
    // 뉴스 내용
    private String newsContent;    
    // 등록일시
    private LocalDateTime createdAt;    
    // 기본 생성자 (MyBatis에서 필수)
    public News() {}    
    // 뉴스 등록용 생성자 (ID와 등록일시는 자동 생성)
    public News(String newsTitle, String newsContent) {
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
    }
    
    // 전체 필드 생성자
    public News(Long newsId, String newsTitle, String newsContent, LocalDateTime createdAt) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.createdAt = createdAt;
    }
    
    // Getter & Setter 메서드들
    public Long getNewsId() {
        return newsId;
    }
    
    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }
    
    public String getNewsTitle() {
        return newsTitle;
    }
    
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    
    public String getNewsContent() {
        return newsContent;
    }
    
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // toString() - 디버깅 및 로깅용
    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}