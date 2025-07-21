package org.kosa.myproject.controller;

import org.kosa.myproject.model.mapper.MemberMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	private MemberMapper memberMapper;

	public MemberController(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	@GetMapping("getTotalMemberCount")
	public String getTotalMemberCount(Model model) {
		model.addAttribute("totalMemberCount", memberMapper.getTotalMemberCount());
		return "member-count";
	}
}



