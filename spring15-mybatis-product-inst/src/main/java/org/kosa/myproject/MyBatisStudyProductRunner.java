package org.kosa.myproject;

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
//        System.out.println("\n[1. 상품 등록 테스트]");
  //    Product newProduct = new Product(null, "갤럭시 S25", "삼성", new BigDecimal("1500000"), null);
//        int insertCount = productMapper.insert(newProduct);
//        System.out.println("등록 성공 여부 (1이면 성공): " + insertCount);
//        System.out.println("새로 등록된 상품 ID: " + newProduct.getProductId()); // useGeneratedKeys로 ID가 주입됨

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
        System.out.println("\n[7. 상품 삭제 테스트]");
        int deleteCount = productMapper.delete(6L);
        System.out.println("삭제 성공 여부 (1이면 성공): " + deleteCount);
        Product deletedProduct = productMapper.findById(6L);
        System.out.println("삭제 후 조회된 상품 (null): " + deletedProduct);

        System.out.println("\n-------------------------------------");
        System.out.println("          MyBatis Mapper 테스트 종료         ");
        System.out.println("-------------------------------------");
 /*       */
    }
}