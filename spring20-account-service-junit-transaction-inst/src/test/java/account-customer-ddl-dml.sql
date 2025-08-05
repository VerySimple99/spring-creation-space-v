DROP TABLE customer;
-- 고객 테이블 생성
CREATE TABLE customer (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '고객 ID (기본키)',
    name VARCHAR(50) NOT NULL COMMENT '고객명',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '이메일 (고유값)'
) COMMENT = '고객 정보 테이블';

-- COMMENT 사용 장점 :  가독성 , 이해도 향상 , 유지보수 용이성 

DROP TABLE account;
-- 계좌 테이블 생성
CREATE TABLE account (
    account_number BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '계좌번호 (기본키)',
    customer_id BIGINT NOT NULL COMMENT '고객 ID (외래키)',
    account_type VARCHAR(20) NOT NULL COMMENT '계좌 유형',
    balance DECIMAL(15,2) DEFAULT 0.00 COMMENT '잔액',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '계좌 개설일시',
    
    -- 제약조건
    CONSTRAINT chk_account_type 
        CHECK (account_type IN ('예적금계좌', '입출금계좌')),
    CONSTRAINT chk_balance_positive 
        CHECK (balance >= 0),
    CONSTRAINT fk_account_customer 
        FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
) COMMENT = '계좌 정보 테이블';

-- 인덱스 생성 (성능 최적화)
CREATE INDEX idx_customer_email ON customer(email);
CREATE INDEX idx_account_customer ON account(customer_id);

-- 데이터 삽입
INSERT INTO customer (name, email) VALUES 
('손흥민', 'son@gmail.com'),
('이강인', 'lee@naver.com'),
('배준호', 'bae@daum.net');

INSERT INTO account (customer_id, account_type, balance) VALUES 
(1, '입출금계좌', 1000.00),
(1, '예적금계좌', 50000.00),
(2, '입출금계좌', 750.00),
(3, '입출금계좌', 20.00);

COMMIT

SELECT * FROM customer;
-- 계좌 이체를 통한 트랜잭션 테스트를 위한 조회
/*
    계좌 번호 1 번 손흥민 입출금계좌 1000원
    계좌 번호 3 번 이강인 입출금계좌 750원
*/    
SELECT a.account_number,
			c.name,
			a.account_type,
			a.balance
FROM account a
INNER JOIN customer
c ON a.customer_id = c.customer_id
ORDER BY a.account_number










