package org.kosa.myproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.myproject.domain.News;
import java.util.List;

/**
 * Post-Redirect-Get 패턴 학습용 News 매퍼 인터페이스
 * MyBatis와 연동하여 뉴스 데이터 처리를 담당
 */
@Mapper
public interface NewsMapper {
    
    /**
     * 전체 뉴스 개수 조회
     * @return 뉴스 총 개수
     */
    int selectNewsCount();
    
    /**
     * 전체 뉴스 목록 조회 (최신순)
     * @return 뉴스 목록
     */
    List<News> selectNewsList();
    
    /**
     * 뉴스 등록
     * @param news 등록할 뉴스 정보
     * @return 등록된 행 수 (성공시 1)
     */
    int insertNews(News news);
    
    /**
     * 뉴스 ID로 단일 뉴스 조회 (향후 상세보기용)
     * @param newsId 뉴스 ID
     * @return 뉴스 정보
     */
    News selectNewsById(Long newsId);
}