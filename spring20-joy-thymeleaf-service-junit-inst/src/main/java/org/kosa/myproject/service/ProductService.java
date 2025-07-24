package org.kosa.myproject.service;

import java.util.List;

import org.kosa.myproject.domain.Product;
import org.kosa.myproject.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 * 상품 관련 비즈니스 로직을 처리하는 서비스 클래스
 * - Controller와 Mapper 사이의 중간 계층 역할
 * - 트랜잭션 관리 및 비즈니스 규칙 적용
 * - 1단계: 기본 CRUD 기능, 2단계: 트랜잭션 기능 추가 예정
 */
@Service
public class ProductService {
    
    private final ProductMapper productMapper;
    
    /**
     * 생성자 주입을 통한 의존성 주입
     * - @Autowired 생략 가능 (Spring 4.3+)
     * - final 키워드로 불변성 보장
     */
 //   @Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    
    /**
     * 전체 상품 목록 조회
     * - 컨트롤러에서 화면 표시용 데이터 요청 시 사용
     * - 현재는 단순 조회만 수행, 향후 페이징/검색 기능 확장 가능
     * 
     * @return 상품 목록 (List<Product>)
     */
    public List<Product> getAllProducts() {
        try {
            List<Product> products = productMapper.selectAllProducts();
            System.out.println("조회된 상품 개수: " + products.size()); // 로깅 (개발용)
            return products;
        } catch (Exception e) {
            System.err.println("상품 목록 조회 실패: " + e.getMessage());
            throw new RuntimeException("상품 목록을 불러올 수 없습니다.", e);
        }
    }
    
    /**
     * 특정 상품 상세 조회
     * - 상품번호로 특정 상품의 상세 정보 조회
     * - 상품 수정/삭제 시 기존 데이터 확인용으로 사용
     * 
     * @param productId 조회할 상품번호
     * @return 상품 정보 (Product), 없으면 null
     */
    public Product getProductById(Long productId) {
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("유효하지 않은 상품번호입니다.");
        }
        
        try {
            Product product = productMapper.selectProductById(productId);
            if (product == null) {
                System.out.println("상품번호 " + productId + "에 해당하는 상품이 없습니다.");
            }
            return product;
        } catch (Exception e) {
            System.err.println("상품 조회 실패 (ID: " + productId + "): " + e.getMessage());
            throw new RuntimeException("상품 정보를 불러올 수 없습니다.", e);
        }
    }
    
    /**
     * 새로운 상품 등록
     * - PRG 패턴의 핵심: POST 요청 처리 후 Redirect를 위한 메서드
     * - 비즈니스 규칙: 상품명 중복 체크, 가격 유효성 검증 등
     * 
     * @param product 등록할 상품 정보
     * @return 등록된 상품 정보 (생성된 ID 포함)
     */
    public Product registerProduct(Product product) {
        // 1. 입력 데이터 검증
        validateProductData(product);
        
        try {
            // 2. 데이터베이스에 상품 등록
            int result = productMapper.insertProduct(product);
            
            // 3. 등록 결과 확인
            if (result == 1) {
                System.out.println("상품 등록 성공 - ID: " + product.getProductId() + 
                                 ", 상품명: " + product.getProductName());
                return product; // Auto-generated ID가 포함된 상태로 반환
            } else {
                throw new RuntimeException("상품 등록에 실패했습니다.");
            }
            
        } catch (Exception e) {
            System.err.println("상품 등록 실패: " + e.getMessage());
            throw new RuntimeException("상품 등록 중 오류가 발생했습니다.", e);
        }
    }
    
    /**
     * 상품 정보 수정
     * - 기존 상품의 정보를 업데이트
     * - 재고 수량은 별도 기능에서 관리 (트랜잭션 학습용)
     * 
     * @param product 수정할 상품 정보
     * @return 수정 성공 여부
     */
    public boolean updateProduct(Product product) {
        // 1. 상품 존재 여부 확인
        Product existingProduct = getProductById(product.getProductId());
        if (existingProduct == null) {
            throw new IllegalArgumentException("수정하려는 상품이 존재하지 않습니다.");
        }
        
        // 2. 입력 데이터 검증
        validateProductData(product);
        
        try {
            int result = productMapper.updateProduct(product);
            boolean success = (result == 1);
            
            if (success) {
                System.out.println("상품 수정 성공 - ID: " + product.getProductId());
            } else {
                System.out.println("상품 수정 실패 - ID: " + product.getProductId());
            }
            
            return success;
            
        } catch (Exception e) {
            System.err.println("상품 수정 실패: " + e.getMessage());
            throw new RuntimeException("상품 수정 중 오류가 발생했습니다.", e);
        }
    }
    
    /**
     * 상품 삭제
     * - 물리적 삭제 (학습 목적)
     * - 실무에서는 논리적 삭제(deleted_at) 권장
     * 
     * @param productId 삭제할 상품번호
     * @return 삭제 성공 여부
     */
    public boolean deleteProduct(Long productId) {
        // 1. 상품 존재 여부 확인
        Product existingProduct = getProductById(productId);
        if (existingProduct == null) {
            throw new IllegalArgumentException("삭제하려는 상품이 존재하지 않습니다.");
        }
        
        try {
            int result = productMapper.deleteProduct(productId);
            boolean success = (result == 1);
            
            if (success) {
                System.out.println("상품 삭제 성공 - ID: " + productId);
            } else {
                System.out.println("상품 삭제 실패 - ID: " + productId);
            }
            
            return success;
            
        } catch (Exception e) {
            System.err.println("상품 삭제 실패: " + e.getMessage());
            throw new RuntimeException("상품 삭제 중 오류가 발생했습니다.", e);
        }
    }
    
    /**
     * 상품 데이터 유효성 검증
     * - 비즈니스 규칙에 따른 데이터 검증
     * - Bean Validation 대신 수동 검증 (학습 목적)
     * 
     * @param product 검증할 상품 정보
     */
    private void validateProductData(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("상품 정보가 없습니다.");
        }
        
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 필수 입력 항목입니다.");
        }
        
        if (product.getProductName().length() > 100) {
            throw new IllegalArgumentException("상품명은 100자를 초과할 수 없습니다.");
        }
        
        if (product.getMaker() == null || product.getMaker().trim().isEmpty()) {
            throw new IllegalArgumentException("제조사는 필수 입력 항목입니다.");
        }
        
        if (product.getMaker().length() > 50) {
            throw new IllegalArgumentException("제조사명은 50자를 초과할 수 없습니다.");
        }
        
        if (product.getPrice() == null || product.getPrice().doubleValue() <= 0) {
            throw new IllegalArgumentException("가격은 0보다 큰 값이어야 합니다.");
        }
        
        if (product.getPrice().doubleValue() > 99999999.99) {
            throw new IllegalArgumentException("가격이 너무 큽니다.");
        }
    }
}