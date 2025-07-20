package org.kosa.myproject.controller;


import org.kosa.myproject.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member") // 공통된 URL 경로를 클래스 레벨에 설정하여 코드 중복을 줄입니다.
public class MemberController2 {
	@GetMapping("/member-test")
	public String memberTest(){
		return "/member/member-test";
	}
    // findMemberById.do -> /member/find-by-id 로 변경
    @GetMapping("/find-by-id") 
    // @RequestParam을 사용하여 매개변수를 명확하게 지정하는 것을 권장합니다.
    public String findMemberById(@RequestParam String memberId, Model model) {
         model.addAttribute("member", new Member(memberId,"a","이강인","파리"));
        System.out.println("1111");
        return "member/findbyid_result"; // view resolver에 의해 /WEB-INF/views/findbyid_result.jsp 로 응답
    }

    // ModelAndView를 사용한 방식은 String과 Model 객체로 변경할 수 있습니다.
    @GetMapping("/find-by-id2")
    public String findMemberById2(@RequestParam String memberId, Model model) {
        if ("java".equals(memberId)) {
            model.addAttribute("member", "이강인 파리");
            return "member/findbyid_ok";
        } else {
            return "member/findbyid_fail";
        }
    }
    
    // paramTest.do -> /member/param-test 로 변경
    @GetMapping("/param-test")
    public String paramTest(@RequestParam String nick, @RequestParam int age, Model model) {
        String type = null;
        if (age > 18) {
            type = "성인";
        } else {
            type = "미성년";
        }
        model.addAttribute("type", type);
        return "member/param_result";
    }

    // registerMember.do -> /member/register 로 변경
    @PostMapping("/register")
    public String registerMember(Member member) {
        System.out.println(member);
        // redirect: 뒤의 URL도 깔끔한 URL로 변경합니다.
        return "redirect:/member/register-result";
    }

    @GetMapping("/register-result")
    public String registerResult() {
        return "member/register_result";
    }
}
/*
 	
 */
