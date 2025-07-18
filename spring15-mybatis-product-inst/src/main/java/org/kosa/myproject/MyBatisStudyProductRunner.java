package org.kosa.myproject;

import java.math.BigDecimal;
import java.util.List;

import org.kosa.myproject.domain.Product;
import org.kosa.myproject.mapper.ProductMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyBatisStudyProductRunner implements CommandLineRunner {

    private final ProductMapper productMapper;

    // 생성자 주입
    public MyBatisStudyProductRunner(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("-------------------------------------");
        System.out.println("       MyBatis Dynamic Query 테스트 시작      ");
        System.out.println("-------------------------------------");     
        //  this.testDynamicUpdate();
        //  this.testDynamicSearch();
        this.testMultipleDelete();
        System.out.println("-------------------------------------");
        System.out.println("        MyBatis Dynamic Query 테스트 종료        ");
        System.out.println("-------------------------------------");
    }
    void testBasic() {
        System.out.println("-------------------------------------");
        System.out.println("          MyBatis Basic Mapper 테스트 시작         ");
        System.out.println("-------------------------------------");
        // 1. 새로운 상품 등록 테스트
//        System.out.println("[1. 상품 등록 테스트]");
//      Product newProduct = new Product(null, "갤럭시 S25", "삼성", new BigDecimal("1500000"), null);
//        int insertCount = productMapper.insert(newProduct);
//        System.out.println("등록 성공 여부 (1이면 성공): " + insertCount);
//        System.out.println("새로 등록된 상품 ID: " + newProduct.getProductId()); // useGeneratedKeys로 ID가 주입됨

        // 2. 모든 상품 조회 테스트
//        System.out.println("[2. 모든 상품 조회 테스트]");
//        List<Product> allProducts = productMapper.findAll();
//        allProducts.forEach(System.out::println);
//        System.out.println("총 상품 수: " + allProducts.size());

        // 3. ID로 상품 조회 테스트
//        System.out.println("[3. ID로 상품 조회 테스트]");
//        Long targetId = 1L;
//        Product foundProduct = productMapper.findById(targetId);
//       System.out.println("조회된 상품: " + foundProduct);

        // 4. 상품 정보 수정 테스트
//        System.out.println("[4. 상품 수정 테스트]");
//        if (foundProduct != null) {
//            foundProduct.setPrice(new BigDecimal("1450000")); // 가격 수정
//            foundProduct.setProductName("갤럭시 S25 Ultra"); // 이름 수정
//            int updateCount = productMapper.update(foundProduct);
//            System.out.println("수정 성공 여부 (1이면 성공): " + updateCount);
//            Product updatedProduct = productMapper.findById(targetId);
//            System.out.println("수정된 상품: " + updatedProduct);
//        }

        // 5. 제조사별 상품 조회 테스트
//        System.out.println("[5. 제조사별 상품 조회 테스트]");
//        List<Product> samsungProducts = productMapper.findByManufacturer("삼성");
//        samsungProducts.forEach(System.out::println);
//        System.out.println("삼성 제품 수: " + samsungProducts.size());

        // 6. 가격 범위별 상품 조회 테스트
//        System.out.println("[6. 가격 범위별 상품 조회 테스트]");
//        List<Product> priceRangeProducts = productMapper.findByPriceRange(new BigDecimal("1000000"), new BigDecimal("2000000"));
//        priceRangeProducts.forEach(System.out::println);
//        System.out.println("100만원 ~ 200만원 상품 수: " + priceRangeProducts.size());

        // 7. 상품 삭제 테스트
//        System.out.println("[7. 상품 삭제 테스트]");
//        int deleteCount = productMapper.delete(6L);
//        System.out.println("삭제 성공 여부 (1이면 성공): " + deleteCount);
//        Product deletedProduct = productMapper.findById(6L);
//        System.out.println("삭제 후 조회된 상품 (null): " + deletedProduct);
//
        System.out.println("-------------------------------------");
        System.out.println("          MyBatis Basic Mapper 테스트 종료         ");
        System.out.println("-------------------------------------");
    }
    /**
     * 동적 업데이트 테스트
     * 필요한 필드만 선택적으로 업데이트
     */
     void testDynamicUpdate() {
        System.out.println("[동적 업데이트 테스트]");
        
        // 먼저 업데이트할 상품 조회
        Product targetProduct = productMapper.findById(1L);
        if (targetProduct != null) {
            System.out.println("업데이트 전 상품: " + targetProduct);
            
            // 가격만 업데이트하는 Map 생성
            Product paramProduct = new Product();
            paramProduct.setProductId(1L);
           paramProduct.setProductName("아이폰 2");
            paramProduct.setManufacturer("Apple");
            paramProduct.setPrice(new BigDecimal("1270000"));     
           
            int updateCount = productMapper.updateDynamic(paramProduct);
            System.out.println("업데이트 성공 여부 (1이면 성공): " + updateCount);
            
            Product updatedProduct = productMapper.findById(1L);
            System.out.println("업데이트 후 상품: " + updatedProduct);
        }
    }
    /**
     * 동적 검색 테스트
     * Map을 활용한 다양한 검색 조건 조합 테스트
     */
    void testDynamicSearch() {
//        System.out.println("[1. 동적 검색 테스트]");
        // 1-1. 제조사로만 검색
//        System.out.println("1-1. 제조사로만 검색 (삼성)");
//        Map<String, Object> searchKeyword1 = new HashMap<>();
//        searchKeyword1.put("manufacturer", "삼성");
//        List<Product> result1 = productMapper.findProductsDynamic(searchKeyword1);
//        printSearchResult("삼성 제품", result1);
        
//      // 1-1-1. 상품명 부분 검색
//      System.out.println("1-3. 상품명 부분 검색 ");
//      Map<String, Object> searchKeyword3 = new HashMap<>();
//      searchKeyword3.put("productName", "갤럭시");
//
//      List<Product> result3 = productMapper.findProductsDynamic(searchKeyword3);
//      printSearchResult("'갤럭시' 포함 제품", result3);


//        // 1-2. 가격 범위로만 검색
//        System.out.println("1-2. 가격 범위로만 검색 (100만원 ~ 200만원)");
//        Map<String, Object> searchKeyword2 = new HashMap<>();
//        searchKeyword2.put("minPrice", new BigDecimal("1000000"));
//        searchKeyword2.put("maxPrice", new BigDecimal("2000000"));
//        List<Product> result2 = productMapper.findProductsDynamic(searchKeyword2);
//        printSearchResult("100만원 ~ 200만원 제품", result2);

//        // 1-3. 상품명 부분 검색 + 최소 가격
//        System.out.println("1-3. 상품명 부분 검색 + 최소 가격 ('갤럭시' + 150만원 이상)");
//        Map<String, Object> searchKeyword3 = new HashMap<>();
//        searchKeyword3.put("productName", "갤럭시");
//        searchKeyword3.put("minPrice", new BigDecimal("1500000"));
//        List<Product> result3 = productMapper.findProductsDynamic(searchKeyword3);
//        printSearchResult("'갤럭시' 포함 150만원 이상 제품", result3);
//
//        // 1-4. 복합 조건 검색
//        System.out.println("1-4. 복합 조건 검색 (애플 제품 + 140만원 이하)");
//        Map<String, Object> searchKeyword4 = new HashMap<>();
//        searchKeyword4.put("manufacturer", "Apple");
//        searchKeyword4.put("maxPrice", new BigDecimal("1400000"));
//        List<Product> result4 = productMapper.findProductsDynamic(searchKeyword4);
//        printSearchResult("애플 제품 140만원 이하", result4);

//        // 1-5. 최대 가격만으로 검색
//        System.out.println("1-5. 특정  가격 이하만으로 검색 (160만원 이하)");
//        Map<String, Object> searchKeyword5 = new HashMap<>();
//        searchKeyword5.put("maxPrice", new BigDecimal("1600000"));
//        List<Product> result5 = productMapper.findProductsDynamic(searchKeyword5);
//        printSearchResult("160만원 이하 제품", result5);

//        // 1-6. 빈 조건으로 전체 검색
//        System.out.println("1-6. 빈 조건으로 전체 검색");
//        Map<String, Object> searchKeyword6 = new HashMap<>();
//        List<Product> result6 = productMapper.findProductsDynamic(searchKeyword6);
//        printSearchResult("전체 제품", result6);
    }



    /**
     * 다중 ID 삭제 테스트
     * IN 조건을 활용한 여러 레코드 삭제
     */
     void testMultipleDelete() {
        System.out.println("[3. 다중 ID 삭제 테스트]");
        
        // 삭제할 ID 목록 (실제 존재하는 ID로 설정)
        List<Long> idsToDelete = List.of(2L, 4L);
        
        System.out.println("삭제 전 해당 상품들:");
        for (Long id : idsToDelete) {
            Product product = productMapper.findById(id);
            if (product != null) {
                System.out.println("- " + product);
            }
        }
        
        int deleteCount = productMapper.deleteByIds(idsToDelete);
        System.out.println("총 삭제된 상품 수: " + deleteCount);
        
        System.out.println("삭제 후 확인:");
        for (Long id : idsToDelete) {
            Product product = productMapper.findById(id);
            System.out.println("ID " + id + " 조회 결과 (null 기대): " + product);
        }
    }

    /**
     * 검색 결과를 보기 좋게 출력하는 헬퍼 메서드
     */
     void printSearchResult(String description, List<Product> products) {
        System.out.println(">> " + description + " 검색 결과 (" + products.size() + "건):");
        if (products.isEmpty()) {
            System.out.println("   검색 결과가 없습니다.");
        } else {
            products.forEach(product -> 
                System.out.println("   " + product.getProductName() + 
                                 " (" + product.getManufacturer() + 
                                 ", " + product.getPrice() + "원)")
            );
        }
    }

}