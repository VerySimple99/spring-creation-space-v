-- 기존 product 테이블
CREATE TABLE product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품번호',
    product_name VARCHAR(100) NOT NULL COMMENT '상품명',
    maker VARCHAR(50) NOT NULL COMMENT '제조사',
    price DECIMAL(10,2) NOT NULL COMMENT '가격',
    stock_quantity INT DEFAULT 0 COMMENT '재고수량',  -- 추가
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시'
);

-- 상품 입고 이력 테이블 (새로 추가)
CREATE TABLE product_stock_history (
    history_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '이력번호',
    product_id BIGINT NOT NULL COMMENT '상품번호',
    quantity_change INT NOT NULL COMMENT '수량변화', -- 양수: 입고, 음수: 출고
    change_type ENUM('IN', 'OUT') NOT NULL COMMENT '변화타입',
    change_reason VARCHAR(100) COMMENT '변화사유',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '처리일시',
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- 샘플 데이터
INSERT INTO product (product_name, maker, price, stock_quantity) VALUES
('갤럭시 S24', '삼성', 1200000.00, 10),
('아이폰 15', '애플', 1300000.00, 5),
('LG 그램 노트북', 'LG', 1500000.00, 3);