package org.kosa.myproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.myproject.domain.Movie;
import java.util.List;

/**
 * 영화(Movie) 데이터 접근을 위한 MyBatis Mapper 인터페이스
 * 
 * 핵심 설계 원칙:
 * 1. Single Responsibility: 영화 관련 데이터 접근만 담당
 * 2. Join Query: 감독 정보를 포함한 완전한 Movie 객체 반환
 * 3. 성능 최적화: 필요한 데이터만 조회하는 다양한 메서드 제공
 */
@Mapper
public interface MovieMapper {
    
    /**
     * 모든 영화 목록 조회 (감독 정보 포함)
     * - JOIN을 통해 Movie 객체 내부의 Director 객체까지 완전히 채워서 반환
     * - 화면: 영화 목록 페이지
     * @return 감독 정보가 포함된 영화 목록
     */
    List<Movie> findAll();
    
    /**
     * 영화 ID로 특정 영화 조회 (감독 정보 포함)
     * - 영화 상세보기에서 사용
     * - Movie 객체와 내부 Director 객체 모두 완전히 채워서 반환
     * @param movieId 조회할 영화 ID
     * @return 감독 정보가 포함된 영화 정보, 없으면 null
     */
    Movie findById(@Param("movieId") Long movieId);
    
    /**
     * 감독 ID로 해당 감독의 영화 목록 조회
     * - 감독 상세페이지에서 "이 감독의 다른 작품" 목록 표시용
     * @param directorId 감독 ID
     * @return 해당 감독의 영화 목록 (감독 정보 포함)
     */
    List<Movie> findByDirectorId(@Param("directorId") Long directorId);
    
    /**
     * 장르별 영화 검색 (감독 정보 포함)
     * - 영화 검색 기능에서 사용
     * @param genre 장르명 (예: "드라마", "액션")
     * @return 해당 장르의 영화 목록
     */
    List<Movie> findByGenre(@Param("genre") String genre);
    
    /**
     * 관객수 기준으로 영화 검색 (감독 정보 포함)
     * - 흥행작 랭킹 페이지에서 사용
     * @param minAttendance 최소 관객수
     * @return 지정된 관객수 이상의 영화 목록 (관객수 내림차순)
     */
    List<Movie> findByAttendanceGreaterThan(@Param("minAttendance") Long minAttendance);
    
    /**
     * 영화 제목으로 검색 (부분 검색, 감독 정보 포함)
     * - 영화 검색 기능에서 사용
     * @param title 검색할 제목 (부분 문자열)
     * @return 제목에 해당 문자열이 포함된 영화 목록
     */
    List<Movie> findByTitleContaining(@Param("title") String title);
    
    // === CUD(Create, Update, Delete) 메서드들 ===
    
    /**
     * 새로운 영화 등록
     * - 영화 등록 폼에서 사용
     * - director_id는 Movie 객체의 director.directorId 값 사용
     * @param movie 등록할 영화 정보
     * @return 영향받은 행의 수 (성공시 1)
     */
    int insert(Movie movie);
    
    /**
     * 영화 정보 수정
     * - 영화 수정 폼에서 사용
     * @param movie 수정할 영화 정보 (movieId 포함)
     * @return 영향받은 행의 수 (성공시 1)
     */
    int update(Movie movie);
    
    /**
     * 영화 삭제
     * - 영화 관리 페이지에서 사용
     * @param movieId 삭제할 영화 ID
     * @return 영향받은 행의 수 (성공시 1)
     */
    int deleteById(@Param("movieId") Long movieId);
    
    // === 통계 및 집계 메서드들 ===
    
    /**
     * 전체 영화 수 조회
     * - 페이징 처리에서 사용
     * @return 전체 영화 수
     */
    long count();
    
    /**
     * 특정 감독의 영화 수 조회
     * - 감독 상세페이지에서 사용
     * @param directorId 감독 ID
     * @return 해당 감독의 영화 수
     */
    long countByDirectorId(@Param("directorId") Long directorId);
    
    /**
     * TOP N 흥행작 조회 (관객수 기준)
     * - 메인 페이지의 "흥행작 랭킹" 섹션에서 사용
     * @param limit 조회할 영화 수 (예: 10)
     * @return 관객수 상위 N개 영화 (감독 정보 포함)
     */
    List<Movie> findTopMoviesByAttendance(@Param("limit") int limit);
}