drop table movie;
drop table director;

create table director(
	director_id number primary key,
	director_name varchar2(100) not null,
	intro varchar2(100) not null
)
drop sequence director_seq;
create sequence director_seq;

select * from director;


drop table movie;

create table movie(
	movie_id number primary key,
	title varchar2(100) not null,
	genre varchar2(100) not null,
	attendance number default 0,
	director_id number not null
	constraint fk_spring_director_id references director(director_id)
)
drop sequence movie_seq;
create sequence movie_seq;

select * from movie;
select * from director;

delete from movie;
delete from director;

commit

--  리스트 화면에서는  영화아이디,영화타이틀과 감독명 리스트를 보여준다 
--  이를 위한 join sql을 작성해본다 
-- ANSI SQL
SELECT m.movie_id,m.title,d.director_name
FROM movie m
INNER JOIN  director d ON m.director_id=d.director_id
-- Oracle SQL
SELECT m.movie_id,m.title,d.director_name
FROM movie m,director d
WHERE m.director_id=d.director_id

-- movie_id  1 에 대한 영화와 감독정보를 모두 조회 ( join )
SELECT m.movie_id,m.title,m.genre,m.attendance,d.director_id,d.director_name,intro
FROM movie m
INNER JOIN  director d ON m.director_id=d.director_id
WHERE movie_id=1

















