package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.myproject.model.mapper.MemberMapper;
import org.kosta.myproject.model.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	private final MemberMapper memberMapper;
	private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	public MemberController(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	@GetMapping("findMemberById")
	public String findMemberById(String id,Model model) {
		String viewName=null;
		MemberVO memberVO=memberMapper.findMemberById(id);
		if(memberVO==null) {
			viewName="member/findbyid-fail";
		}else {
			model.addAttribute("memberVO", memberVO);
			viewName="member/findbyid-ok";
		}
		return viewName;
	}
	@GetMapping("findMemberListByAddress")
	public String findMemberListByAddress(String address,Model model) {
		model.addAttribute("memberList", memberMapper.findMemberListByAddress(address));
		return "member/findbyaddress-result";
	}
	// 세션 테스트 
	@RequestMapping("session-test")
	public String sessionTest(HttpServletRequest request) {
		// jsp 에서는 세션을 기본 생성하지만 , thymeleaf 는 세션을 생성하지 않는다 
		HttpSession session=request.getSession(false);// getSession(false) : 세션이 있으면 기존 세션 반환 , 없으면 null 반환 
		log.debug("session test: {}",session);
		return "redirect:home";
	}
	@RequestMapping("session-test2")
	public String sessionTest2(HttpSession session) {//세션이 없으면 세션을 생성, 기존 세션이 있으면 기존세션을 전달 
		log.debug("session test2: {}",session);
		return "redirect:home";
	}
	@PostMapping("login") // post request method만 허용 
	public String login(MemberVO memberVO,HttpServletRequest request) {
		MemberVO resultVO=memberMapper.login(memberVO);
		log.debug("login result vo:{}",resultVO);
		if(resultVO==null) { // mapper의 반환값이 null이면 로그인 실패 
			return "member/login-fail";
		}else {// 로그인 성공 
			HttpSession session=request.getSession();
			session.setAttribute("mvo", resultVO);
			return "redirect:/";
		}		
	}
	@PostMapping("logout") // 로그아웃, 로그인은 post 방식만 허용하도록 한다 
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if(session!=null)
			session.invalidate();
		return "redirect:/";
	}
	@RequestMapping("updateMemberForm")
	public String updateMemberForm() {
		return "member/update-form";
	}
	@PostMapping("updateMember")
	public String updateMember(MemberVO memberVO,HttpServletRequest request) {
		memberMapper.updateMember(memberVO);
		HttpSession session=request.getSession(false);
	//	if(session==null||session.getAttribute("mvo")==null) {
	//		return "redirect:home";
	//	}else {
			session.setAttribute("mvo", memberVO);//세션의 mvo를 업데이트
		   return "redirect:updateMemberResult";
	//	}
	}
	@RequestMapping("updateMemberResult")
	public String updateMemberResult() {
		return "member/update-result";
	}
	@RequestMapping("registerMemberForm")
	public String registerMemberForm() {
		return "member/register-form";
	}
	@PostMapping("registerMember")
	public String registerMember(MemberVO memberVO) {
		memberMapper.registerMember(memberVO);
		return "redirect:registerMemberResult";
	}
	@RequestMapping("registerMemberResult")
	public String registerMemberResult() {
		return "member/register-result";
	}
}
















