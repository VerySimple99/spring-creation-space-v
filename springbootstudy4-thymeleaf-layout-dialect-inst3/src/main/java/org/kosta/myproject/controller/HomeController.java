package org.kosta.myproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.kosta.myproject.model.mapper.MemberMapper;
import org.kosta.myproject.model.vo.MemberVO;
import org.kosta.myproject.model.vo.MyProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private MemberMapper memberMapper;

	@Autowired
	public HomeController(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "안녕 백리향");
		model.addAttribute("logo", "thymeleaf.png");// 동적으로 변화될 수 있는 이미지 정보
		return "index";
	}

	@RequestMapping("study1")
	public String study1(Model model) {
		// model.addAttribute("address", "도하");
		// model.addAttribute("name", "조규성");
		model.addAttribute("memberVO", new MemberVO("java", "a", "이강인", "마요르카"));
		return "step01-text-object-expression";
	}

	@RequestMapping("study2")
	public String study2(Model model) {
		model.addAttribute("memberVO", new MemberVO("spring", "a", "네이마르", "파리"));
		return "step02-form-object-expression";
	}

	@RequestMapping("study3")
	public String study3(Model model) {
		// model.addAttribute("memberVO",new MemberVO("spring","a","네이마르","파리") );
		model.addAttribute("pageNo", 9);
		model.addAttribute("keyword", "월드컵");
		model.addAttribute("orderId", "77");
		model.addAttribute("maker", "삼성");
		return "step03-link-href-querystring";
	}

	@RequestMapping("board/list")
	public String study3Result1(int pageNo, String keyword) {
		System.out.println(pageNo + " " + keyword);
		return "step03-result1-param";
	}

	@RequestMapping("order/details")
	public String study3Result2(int orderId, String maker) {
		System.out.println(orderId + " " + maker);
		return "step03-result2-param";
	}

	@RequestMapping("study4")
	public String study4(HttpSession session) {
		session.setAttribute("mvo", new MemberVO("lee", "a", "이강인", "마요르카"));
		return "step04-session-param";
	}

	@RequestMapping("study4-test")
	public String study4Test(String id, String name) {
		System.out.println(id + " " + name);
		return "step04-session-param-result";
	}

	@RequestMapping("study5")
	public String study5(Model model) {
		model.addAttribute("product", new MyProductVO(1, "테라", "하이트진로", 1540, true));
		model.addAttribute("size", "L"); // L M S
		return "step05-if-unless-switch";
	}

	@RequestMapping("study6")
	public String study6(Model model) {
		// db 즉 mybatis 를 통해 회원 리스트를 반환 받았다고 가정
		ArrayList<MemberVO> list = new ArrayList<>();
		list.add(new MemberVO("java", "a", "김진수", "전주"));
		list.add(new MemberVO("spring", "a", "조규성", "도르트문트"));
		list.add(new MemberVO("ajax", "a", "이강인", "마요르카"));
		model.addAttribute("memberList", list);
		// mybatis 연동해 model 에 memberMapList를 할당
		model.addAttribute("memberMapList", memberMapper.findAllMemberMapList());
		return "step06-loop-each";
	}

	@RequestMapping("study7")
	public String study7(Model model) {
		ArrayList<MyProductVO> productList = new ArrayList<MyProductVO>();
		productList.add(new MyProductVO(1, "테라", "하이트진로", 1410, true));
		productList.add(new MyProductVO(2, "카스", "오비", 1410, false));
		productList.add(new MyProductVO(3, "참이슬", "하이트진로", 1300, false));
		productList.add(new MyProductVO(4, "처음처럼", "롯데", 1500, true));
		model.addAttribute("productList", productList);
		return "step07-loop-each-lists";
	}

	@RequestMapping("study8")
	public String study8(Model model) {
		model.addAttribute("startPageNumber", "1");
		model.addAttribute("endPageNumber", "4");
		return "step08-loop-each-numbers-pagination";
	}

	@RequestMapping("study9")
	public String study9() {
		return "step09-javascript";
	}

	@RequestMapping("study10")
	public String study10() {
		return "step10-ajax";
	}

	// Thymeleaf layout 테스트
	@RequestMapping("study13")
	public String study13() {
		return "step13-main1";
	}

	// Thymeleaf layout 테스트
	@RequestMapping("study13-2")
	public String study13Two() {
		return "step13-main2";
	}

	// Thymeleaf layout 테스트
	@RequestMapping("study14")
	public String study14() {
		return "step14-main1";
	}

	// Thymeleaf layout 테스트
	@RequestMapping("study14-2")
	public String study14Two() {
		return "step14-main2";
	}
}
