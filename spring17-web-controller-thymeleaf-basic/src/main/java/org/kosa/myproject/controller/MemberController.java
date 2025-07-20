package org.kosa.myproject.controller;

import org.kosa.myproject.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * === Spring MVC Controller 핵심 개념 학습 ===
 * 
 * Controller의 역할:
 * 1. 클라이언트의 HTTP 요청을 받음
 * 2. 비즈니스 로직 처리 (또는 Service 계층에 위임)
 * 3. 모델 데이터를 준비
 * 4. 적절한 뷰(View)로 응답
 * 
 * 이것이 바로 MVC 패턴의 핵심입니다!
 */

// @Controller: 이 클래스가 Spring MVC의 컨트롤러임을 선언
// Spring이 이 어노테이션을 보고 웹 요청을 처리할 수 있는 Bean으로 등록
@Controller

// @RequestMapping: 클래스 레벨에서 공통 URL 경로를 정의
// 모든 메서드의 URL 앞에 "/member"가 자동으로 붙음
// 장점: 코드 중복 제거, URL 구조 체계화, 유지보수성 향상
@RequestMapping("/member")
public class MemberController {

    /**
     * === 기본적인 뷰 반환 패턴 ===
     * 가장 단순한 형태의 컨트롤러 메서드
     */
    @GetMapping("/member-test")  // GET /member/member-test 요청 처리
    public String memberTest() {
        // 반환값: 뷰 이름 (타임리프 템플릿 파일명)
        // ViewResolver가 "/member/member-test"를 해석하여
        // "src/main/resources/templates/member/member-test.html" 파일을 찾아 렌더링
        return "/member/member-test";
        
        // 주의: 앞의 "/"는 생략 가능 ("member/member-test"와 동일)
    }

    /**
     * === 파라미터 처리와 모델 데이터 전달 ===
     * Spring MVC와 타임리프의 핵심 연동 패턴
     */
    @GetMapping("/find-by-id")  // URL: /member/find-by-id?memberId=값
    public String findMemberById(
            // @RequestParam: HTTP 요청 파라미터를 메서드 매개변수로 바인딩
            // required=true가 기본값 (파라미터 필수)
            // required=false로 설정하면 선택적 파라미터
            @RequestParam String memberId, 
            
            // Model: 뷰에 전달할 데이터를 담는 객체
            // Controller와 View 사이의 데이터 전달 통로
            Model model
    ) {
        /*
         * === 비즈니스 로직 영역 ===
         * 실무에서는 Service 계층에서 처리:
         * Member member = memberService.findById(memberId);
         */
        
        // 테스트용 더미 데이터 생성
        Member member = new Member(memberId, "a", "이강인", "파리");
        
        /*
         * === 모델 데이터 설정 (Controller → View 데이터 전달) ===
         * model.addAttribute(키, 값):
         * - 키: 타임리프에서 ${키} 형태로 접근할 이름
         * - 값: 실제 전달할 데이터 객체
         * 
         * 타임리프에서 ${member}로 접근 가능
         * 예: ${member.name}, ${member.city} 등
         */
        model.addAttribute("member", member);
        
        System.out.println("회원 조회 요청 처리: " + memberId);
        
        /*
         * === 뷰 이름 반환 (Forward 방식) ===
         * "member/findbyid_result"는 뷰 이름
         * 
         * ViewResolver가 다음과 같이 해석:
         * prefix: "classpath:/templates/"
         * suffix: ".html"
         * 결과: "classpath:/templates/member/findbyid_result.html"
         * 
         * 이것이 Forward 방식!
         * - URL 변경 없음
         * - 모델 데이터 유지
         * - 서버 내부에서 뷰로 직접 이동
         */
        return "member/findbyid_result";
    }

    /**
     * === 조건부 처리와 다양한 뷰 반환 ===
     * 비즈니스 로직에 따른 분기 처리 패턴
     */
    @GetMapping("/find-by-id2")
    public String findMemberById2(@RequestParam String memberId, Model model) {
        
        /*
         * === 조건부 로직과 뷰 분기 ===
         * 실무에서 자주 사용되는 패턴:
         * - 성공/실패에 따른 다른 뷰 반환
         * - 사용자 권한에 따른 뷰 분기
         * - 데이터 존재 여부에 따른 처리
         */
        if ("java".equals(memberId)) {
            // 성공 케이스: 데이터 설정 후 성공 뷰로 이동
            model.addAttribute("member", "이강인 파리");
            return "member/findbyid_ok";    // 성공 페이지
        } else {
            // 실패 케이스: 실패 뷰로 이동 (데이터 없음)
            return "member/findbyid_fail";  // 실패 페이지
        }
        
        /*
         * 주의사항:
         * - 각 분기마다 적절한 모델 데이터 설정 필요
         * - 뷰 이름은 명확하고 의미있게 작성
         * - 예외 상황도 고려한 뷰 준비
         */
    }

    /**
     * === 다중 파라미터 처리와 데이터 가공 ===
     * 여러 파라미터를 받아서 로직 처리 후 결과 전달
     */
    @GetMapping("/param-test")  // URL: /member/param-test?nick=값&age=값
    public String paramTest(
            @RequestParam String nick,  // 문자열 파라미터
            @RequestParam int age,      // 숫자 파라미터 (자동 변환)
            Model model
    ) {
        /*
         * === 비즈니스 로직 처리 ===
         * 받은 파라미터를 가공하여 새로운 데이터 생성
         */
        String type = null;
        if (age > 18) {
            type = "성인";
        } else {
            type = "미성년";
        }
        
        /*
         * === 가공된 데이터를 모델에 추가 ===
         * 원본 파라미터뿐만 아니라 가공된 결과도 뷰에 전달 가능
         */
        model.addAttribute("type", type);
        // 필요시 원본 파라미터도 전달 가능:
        // model.addAttribute("nick", nick);
        // model.addAttribute("age", age);
        
        return "member/param_result";
    }

