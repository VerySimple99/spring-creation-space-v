CREATE TABLE news (
    news_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '뉴스 고유번호',
    news_title VARCHAR(200) NOT NULL COMMENT '뉴스 제목',
    news_content TEXT NOT NULL COMMENT '뉴스 내용',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시'
) 

-- ============================================
-- News 테이블 성능 최적화 인덱스 설계
-- Post-Redirect-Get 패턴 학습용 뉴스 테이블
-- ============================================

-- 현재 테이블 구조 확인
DESC news;

-- 기존 인덱스 확인
SHOW INDEX FROM news;

-- ============================================
-- 1. 핵심 인덱스 생성
-- ============================================
/*
=== DDL (Data Definition Language) - 데이터 정의어 ===
용도: 데이터베이스 구조(스키마)를 정의하고 변경
특징: 
- 즉시 커밋(Auto Commit) - 롤백 불가
- 데이터베이스 구조에 영향
- 메타데이터 변경
*/
-- 1-1. 등록일시 기준 정렬 인덱스 (가장 중요)
-- 용도: 최신순 뉴스 목록 조회 (ORDER BY created_at DESC)
-- 효과: 뉴스 목록 페이지 성능 대폭 향상
CREATE INDEX idx_news_created_at ON news(created_at DESC);

-- 1-2. 제목 검색 인덱스
-- 용도: 뉴스 제목으로 검색 (LIKE '%keyword%' 제외한 전방 일치 검색)
-- 효과: 제목 기반 검색 성능 향상
CREATE INDEX idx_news_title ON news(news_title);

-- 1-3. 복합 인덱스: 제목 + 등록일시
-- 용도: 제목 검색 결과를 날짜순으로 정렬할 때
-- 효과: 검색과 정렬을 동시에 최적화
CREATE INDEX idx_news_title_created_at ON news(news_title, created_at DESC);
