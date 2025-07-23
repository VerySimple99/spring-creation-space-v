INSERT INTO news (news_title, news_content) VALUES 
('Spring Boot 3.0 새로운 기능', 'Spring Boot 3.0에서는 Java 17 기반으로 많은 개선사항이 있습니다. 네이티브 이미지 지원과 관측 가능성(Observability) 기능이 크게 향상되었습니다.'),
('Post-Redirect-Get 패턴 이해하기', 'PRG 패턴은 사용자가 실수로 새로고침을 눌렀을 때 중복 제출을 방지하는 웹 개발의 필수 패턴입니다. POST 요청 후 즉시 GET 요청으로 리다이렉트합니다.'),
('MySQL 8.0 성능 최적화', 'MySQL 8.0에서는 새로운 옵티마이저와 JSON 기능 향상으로 데이터베이스 성능이 크게 개선되었습니다.');

COMMIT

-- ============================================
-- 3. 인덱스 효과 확인 쿼리
-- ============================================

-- 3-1. 최신 뉴스 10개 조회 (가장 빈번한 쿼리)
EXPLAIN SELECT news_id, news_title, created_at 
FROM news 
ORDER BY created_at DESC 
LIMIT 10;

-- 3-2. 제목으로 검색 (전방 일치)
EXPLAIN SELECT news_id, news_title, created_at 
FROM news 
WHERE news_title LIKE 'Spring%' 
ORDER BY created_at DESC;

-- 3-3. 특정 날짜 범위 뉴스 조회
EXPLAIN SELECT COUNT(*) 
FROM news 
WHERE created_at >= '2024-01-01' 
AND created_at < '2024-02-01';



-- ============================================
-- 인덱스 설계 원칙 및 주의사항
-- ============================================

/*
=== 인덱스 설계 핵심 원칙 ===

1. **조회 패턴 기반 설계**
   - 가장 자주 사용되는 쿼리 패턴 우선 고려
   - ORDER BY절에 사용되는 컬럼은 필수 인덱스

2. **카디널리티 고려**
   - 고유값이 많은 컬럼일수록 인덱스 효과 높음
   - created_at > news_title > news_id 순으로 효과적

3. **복합 인덱스 순서**
   - WHERE절 조건 → ORDER BY절 순서로 구성
   - 선택도가 높은 컬럼을 앞쪽에 배치

4. **인덱스 개수 제한**
   - 과도한 인덱스는 INSERT/UPDATE 성능 저하
   - 실제 사용되는 인덱스만 유지

=== 실무 활용 가이드 ===

1. **Post-Redirect-Get 패턴에 최적화**
   - 목록 조회: idx_news_created_at 활용
   - 검색 기능: idx_news_title 또는 복합 인덱스 활용

*/
