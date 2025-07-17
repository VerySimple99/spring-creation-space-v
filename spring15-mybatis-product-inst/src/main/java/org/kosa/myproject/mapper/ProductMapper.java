package org.kosa.myproject.mapper;

import java.util.List;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.myproject.domain.Product; // Product 클래스 경로에 맞게 수정

/**
* Product 데이터 접근을 위한 MyBatis Mapper 인터페이스
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
  * @param minPrice 최소 가격
  * @param maxPrice 최대 가격
  * @return 해당 가격 범위의 상품 목록
  */
 List<Product> findByPriceRange(@Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice);
}
