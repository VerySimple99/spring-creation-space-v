select * from spring_member;

select id,password,name,address from spring_member where id='java';

select distinct address from spring_member;

select id,name from spring_member where address='토트넘'

-- login sql 
select id,password,name,address from spring_member where id='java' and password='a';
select id,password,name,address from spring_member where id='java' and password='b';

-- update sql 
select id,password,name,address from spring_member where id='java'
update spring_member set password='b',name='아이유2',address='종로' where id='java';