    /**
     * === POST 요청 처리와 객체 바인딩 ===
     * 폼 데이터를 객체로 자동 바인딩하는 Spring MVC의 강력한 기능
     */
    @PostMapping("/register")  // POST /member/register 요청 처리
    public String registerMember(
            // Member member: 요청 파라미터를 Member 객체로 자동 바인딩
            // Spring이 HTTP 요청의 파라미터 이름과 Member 클래스의 필드를 매칭
            // 예: name=홍길동&email=hong@test.com → Member 객체의 name, email 필드에 자동 설정
            Member member
    ) {
        /*
         * === 객체 바인딩의 장점 ===
         * 1. 코드 간소화: 각 필드별 @RequestParam 불필요
         * 2. 타입 안전성: 컴파일 타임에 필드 확인 가능
         * 3. 유지보수성: Member 클래스 변경 시 자동 반영
         * 
         * 바인딩 조건:
         * - 파라미터 이름과 필드 이름 일치
         * - 기본 생성자 필요
         * - Setter 메서드 필요 (또는 필드 직접 접근 허용)
         */
        
        System.out.println("등록된 회원: " + member);
        
        /*
         * === Redirect 패턴 ===
         * "redirect:" 접두사 사용
         * 
         * redirect vs forward 차이점:
         * 
         * 1. Redirect (PRG 패턴 - Post-Redirect-Get):
         *    - 브라우저에게 새로운 URL로 이동하라고 지시 (HTTP 302)
         *    - URL이 변경됨
         *    - 새로운 HTTP 요청 발생
         *    - 모델 데이터 소실 (새 요청이므로)
         *    - 브라우저 새로고침 시 중복 제출 방지
         *    - 주로 POST → GET 패턴에서 사용
         * 
         * 2. Forward:
         *    - 서버 내부에서 뷰로 직접 이동
         *    - URL 변경 없음
         *    - 같은 요청 내에서 처리
         *    - 모델 데이터 유지
         *    - 주로 데이터 조회 후 뷰 표시에 사용
         */
        return "redirect:/member/register-result";
    }

    /**
     * === Redirect 대상 페이지 ===
     * POST 처리 후 최종 결과 페이지
     */
    @GetMapping("/register-result")
    public String registerResult() {
        /*
         * === Redirect 후 처리 ===
         * - 새로운 GET 요청으로 처리됨
         * - 이전 POST의 모델 데이터는 없음
         * - 필요한 데이터는 다시 조회하거나 세션/플래시 속성 활용
         * 
         * 개선 방안:
         * 1. RedirectAttributes 사용하여 일회성 데이터 전달
         * 2. 세션에 임시 데이터 저장
         * 3. URL 파라미터로 필요한 정보 전달
         */
        return "member/register_result";
    }
}

/*
 * === Forward vs Redirect 사용 기준 정리 ===
 * 
 * ✅ Forward 사용 시점:
 * 1. 데이터 조회 후 결과 표시
 * 2. 폼 검증 실패 시 입력 데이터 유지하며 폼 재표시
 * 3. 에러 페이지 표시
 * 4. 단순 페이지 이동
 * 예: 회원 목록 조회, 상세 정보 표시, 검색 결과 표시
 * 
 * ✅ Redirect 사용 시점:
 * 1. 데이터 생성/수정/삭제 후 (PRG 패턴)
 * 2. 로그인 성공 후 메인 페이지로 이동
 * 3. 권한 없는 페이지 접근 시 로그인 페이지로 이동
 * 4. 중복 제출 방지가 필요한 경우
 * 예: 회원 가입 후, 글 작성 후, 결제 완료 후
 * 
 * === 실무 권장 패턴 ===
 * 
 * 1. 조회(GET) → Forward
 *    @GetMapping("/list")
 *    public String list(Model model) {
 *        model.addAttribute("members", memberService.findAll());
 *        return "member/list";  // Forward
 *    }
 * 
 * 2. 등록/수정/삭제(POST) → Redirect
 *    @PostMapping("/save")
 *    public String save(Member member) {
 *        memberService.save(member);
 *        return "redirect:/member/list";  // Redirect
 *    }
 * 
 * 3. 폼 검증 실패 → Forward (입력 데이터 유지)
 *    @PostMapping("/save")
 *    public String save(@Valid Member member, BindingResult result, Model model) {
 *        if (result.hasErrors()) {
 *            return "member/form";  // Forward (검증 오류 정보 유지)
 *        }
 *        memberService.save(member);
 *        return "redirect:/member/list";  // Redirect
 *    }
 * 
 * === 타임리프와의 연동 핵심 ===
 * 
 * 1. 모델 데이터 전달:
 *    Controller: model.addAttribute("member", member);
 *    Thymeleaf: <p th:text="${member.name}"></p>
 * 
 * 2. 폼 바인딩:
 *    Controller: public String save(Member member)
 *    Thymeleaf: <form th:object="${member}">
 *                 <input th:field="*{name}">
 *               </form>
 * 
 * 3. URL 생성:
 *    Controller: @GetMapping("/detail/{id}")
 *    Thymeleaf: <a th:href="@{/member/detail/{id}(id=${member.id})}">
 * 
 * 이러한 패턴들을 익히면 Spring MVC와 타임리프를 자유자재로 활용할 수 있습니다!
 */