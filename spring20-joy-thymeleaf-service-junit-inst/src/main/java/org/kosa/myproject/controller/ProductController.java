package org.kosa.myproject.controller;

import java.util.List;

import org.kosa.myproject.domain.Product;
import org.kosa.myproject.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 상품 관리 웹 컨트롤러
 * - PRG (Post-Redirect-Get) 패턴의 핵심 구현
 * - 사용자 요청을 받아 적절한 서비스 호출 후 응답 처리
 * - 1단계: 기본 CRUD + PRG 패턴, 2단계: 트랜잭션 기능 추가 예정
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;
    
    /**
     * 생성자 주입을 통한 의존성 주입
     * - 컨트롤러는 서비스에만 의존, 직접적인 데이터 접근 금지
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    /**
     * 상품 목록 조회 (GET 요청)
     * - PRG 패턴의 'G(Get)' 역할
     * - 상품 등록 후 리다이렉트되는 최종 도착지
     * - URL: GET /products
     * 
     * @param model Thymeleaf 템플릿에 데이터 전달용
     * @return 상품 목록 템플릿 경로
     */
    @GetMapping
    public String productList(Model model) {
        try {
            // 1. 서비스에서 전체 상품 목록 조회
            List<Product> products = productService.getAllProducts();
            
            // 2. 모델에 데이터 추가 (Thymeleaf에서 사용)
            model.addAttribute("products", products);
            model.addAttribute("totalCount", products.size());
            
            // 3. 성공/실패 메시지 확인 (RedirectAttributes에서 전달)
            // Flash attribute는 자동으로 model에 추가됨
            
            System.out.println("상품 목록 페이지 요청 - 조회된 상품 수: " + products.size());
            
            return "product/list"; // templates/product/list.html
            
        } catch (Exception e) {
            System.err.println("상품 목록 조회 중 오류: " + e.getMessage());
            model.addAttribute("errorMessage", "상품 목록을 불러오는 중 오류가 발생했습니다.");
            return "product/list";
        }
    }
    
    /**
     * 상품 등록 폼 표시 (GET 요청)
     * - 새로운 상품을 등록하기 위한 폼 페이지
     * - URL: GET /products/register
     * 
     * @param model 빈 Product 객체를 폼에 바인딩
     * @return 상품 등록 폼 템플릿 경로
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        // 1. 빈 Product 객체를 모델에 추가 (Thymeleaf 폼 바인딩용)
        model.addAttribute("product", new Product());
        
        System.out.println("상품 등록 폼 페이지 요청");
        
        return "product/register"; // templates/product/register.html
    }
    
    /**
     * 상품 등록 처리 (POST 요청) - PRG 패턴의 핵심
     * - PRG 패턴의 'P(Post)' 역할
     * - 폼 데이터를 받아 처리한 후 'R(Redirect)' 수행
     * - URL: POST /products/register
     * 
     * @param product 폼에서 전송된 상품 정보 (자동 바인딩)
     * @param redirectAttributes Flash Attribute로 메시지 전달
     * @return 리다이렉트 URL (PRG 패턴의 'R')
     */
    @PostMapping("/register")
    public String registerProduct(@ModelAttribute Product product, 
                                RedirectAttributes redirectAttributes) {
        try {
            // 1. 서비스를 통한 상품 등록 처리
            Product registeredProduct = productService.registerProduct(product);
            
            // 2. 성공 메시지를 Flash Attribute로 전달
            // - Flash Attribute: 리다이렉트 후 한 번만 사용되는 임시 데이터
            // - PRG 패턴에서 POST 처리 결과를 GET 페이지에 전달하는 방법
            redirectAttributes.addFlashAttribute("successMessage", 
                "상품이 성공적으로 등록되었습니다. (상품번호: " + registeredProduct.getProductId() + ")");
            
            System.out.println("상품 등록 성공 - 상품번호: " + registeredProduct.getProductId() + 
                             ", 상품명: " + registeredProduct.getProductName());
            
            // 3. PRG 패턴의 'R(Redirect)' - 상품 목록으로 리다이렉트
            // - 302 리다이렉트 응답으로 브라우저가 새로운 GET 요청 수행
            // - 새로고침 시 중복 등록 방지 효과
            return "redirect:/products";
            
        } catch (IllegalArgumentException e) {
            // 4-1. 유효성 검증 실패 시 (비즈니스 규칙 위반)
            System.err.println("상품 등록 유효성 검증 실패: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("product", product); // 입력값 유지
            
            // 등록 폼으로 리다이렉트 (입력 데이터 보존)
            return "redirect:/products/register";
            
        } catch (Exception e) {
            // 4-2. 시스템 오류 시
            System.err.println("상품 등록 시스템 오류: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", 
                "상품 등록 중 시스템 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
            redirectAttributes.addFlashAttribute("product", product);
            
            return "redirect:/products/register";
        }
    }
    
    /**
     * 특정 상품 상세 조회 (GET 요청)
     * - 상품번호를 Path Variable로 받아 상세 정보 표시
     * - URL: GET /products/{productId}
     * 
     * @param productId 조회할 상품번호 (URL 경로에서 추출)
     * @param model 상품 정보를 템플릿에 전달
     * @return 상품 상세 템플릿 경로
     */
    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Long productId, Model model) {
        try {
            // 1. 서비스를 통한 상품 상세 정보 조회
            Product product = productService.getProductById(productId);
            
            if (product == null) {
                // 2-1. 상품이 존재하지 않는 경우
                model.addAttribute("errorMessage", "요청하신 상품을 찾을 수 없습니다.");
                return "product/list"; // 목록으로 돌아가기
            }
            
            // 2-2. 상품 정보를 모델에 추가
            model.addAttribute("product", product);
            
            System.out.println("상품 상세 조회 - 상품번호: " + productId + 
                             ", 상품명: " + product.getProductName());
            
            return "product/detail"; // templates/product/detail.html
            
        } catch (Exception e) {
            System.err.println("상품 상세 조회 오류: " + e.getMessage());
            model.addAttribute("errorMessage", "상품 정보를 불러오는 중 오류가 발생했습니다.");
            return "product/list";
        }
    }
    
    /**
     * 루트 경로 접근 시 상품 목록으로 리다이렉트
     * - URL: GET /
     * - 사용자 편의를 위한 기본 페이지 설정
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }
    
    // ========== 향후 확장 기능 (주석 처리) ==========
    // 2단계에서 트랜잭션 학습 시 활성화 예정
    
    /*
    // 상품 수정 폼
    @GetMapping("/{productId}/edit")
    public String editForm(@PathVariable Long productId, Model model) {
        // 구현 예정
    }
    
    // 상품 수정 처리 (PRG 패턴 적용)
    @PostMapping("/{productId}/edit")
    public String updateProduct(@PathVariable Long productId, 
                               @ModelAttribute Product product,
                               RedirectAttributes redirectAttributes) {
        // 구현 예정
    }
    
    // 상품 삭제 처리 (PRG 패턴 적용)
    @PostMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId,
                               RedirectAttributes redirectAttributes) {
        // 구현 예정
    }
    */
}