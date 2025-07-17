CREATE TABLE spring_product ( 
-- 상품 아이디 (Primary Key, 자동 증가) 
product_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 고유 식별자', 
-- 상품명 (필수, 최대 100자) 
product_name VARCHAR(100) NOT NULL COMMENT '상품명', 
-- 제조사 (필수, 최대 50자) 
manufacturer VARCHAR(50) NOT NULL COMMENT '제조사명', 
-- 가격 (필수, 소수점 2자리까지 지원, 기본값 0) 
price DECIMAL(12, 2) NOT NULL DEFAULT 0.00 COMMENT '상품 가격', 
-- 등록일시 (자동으로 현재 시간 설정) 
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시'
);
-- 상품명 검색을 위한 인덱스
CREATE INDEX idx_spring_product_name ON spring_product(product_name);

-- 제조사별 조회를 위한 인덱스
CREATE INDEX idx_spring_product_manufacturer ON spring_product(manufacturer);

-- 가격 범위 검색을 위한 인덱스
CREATE INDEX idx_spring_product_price ON spring_product(price);

-- 등록일시 정렬을 위한 인덱스
CREATE INDEX idx_spring_product_created_at ON spring_product(created_at);

-- 복합 인덱스 (제조사 + 가격 조건 검색 최적화)
CREATE INDEX idx_spring_product_manufacturer_price ON spring_product(manufacturer, price);




INSERT INTO spring_product (product_name, manufacturer, price) VALUES
('맥북 프로 16인치', 'Apple', 2800000.00),
('아이패드 에어 5세대', 'Apple', 950000.00),
('갤럭시 Z 폴드 6', '삼성', 2500000.00),
('갤럭시 탭 S9', '삼성', 1200000.00),
('LG 그램 17인치', 'LG', 1800000.00),
('LG 스탠바이미', 'LG', 1000000.00),
('소니 WH-1000XM5', 'Sony', 450000.00),
('다이슨 에어랩', 'Dyson', 600000.00),
('샤오미 로봇청소기', '샤오미', 350000.00),
('삼성 비스포크 냉장고', '삼성', 3000000.00);

COMMIT


