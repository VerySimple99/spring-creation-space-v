-- 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS point_tx;
DROP TABLE IF EXISTS member_tx;

-- member_tx 테이블 생성
CREATE TABLE member_tx (
    id INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL
);

-- member_tx에 데이터 삽입
INSERT INTO member_tx(password, name, address)
VALUES('1234', '아이유', '판교');

-- point_tx 테이블 생성

CREATE TABLE point_tx (
    point_tx_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL, -- member_tx의 id를 참조할 외래 키
    point INT NOT NULL,
    point_type VARCHAR(100) NOT NULL,
    -- 외래 키 제약 조건 추가
    CONSTRAINT fk_member_id FOREIGN KEY(member_id) REFERENCES member_tx(id)
);

-- point_tx에 데이터 삽입
INSERT INTO point_tx(member_id, point, point_type) VALUES(1, 100, 'cgv');

-- 부모 키가 없는 경우 (member_id 2는 member_tx에 없다고 가정)
-- 이 삽입은 외래 키 제약 조건으로 인해 실패
-- INSERT INTO point_tx(member_id, point, point_type) VALUES(2, 100, 'cgv');

COMMIT

-- 데이터 조회
SELECT * FROM member_tx;
SELECT * FROM point_tx;

-- 테스트용 : 데이터 삭제 (외래 키 제약 조건으로 인해 point_tx 먼저 삭제)
DELETE FROM point_tx;
DELETE FROM member_tx;

