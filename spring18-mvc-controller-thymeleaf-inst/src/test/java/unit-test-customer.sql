CREATE TABLE spring_customer (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255)
);

select * from SPRING_CUSTOMER;

select id,name,address from spring_customer where id='java';

insert into spring_customer(id,name,address) values('ajax1','안정환','도하');
insert into spring_customer(id,name,address) values('ajax2','백승호','도하');
insert into spring_customer(id,name,address) values('ajax3','벤투','도하');
-- id가 존재하면 1 , 존재하지 않으면 0 
select count(*) from spring_customer where id='green';

select id,name,address from spring_customer where address='도하'

commit









