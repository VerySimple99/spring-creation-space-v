package org.kosa.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.myproject.domain.Product;

/**
 * 상품 관련 데이터베이스 접근을 담당하는 MyBatis Mapper 인터페이스
 * - @Mapper 어노테이션으로 MyBatis가 자동으로 구현체 생성
 * - XML 파일과 매핑되어 실제 SQL 쿼리 실행
 */
@Mapper
public interface ProductMapper {
	int getTotalProductCount();
    
    /**
     * 모든 상품 목록 조회
     * @return 상품 목록 (List<Product>)
     */
    List<Product> selectAllProducts();
    
    /**
     * 상품번호로 특정 상품 조회
     * @param productId 상품번호
     * @return 상품 정보 (Product)
     */
    Product selectProductById(@Param("productId") Long productId);
    
    /**
     * 새로운 상품 등록
     * @param product 등록할 상품 정보
     * @return 영향받은 행의 수 (insert 성공 시 1)
     */
    int insertProduct(Product product);
    
    /**
     * 상품 정보 수정
     * @param product 수정할 상품 정보
     * @return 영향받은 행의 수 (update 성공 시 1)
     */
    int updateProduct(Product product);
    
    /**
     * 상품 삭제
     * @param productId 삭제할 상품번호
     * @return 영향받은 행의 수 (delete 성공 시 1)
     */
    int deleteProduct(@Param("productId") Long productId);
}