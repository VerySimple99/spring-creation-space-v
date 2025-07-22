package org.kosta.myproject.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 핸들러 인터셉터 설정 클래스임을 스프링 컨테이너에 알린다 
public class HandlerInterceptorConfigure implements WebMvcConfigurer {
	private final LoginCheckInterceptor loginCheckInterceptor;
	@Autowired
	public HandlerInterceptorConfigure(LoginCheckInterceptor loginCheckInterceptor) {
		super();
		this.loginCheckInterceptor = loginCheckInterceptor;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns("/**") : /** => 현재 경로(context path)와 하위의 모든 경로에 인터셉터를 적용, 모든 요청에 인터셉터 적용하겠다는 의미 
		//excludePathPatterns("path") : 인터셉터 적용을 제외할 경로를 명시 ( home , 로그인폼이 있는 경로, 정적 자원정보, 비인증상태에서 서비스 할수 있는 경로 ) 
		// /registerMember* : registerMember 문자열로 시작되는 모든 경로 
		registry.addInterceptor(loginCheckInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/","/home","/login","/registerMember*","/css/**","/scripts/**","/images/**");
	}
}



















