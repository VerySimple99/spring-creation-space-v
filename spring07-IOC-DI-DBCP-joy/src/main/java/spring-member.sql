-- AWS EC2 Server 에 설치한 오라클 상에 회원 테이블을 생성해 본다 
CREATE TABLE spring_member(
	id VARCHAR2(100) PRIMARY KEY,
	password VARCHAR2(100) NOT NULL,
	name VARCHAR2(100) NOT NULL,
	address VARCHAR2(100) NOT NULL
)
INSERT INTO spring_member(id,password,name,address) VALUES('java','a','아이유','오리');
SELECT * FROM spring_member;
SELECT COUNT(*) FROM spring_member;
COMMIT