-- MySQL 기반 Movie-Director 데이터베이스 스키마
-- Oracle과의 주요 차이점: AUTO_INCREMENT, 외래키 문법, 데이터 타입

-- 기존 테이블 삭제 (존재하는 경우)
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS director;

-- 감독 테이블 생성
-- Oracle과의 차이점:
-- 1. NUMBER → BIGINT (정수 타입)
-- 2. VARCHAR2 → VARCHAR (문자열 타입)  
-- 3. SEQUENCE → AUTO_INCREMENT (자동 증가)
CREATE TABLE director (
    director_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    director_name VARCHAR(100) NOT NULL,
    intro VARCHAR(100) NOT NULL
);

-- 영화 테이블 생성
-- Oracle과의 차이점:
-- 1. 외래키 제약조건 문법이 다름
-- 2. CONSTRAINT 이름 지정 방식 변경
CREATE TABLE movie (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    attendance BIGINT DEFAULT 0,
    director_id BIGINT NOT NULL,
    CONSTRAINT fk_movie_director_id 
        FOREIGN KEY (director_id) REFERENCES director(director_id)
        ON DELETE CASCADE  -- 감독 삭제시 영화도 함께 삭제
);

-- 테이블 조회
SELECT * FROM director;
SELECT * FROM movie;

-- 데이터 삭제 (테스트용)
DELETE FROM movie;     -- 외래키 때문에 movie를 먼저 삭제
DELETE FROM director;

-- MySQL에서는 AUTO_INCREMENT 값 초기화
ALTER TABLE movie AUTO_INCREMENT = 1;
ALTER TABLE director AUTO_INCREMENT = 1;

-- 트랜잭션 커밋
COMMIT;

-- ===============================================
-- 테스트 데이터 삽입 (실습용)
-- ===============================================

-- 감독 데이터 삽입
INSERT INTO director (director_name, intro) VALUES 
('봉준호', '기생충, 설국열차로 유명한 한국의 세계적 감독'),
('박찬욱', '올드보이, 아가씨로 유명한 스타일리시한 감독'),
('최동훈', '도둑들, 암살로 유명한 블록버스터 감독');

-- 영화 데이터 삽입
INSERT INTO movie (title, genre, attendance, director_id) VALUES 
('기생충', '드라마', 10085374, 1),
('설국열차', 'SF', 9347447, 1),
('올드보이', '스릴러', 3260000, 2),
('아가씨', '스릴러', 4226000, 2),
('도둑들', '액션', 12983330, 3);

COMMIT

-- ===============================================
-- JOIN 쿼리 예제
-- ===============================================

-- 1. 리스트 화면용: 영화ID, 영화제목, 감독명 조회
-- ANSI SQL (표준 SQL - MySQL에서 권장)
SELECT 
    m.movie_id,
    m.title,
    d.director_name
FROM movie m
INNER JOIN director d ON m.director_id = d.director_id
ORDER BY m.movie_id;

-- 2. 특정 영화의 상세 정보 (movie_id = 1)
-- 영화와 감독 정보를 모두 조회
SELECT 
    m.movie_id,
    m.title,
    m.genre,
    m.attendance,
    d.director_id,
    d.director_name,
    d.intro
FROM movie m
INNER JOIN director d ON m.director_id = d.director_id
WHERE m.movie_id = 1;

-- 3. 감독별 영화 목록 (감독명과 함께)
SELECT 
    d.director_name,
    m.title,
    m.genre,
    FORMAT(m.attendance, 0) AS formatted_attendance  -- MySQL의 숫자 포맷팅
FROM director d
LEFT JOIN movie m ON d.director_id = m.director_id
ORDER BY d.director_name, m.title;

-- 4. 관객수가 500만 이상인 영화와 감독 정보
SELECT 
    m.title,
    FORMAT(m.attendance, 0) AS '관객수',
    d.director_name AS '감독'
FROM movie m
INNER JOIN director d ON m.director_id = d.director_id
WHERE m.attendance >= 5000000
ORDER BY m.attendance DESC;

-- 5. 감독별 총 관객수 (집계 함수 사용)
SELECT 
    d.director_name AS '감독명',
    COUNT(m.movie_id) AS '영화수',
    FORMAT(SUM(m.attendance), 0) AS '총관객수',
    FORMAT(AVG(m.attendance), 0) AS '평균관객수'
FROM director d
LEFT JOIN movie m ON d.director_id = m.director_id
GROUP BY d.director_id, d.director_name
ORDER BY SUM(m.attendance) DESC;

-- ===============================================
-- 추가적인 MySQL 특화 기능들
-- ===============================================

-- 6. 최근 등록된 영화 3편 (MySQL의 LIMIT 사용)
SELECT 
    m.title,
    d.director_name,
    FORMAT(m.attendance, 0) AS '관객수'
FROM movie m
INNER JOIN director d ON m.director_id = d.director_id
ORDER BY m.movie_id DESC
LIMIT 3;

-- 7. 영화 제목에 특정 키워드가 포함된 경우 (대소문자 구분 없음)
SELECT 
    m.title,
    d.director_name
FROM movie m
INNER JOIN director d ON m.director_id = d.director_id
WHERE m.title LIKE '%기생%'   -- '기생'이 포함된 영화
   OR m.title LIKE '%도둑%';  -- '도둑'이 포함된 영화