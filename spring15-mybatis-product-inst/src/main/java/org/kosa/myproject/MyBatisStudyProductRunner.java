package org.kosa.myproject;

import java.math.BigDecimal;

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
        System.out.println("          MyBatis Mapper 테스트 시작         ");
        System.out.println("-------------------------------------");

        // 1. 새로운 상품 등록 테스트
        System.out.println("\n[1. 상품 등록 테스트]");
      Product newProduct = new Product(null, "갤럭시 S25", "삼성", new BigDecimal("1500000"), null);
        int insertCount = productMapper.insert(newProduct);
        System.out.println("등록 성공 여부 (1이면 성공): " + insertCount);
        System.out.println("새로 등록된 상품 ID: " + newProduct.getProductId()); // useGeneratedKeys로 ID가 주입됨

        // 2. 모든 상품 조회 테스트
//        System.out.println("\n[2. 모든 상품 조회 테스트]");
//        List<Product> allProducts = productMapper.findAll();
//        allProducts.forEach(System.out::println);
//        System.out.println("총 상품 수: " + allProducts.size());

        // 3. ID로 상품 조회 테스트
//        System.out.println("\n[3. ID로 상품 조회 테스트]");
//        Long targetId = 1L;
//        Product foundProduct = productMapper.findById(targetId);
//       System.out.println("조회된 상품: " + foundProduct);

        // 4. 상품 정보 수정 테스트
//        System.out.println("\n[4. 상품 수정 테스트]");
//        if (foundProduct != null) {
//            foundProduct.setPrice(new BigDecimal("1450000")); // 가격 수정
//            foundProduct.setProductName("갤럭시 S25 Ultra"); // 이름 수정
//            int updateCount = productMapper.update(foundProduct);
//            System.out.println("수정 성공 여부 (1이면 성공): " + updateCount);
//            Product updatedProduct = productMapper.findById(targetId);
//            System.out.println("수정된 상품: " + updatedProduct);
//        }

        // 5. 제조사별 상품 조회 테스트
//        System.out.println("\n[5. 제조사별 상품 조회 테스트]");
//        List<Product> samsungProducts = productMapper.findByManufacturer("삼성");
//        samsungProducts.forEach(System.out::println);
//        System.out.println("삼성 제품 수: " + samsungProducts.size());

        // 6. 가격 범위별 상품 조회 테스트
//        System.out.println("\n[6. 가격 범위별 상품 조회 테스트]");
//        List<Product> priceRangeProducts = productMapper.findByPriceRange(new BigDecimal("1000000"), new BigDecimal("2000000"));
//        priceRangeProducts.forEach(System.out::println);
//        System.out.println("100만원 ~ 200만원 상품 수: " + priceRangeProducts.size());

        // 7. 상품 삭제 테스트
//        System.out.println("\n[7. 상품 삭제 테스트]");
//        int deleteCount = productMapper.delete(6L);
//        System.out.println("삭제 성공 여부 (1이면 성공): " + deleteCount);
//        Product deletedProduct = productMapper.findById(6L);
//        System.out.println("삭제 후 조회된 상품 (null): " + deletedProduct);
//
//        System.out.println("\n-------------------------------------");
//        System.out.println("          MyBatis Mapper 테스트 종료         ");
//        System.out.println("-------------------------------------");
 /*       */
        System.out.println("-------------------------------------");
        System.out.println("       MyBatis Dynamic Query 테스트 시작      ");
        System.out.println("-------------------------------------");

        // 테스트용 상품 데이터 먼저 삽입 (필요하다면)
        // 이 부분은 이미 DB에 데이터가 있다고 가정하고 생략하거나,
        // 기존의 insert 코드를 주석 해제하여 사용하시면 됩니다.

        // 1. 동적 검색 테스트
//        System.out.println("\n[1. 동적 검색 테스트]");
//        // 1-1. 제조사로만 검색
//        Product searchCriteria1 = new Product(null, null, "삼성", null, null);
//        List<Product> result1 = productMapper.findProductsDynamic(searchCriteria1);
//        System.out.println("삼성 제품 검색 결과:");
//        result1.forEach(System.out::println);
//
//        // 1-2. 가격 범위로만 검색
//        Product searchCriteria2 = new Product(null, null, null, new BigDecimal("1000000"), new BigDecimal("2000000"));
//        List<Product> result2 = productMapper.findProductsDynamic(searchCriteria2);
//        System.out.println("\n100만원 ~ 200만원 제품 검색 결과:");
//        result2.forEach(System.out::println);
//        
//        // 1-3. 상품명과 가격 범위로 검색
//        Product searchCriteria3 = new Product(null, "그램", null, new BigDecimal("1500000"), null);
//        List<Product> result3 = productMapper.findProductsDynamic(searchCriteria3);
//        System.out.println("\n'그램'이 포함된 150만원 이상 제품 검색 결과:");
//        result3.forEach(System.out::println);
//        
//
//        // 2. 동적 업데이트 테스트
//        System.out.println("\n[2. 동적 업데이트 테스트]");
//        // product_id 1번 상품의 가격만 업데이트
//        Product updateCriteria1 = new Product(1L, null, null, new BigDecimal("2900000"), null);
//        int updateCount = productMapper.updateDynamic(updateCriteria1);
//        System.out.println("업데이트 성공 여부 (1이면 성공): " + updateCount);
//        Product updatedProduct = productMapper.findById(1L);
//        System.out.println("업데이트 후 상품 정보: " + updatedProduct);
//
//
//        // 3. 여러 ID를 이용해 삭제 테스트
//        System.out.println("\n[3. 여러 ID를 이용해 삭제 테스트]");
//        // 삭제할 ID 목록 (DB에 존재하는 ID로 바꿔주세요)
//        List<Long> idsToDelete = Arrays.asList(2L, 4L, 6L);
//        int deleteCount = productMapper.deleteByIds(idsToDelete);
//        System.out.println("총 삭제된 상품 수: " + deleteCount);
//        System.out.println("삭제 후 2번 상품 조회 (null 기대): " + productMapper.findById(2L));


        System.out.println("\n-------------------------------------");
        System.out.println("        MyBatis Dynamic Query 테스트 종료        ");
        System.out.println("-------------------------------------");
    }
}