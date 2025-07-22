package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.myproject.model.vo.CustomerVO;
import org.kosta.myproject.model.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyTestController {
	@RequestMapping("hello")
	public String hello(Model model) {
		model.addAttribute("info1", "대한민국");
		model.addAttribute("info2", "포르투칼");
		return "result1";
	}
	@RequestMapping("hi")
	public String hi(Model model) {
		model.addAttribute("message", "대한민국 16강 진출");
		return "result2";
	}
	@RequestMapping("paramTest1")
	public String paramTest1(String customerId,String customerName) {
		System.out.println(customerId+" "+customerName);
		return "result3";
	}
	@PostMapping("paramTest2")
	public String paramTest2(String memberName,int memberAge) {
		System.out.println(memberName+" "+memberAge);
		return "result4";
	}
	@PostMapping("paramTest3")
	public String paramTest3(String[] menu,Model model) {
		for(String food:menu)
			System.out.println(food);
		model.addAttribute("menuList", menu);
		return "result5";
	}
	// HandlerMapping에 의해 컨트롤러가 연결 
	// http request method  post 방식이 아니면 http response status code 405 error 응답
	@PostMapping("paramTest4") 
	public String paramTest4(CustomerVO customerVO,Model model) {//HandlerAdapter가 request를 분석해서 적절한 데이터로 변환해 전달
		System.out.println(customerVO);
		model.addAttribute("customerVO",customerVO);
		return "result6";//view name : ViewResolver에 의해 templates/result6.html 로 응답한다
	}
	@PostMapping("paramTest5")
	public String paramTest5(UserVO userVO,Model model) {// HandlerAdapter에 의해 UserVO has a CarVO 가 생성되어 전달된다 
		//System.out.println(userVO);
		model.addAttribute("userVO", userVO);
		return "result7";
	}	
	@RequestMapping("sessionTest0")
	// HandlerAdapter 가 session이 존재하지 않으면 새로 생성해서 전달 ,
	// 기존 session이 있으면 기존 세션 전달 
	public String sessionTest0(HttpSession session) {	
		System.out.println("session 객체를 전달받을 수 있다 "+session);		
		return "result9";// view name 
	}
	@RequestMapping("sessionTest")
	// HandlerAdapter 가 session이 존재하지 않으면 새로 생성해서 전달 ,
	// 기존 session이 있으면 기존 세션 전달하고  vo 를 할당  
	public String sessionTest(HttpSession session) {	
		System.out.println("session 객체를 전달받을 수 있다 "+session);
		session.setAttribute("customerVO", new CustomerVO("java","아이유","오리"));
		return "result9";// view name 
	}
	@RequestMapping("sessionTest2")
	public String sessionTest2(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession(false); // 기존 세션이 없으면 null 반환 
		String checkMessage=null;
		if(session==null) {
			checkMessage="세션이 존재하지 않습니다";
		}else if(session.getAttribute("customerVO")==null) {
			checkMessage="인증 정보가 존재하지 않습니다";
		}else if(session!=null&&session.getAttribute("customerVO")!=null) {
			checkMessage="로그인 상태입니다";
		}
		model.addAttribute("checkMessage", checkMessage);
		return "result9-2";
	}
}















