DROP TABLE product;

-- 상품 테이블 생성
CREATE TABLE product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품번호',
    product_name VARCHAR(100) NOT NULL COMMENT '상품명',
    maker VARCHAR(50) NOT NULL COMMENT '제조사',
    price DECIMAL(10,2) NOT NULL COMMENT '가격',
    stock_quantity INT DEFAULT 0 COMMENT '재고수량',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시'
) COMMENT='상품 테이블';

-- 샘플 데이터 삽입
INSERT INTO product (product_name, maker, price, stock_quantity) VALUES
('갤럭시 S24', '삼성', 1200000.00, 10),
('아이폰 15', '애플', 1300000.00, 5),
('LG 그램 노트북', 'LG', 1500000.00, 3),
('Surface Pro', '마이크로소프트', 1100000.00, 7),
('맥북 에어', '애플', 1400000.00, 2);

COMMIT;