package org.kosa.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(Model model) {
		System.out.println("HomeController home");
		// 아래는  request.setAttribute(name,value); 를 의미  
		model.addAttribute("message", "Hello Spring Boot");
		// index 는 view name 
		return "index"; // viewResolver에 의해 templates/index.html 로 응답 
	}
}



