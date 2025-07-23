package org.kosa.myproject.controller;

import java.util.List;
import java.util.Map;

import org.kosa.myproject.domain.Member;
import org.kosa.myproject.domain.MyProduct;
import org.kosa.myproject.mapper.MemberMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	private final MemberMapper memberMapper;

	public HomeController(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "안녕 백리향");
		model.addAttribute("logo", "thymeleaf.png");// 동적으로 변화될 수 있는 이미지 정보
		return "index";
	}

	@GetMapping("/study1")
	public String study1(Model model) {
		// model.addAttribute("address", "도하");
		// model.addAttribute("name", "조규성");
		model.addAttribute("member", new Member("java", "a", "이강인", "마요르카"));
		return "step01-text-object-expression";
	}

	@GetMapping("/study2")
	public String study2(Model model) {
		model.addAttribute("member", new Member("spring", "a", "네이마르", "파리"));
		return "step02-form-object-expression";
	}

	@GetMapping("/study3")
	public String study3(Model model) {
		// model.addAttribute("memberVO",new MemberVO("spring","a","네이마르","파리") );
		model.addAttribute("pageNo", 9);
		model.addAttribute("keyword", "월드컵");
		model.addAttribute("orderId", "77");
		model.addAttribute("maker", "삼성");
		return "step03-link-href-querystring";
	}

	@GetMapping("/board/list")
	public String study3Result1(int pageNo, String keyword) {
		System.out.println(pageNo + " " + keyword);
		return "step03-result1-param";
	}

	@GetMapping("/order/details")
	public String study3Result2(int orderId, String maker) {
		System.out.println(orderId + " " + maker);
		return "step03-result2-param";
	}

	@GetMapping("/study4")
	public String study4(HttpSession session) {
		session.setAttribute("mvo", new Member("lee", "a", "이강인", "마요르카"));
		return "step04-session-param";
	}

	@GetMapping("/study4-test")
	public String study4Test(String id, String name) {
		System.out.println(id + " " + name);
		return "step04-session-param-result";
	}

	@GetMapping("/study5")
	public String study5(Model model) {
		model.addAttribute("product", new MyProduct(1, "테라", "하이트진로", 1540, true));
		model.addAttribute("size", "L"); // L M S
		return "step05-if-unless-switch";
	}

	@GetMapping("/study6")
	public String study6(Model model) {
		// db 즉 mybatis 를 통해 회원 리스트를 반환 받았다고 가정	
		// List.of를 사용하여 불변(Immutable) 리스트 생성
        List<Member> list = List.of(
            new Member("java", "a", "김진수", "전주"),
            new Member("spring", "a", "조규성", "도르트문트"),
            new Member("ajax", "a", "이강인", "마요르카")
        );
		model.addAttribute("memberList", list);
		// mybatis 연동해 model 에 memberMapList를 할당
		List<Map<String,String>> mapList=memberMapper.findAllMemberMapList();
		System.out.println(mapList);
		model.addAttribute("memberMapList", mapList);
		return "step06-loop-each";
	}

	@GetMapping("/study7")
	public String study7(Model model) {
		List<MyProduct> productList = List.of(new MyProduct(1, "테라", "하이트진로", 1410, true),
				new MyProduct(2, "카스", "오비", 1410, false),
				new MyProduct(3, "참이슬", "하이트진로", 1300, false),
				new MyProduct(4, "처음처럼", "롯데", 1500, true)
		);
		model.addAttribute("productList", productList);
		return "step07-loop-each-lists";
	}

	@GetMapping("/study8")
	public String study8(Model model) {
		model.addAttribute("startPageNumber", "1");
		model.addAttribute("endPageNumber", "4");
		return "step08-loop-each-numbers-pagination";
	}

	@GetMapping("/study9")
	public String study9() {
		return "step09-javascript";
	}

	// Thymeleaf layout 테스트
	@GetMapping("/study10")
	public String study10() {
		return "step10-main1";
	}

	// Thymeleaf layout 테스트
	@GetMapping("/study10-2")
	public String study10Two() {
		return "step10-main2";
	}

	// Thymeleaf layout 테스트
	@GetMapping("/study11")
	public String study14() {
		return "step11-main1";
	}

	// Thymeleaf layout 테스트
	@GetMapping("/study11-2")
	public String study14Two() {
		return "step11-main2";
	}
}
