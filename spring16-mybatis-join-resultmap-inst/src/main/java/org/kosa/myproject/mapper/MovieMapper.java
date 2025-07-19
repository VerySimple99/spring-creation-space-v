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
     * 전체 영화 수 조회
     * - 페이징 처리에서 사용
     * @return 전체 영화 수
     */
    long count();
    /**
     * 영화 ID로 특정 영화 조회 (감독 정보 포함)
     * - 영화 상세보기에서 사용
     * - Movie 객체와 내부 Director 객체 모두 완전히 채워서 반환
     * @param movieId 조회할 영화 ID
     * @return 감독 정보가 포함된 영화 정보, 없으면 null
     */
    Movie findById(@Param("movieId") Long movieId);
    
    /**
     * 모든 영화 목록 조회 (감독 정보 포함)
     * - JOIN을 통해 Movie 객체 내부의 Director 객체까지 완전히 채워서 반환
     * - 화면: 영화 목록 페이지
     * @return 감독 정보가 포함된 영화 목록
     */
    List<Movie> findAll();
}