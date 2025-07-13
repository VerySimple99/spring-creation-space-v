CREATE TABLE spring_account(
	account_no NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	balance NUMBER NOT NULL
)
CREATE SEQUENCE spring_account_seq;

-- 계좌 등록 SQL 
INSERT INTO spring_account(account_no,name,balance) VALUES(spring_account_seq.nextval,'손흥민',100);

INSERT INTO spring_account(account_no,name,balance) VALUES(spring_account_seq.nextval,'이강인',300);
INSERT INTO spring_account(account_no,name,balance) VALUES(spring_account_seq.nextval,'황희찬',200);
COMMIT 

-- 계좌 조회 
SELECT account_no,name,balance FROM spring_account WHERE account_no=1;
-- mybatis 적용시 현재 AccountVO 의 accountNo 변수명과 ACCOUNT TABLE의 account_no 컬럼명이 다르므로 
-- 위 sql 을 그대로 적용하면 accountNo 정보는 조회되지 않는다. 이렇게 컬럼명과 인스턴스 변수명이 불일치할 경우에는 
-- 별칭을 이용하면 된다 ( 또는 MyBatis 설정으로 자동 매핑하게 할 수도 있다 ) 
SELECT account_no as accountNo,name,balance FROM spring_account WHERE account_no=1;

SELECT * FROM spring_account;

SELECT account_no,name,balance FROM spring_account ORDER BY account_no DESC

SELECT count(*) FROM spring_account

SELECT account_no,name,balance FROM spring_account WHERE balance>250 ORDER BY balance DESC


















