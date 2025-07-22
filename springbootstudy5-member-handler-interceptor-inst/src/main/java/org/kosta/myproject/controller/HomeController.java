package org.kosta.myproject.controller;

import org.kosta.myproject.model.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	// final keyword : 불변을 보장 -> 한번 초기화 되면 이후 재할당 불가 -> 안전성을 보장 
	private final MemberMapper memberMapper;
	@Autowired // constructor injection : 즉 생성자를 통한 주입을 권장 , 객체 생성시점에 한번 초기화 되는 것을 보장
	public HomeController(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	@RequestMapping(value={"/","home"}) // 하나의 컨트롤러 메서드에서 여러개의 url pattern을 명시할 수 있음 
	public String home(Model model) {
		model.addAttribute("totalMemberCount", memberMapper.getTotalMemberCount());
		model.addAttribute("addressList", memberMapper.getAddressKindList());
		return "index";
	}
}





