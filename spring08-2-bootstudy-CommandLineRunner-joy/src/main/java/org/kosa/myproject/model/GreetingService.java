package org.kosa.myproject.model;

import org.springframework.stereotype.Service;

// 간단한 서비스 클래스
@Service
public class GreetingService {
    public String getGreeting() {
        return "안녕하세요! Spring Boot의 세계에 오신 것을 환영합니다! 🎉";
    }
}
