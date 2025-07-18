package org.kosa.myproject.mapper;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.myproject.domain.Product;

/**
 * Product 데이터 접근을 위한 MyBatis Mapper 인터페이스
 * 
 * 핵심 개념: Repository Pattern
 * - 데이터 액세스 로직을 캡슐화
 * - 비즈니스 로직과 데이터 액세스 로직을 분리
 * - 테스트 가능성과 유지보수성 향상
 */
@Mapper
public interface ProductMapper {

    /**
     * 모든 상품 조회
     * @return 전체 상품 목록
     */
    List<Product> findAll();

    /**
     * ID로 특정 상품 조회
     * @param productId 상품 ID
     * @return 해당 상품 정보 (없으면 null)
     */
    Product findById(Long productId);

    /**
     * 새로운 상품 등록
     * useGeneratedKeys="true"로 자동 생성된 ID를 Product 객체에 주입
     * @param product 등록할 상품 정보
     * @return 처리된 행의 수 (1이면 성공)
     */
    int insert(Product product);

    /**
     * 상품 정보 수정
     * @param product 수정할 상품 정보
     * @return 처리된 행의 수 (1이면 성공)
     */
    int update(Product product);

    /**
     * 상품 삭제
     * @param productId 삭제할 상품 ID
     * @return 처리된 행의 수 (1이면 성공)
     */
    int delete(Long productId);

    /**
     * 제조사별 상품 조회
     * @param manufacturer 제조사명
     * @return 해당 제조사의 상품 목록
     */
    List<Product> findByManufacturer(String manufacturer);

    /**
     * 가격 범위별 상품 조회
     * @Param 애노테이션으로 파라미터명을 명시적으로 지정
     * @param minPrice 최소 가격
     * @param maxPrice 최대 가격
     * @return 해당 가격 범위의 상품 목록
     */
    List<Product> findByPriceRange(@Param("minPrice") BigDecimal minPrice,
                                   @Param("maxPrice") BigDecimal maxPrice);

    /**
     * 동적 조건으로 상품 검색 (Map 방식)
     * 
     * 핵심 개념: 동적 SQL with Map
     * - Map을 사용하여 검색 조건과 도메인 모델을 분리
     * - 유연한 검색 조건 조합 가능
     * - 실무에서 가장 많이 사용되는 패턴
     * 
     * @param searchConditions 검색 조건을 담은 Map
     *        지원되는 키:
     *        - "productName": 상품명 (부분 검색, LIKE '%keyword%')
     *        - "manufacturer": 제조사 (정확히 일치)
     *        - "minPrice": 최소 가격 (이상 조건)
     *        - "maxPrice": 최대 가격 (이하 조건)
     * 
     * 사용 예시:
     * Map<String, Object> conditions = new HashMap<>();
     * conditions.put("manufacturer", "삼성");
     * conditions.put("minPrice", new BigDecimal("1000000"));
     * 
     * @return 검색 조건에 맞는 상품 목록
     */
    List<Product> findProductsDynamic(Map<String, Object> searchConditions);

    /**
     * 동적 필드로 상품 정보 수정 (Map 방식)
     * 
     * 핵심 개념: 선택적 업데이트
     * - 변경할 필드만 Map에 포함하여 전달
     * - null 체크를 통한 동적 SET 절 구성
     * - 불필요한 업데이트 방지로 성능 향상
     * 
     * @param updateData 수정할 데이터를 담은 Map
     *        필수: "productId" (수정 대상 식별)
     *        선택: "productName", "manufacturer", "price"
     * 

     * 
     * @return 처리된 행의 수 (1이면 성공)
     */
    int updateDynamic(Product paramProduct);

    /**
     * 여러 ID를 이용해 상품 삭제
     * 
     * 핵심 개념: IN 조건을 활용한 배치 삭제
     * - 여러 레코드를 한 번의 쿼리로 삭제
     * - 성능상 효율적인 방식
     * 
     * @param productIds 삭제할 상품 ID 목록
     * @return 삭제된 행의 수
     */
    int deleteByIds(List<Long> productIds);
}