package org.kosta.myproject.controller;

import org.kosta.myproject.model.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	private MemberMapper memberMapper;
	@Autowired
	public MemberController(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	@RequestMapping("getTotalMemberCount")
	public String getTotalMemberCount(Model model) {
		model.addAttribute("totalMemberCount", memberMapper.getTotalMemberCount());
		return "member-count";
	}
}



