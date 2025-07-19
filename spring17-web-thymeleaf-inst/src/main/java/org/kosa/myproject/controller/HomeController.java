package org.kosa.myproject.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.kosa.myproject.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Thymeleaf 템플릿을 사용하는 컨트롤러
 * JSP와 달리 @ResponseBody 없이 뷰 이름만 반환
 */
@Controller
public class HomeController {
    
    /**
     * 메인 페이지 요청 처리
     * Model: Thymeleaf Context로 변환되어 템플릿에 데이터 전달
     */
    @GetMapping("/")
    public String home(Model model) {
        // 템플릿에서 사용할 데이터 준비
        model.addAttribute("pageTitle", "Thymeleaf 첫 페이지");
        model.addAttribute("message", "안녕하세요! Thymeleaf 세계에 오신 것을 환영합니다.");
        model.addAttribute("currentTime", 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("userName", "개발자");
        
        // 반환값: 템플릿 파일명 (확장자 제외)
        // "index" → templates/index.html 파일을 찾음
        return "index";
    }
    
    /**
     * 사용자 프로필 페이지
     * 경로 변수와 객체 전달 예제
     */
    @GetMapping("/profile")
    public String profile(Model model) {
        // 생년월일로 사용자 객체 생성
        User user = new User("김철수", "kim@example.com", 
                            LocalDate.of(1998, 5, 15));  // 1998년 5월 15일생
        
        model.addAttribute("user", user);
        model.addAttribute("hobbies", new String[]{"독서", "코딩", "여행"});
        
        return "user/profile";
    }
}

