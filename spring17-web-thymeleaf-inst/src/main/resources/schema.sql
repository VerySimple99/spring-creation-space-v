-- 사용자 테이블 생성 (이미 존재하면 삭제 후 생성)
DROP TABLE IF EXISTS users;

/*
 src/main/resources에 schema.sql과 data.sql을 사용
 애플리케이션 시작 시점에 자동으로 실행
 스프링 부트는 애플리케이션 시작 시 데이터베이스 스키마를 자동으로 초기화하는 강력한 기능을 제공합니다. 
 특정 위치에 SQL 파일을 두면, 스프링 부트가 이를 감지하고 실행해줍니다.
  
 DDL 파일: src/main/resources/schema.sql
 용도: 데이터베이스 테이블, 인덱스, 제약 조건 등 스키마를 정의하는 DDL 문을 포함합니다
 
 DML 파일: src/main/resources/data.sql
 용도: 애플리케이션 시작 시 초기 데이터를 삽입하는 DML(Data Manipulation Language) 문을 포함합니다.

 */
CREATE TABLE users (
        id BIGINT PRIMARY KEY AUTO_INCREMENT, -- 여기에 AUTO_INCREMENT 추가
        username VARCHAR(100) NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL,
        birth_date DATE NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

/*
 인덱스 많을수록 SELECT 빨라짐
INSERT/UPDATE 시에는 인덱스 갱신 비용 발생
기본적으로 3-4개 인덱스면 충분
 */
-- 인덱스 추가 (선택 사항)
CREATE INDEX idx_users_username ON users(username);
-- 용도: 사용자명으로 검색할 때 사용 (로그인, 검색 기능)

-- 생년월일 범위 검색용 인덱스  
CREATE INDEX idx_users_birth_date ON users(birth_date);
-- 용도: 나이대별 조회, 생일자 조회 등

-- 가입일 정렬용 인덱스
CREATE INDEX idx_users_created_at ON users(created_at);
-- 용도: 최신 가입자 조회, 가입일 기준 정렬

-- 사용자명 + 가입일 복합 인덱스
CREATE INDEX idx_users_username_created ON users(username, created_at);
-- 용도: 특정 사용자의 가입일 조회 시 효율적

-- 생년월일 + 가입일 복합 인덱스  
CREATE INDEX idx_users_birth_created ON users(birth_date, created_at);
-- 용도: 특정 연령대의 가입 패턴 분석 시 사용
