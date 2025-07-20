package org.kosa.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // MVC 컨트롤러로 등록 (뷰 반환용)
public class HomeController {
    
    /**
     * 홈페이지 요청 처리
     * URL: / 또는 /home
     * 
     * @Controller: 이 클래스가 MVC 컨트롤러임을 Spring에게 알림
     * @GetMapping: HTTP GET 요청을 이 메소드에 매핑
     * Model: 템플릿에 데이터를 전달하는 객체
     */
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        // 템플릿에 전달할 데이터 추가
        model.addAttribute("shopName", "행복한 과일가게");
        model.addAttribute("message", "신선한 과일로 건강한 하루를 시작하세요!");
        
        // templates/index.html 반환
        return "index";
    }
}