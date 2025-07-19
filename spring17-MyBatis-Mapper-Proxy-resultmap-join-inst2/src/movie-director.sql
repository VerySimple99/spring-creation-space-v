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

<!-- 
감독별 통계 정보 조회 (영화가 있는 감독만)
- INNER JOIN: 영화가 있는 감독만 조회 (더 직관적)
- GROUP BY: 감독별로 데이터를 묶어서 집계
- 집계함수: SUM(합계), AVG(평균) 사용
- COALESCE 불필요: INNER JOIN으로 NULL 값 자동 제거
-->
<select id="findDirectorStatistics" resultType="map">
    SELECT 
        d.director_id as directorId,
        d.director_name as directorName,
        SUM(m.attendance) as totalAttendance,
        ROUND(AVG(m.attendance), 0) as averageAttendance
    FROM director d
    INNER JOIN movie m ON d.director_id = m.director_id
    GROUP BY d.director_id, d.director_name
    ORDER BY totalAttendance DESC
</select>








