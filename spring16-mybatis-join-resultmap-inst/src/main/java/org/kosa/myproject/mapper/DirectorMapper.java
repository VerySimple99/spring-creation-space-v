package org.kosa.myproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.myproject.domain.Director;

/**
 * 감독(Director) 데이터 접근을 위한 MyBatis Mapper 인터페이스
 * 
 * 핵심 설계 원칙:
 * 1. Single Responsibility: 감독 관련 데이터 접근만 담당
 * 2. Simple Query: 감독 정보만 조회 (영화 목록은 MovieMapper 담당)
 * 3. 참조 무결성: 영화가 있는 감독은 삭제 불가 등의 비즈니스 룰 지원
 */
@Mapper
public interface DirectorMapper {
    
    /**
     * 모든 감독 목록 조회
     * - 감독 관리 페이지에서 사용
     * - 영화 등록시 감독 선택 드롭다운에서 사용
     * @return 모든 감독 목록
     */
    List<Director> findAll();
    
    /**
     * 감독 ID로 특정 감독 조회
     * - 감독 상세보기에서 사용
     * - 영화 등록/수정시 감독 유효성 검증에서 사용
     * @param directorId 조회할 감독 ID
     * @return 감독 정보, 없으면 null
     */
    Director findById(@Param("directorId") Long directorId);
    
    // === CUD(Create, Update, Delete) 메서드들 ===
    
    /**
     * 새로운 감독 등록
     * - 감독 등록 폼에서 사용
     * @param director 등록할 감독 정보
     * @return 영향받은 행의 수 (성공시 1)
     */
    int insert(Director director);
    
    /**
     * 감독 정보 수정
     * - 감독 수정 폼에서 사용
     * @param director 수정할 감독 정보 (directorId 포함)
     * @return 영향받은 행의 수 (성공시 1)
     */
    int update(Director director);
    
    /**
     * 감독 삭제
     * - 감독 관리 페이지에서 사용
     * - 주의: 영화가 있는 감독은 삭제 불가 (외래키 제약조건)
     * @param directorId 삭제할 감독 ID
     * @return 영향받은 행의 수 (성공시 1)
     */
    int deleteById(@Param("directorId") Long directorId);
    

    
    // === 통계 및 집계 메서드들 ===
    
    /**
     * 감독별 총 관객수 통계 조회
     * - 감독 랭킹 페이지에서 사용
     * - 주의: 이 메서드는 JOIN이 필요하므로 복잡한 쿼리
     * @return 감독별 통계 정보가 담긴 Map 또는 DTO 목록
     */
    List<Map<String,Object>> findDirectorStatistics();  // 필요시 추가
}