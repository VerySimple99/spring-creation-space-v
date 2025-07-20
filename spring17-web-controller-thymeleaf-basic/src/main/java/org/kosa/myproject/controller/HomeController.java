package org.kosa.myproject.controller;

import org.springframework.stereotype.Controller; // 이 클래스가 스프링 컨트롤러임을 선언합니다.
import org.springframework.ui.Model; // 뷰(View)에 데이터를 전달하는 데 사용되는 객체입니다.
import org.springframework.web.bind.annotation.GetMapping; // HTTP GET 요청을 처리하는 메소드임을 명시합니다.
/**
 * @GetMapping({"home.do","/"})
 * HTTP GET 요청이 들어올 때 이 메소드가 실행되도록 매핑합니다.
 * 두 가지 경로, 즉 "home.do"와 "/" (애플리케이션의 루트 경로)에 대해 이 메소드가 응답합니다.
 * * @param model Model 객체는 컨트롤러에서 뷰로 데이터를 전달하는 데 사용됩니다.
 * 이 객체에 속성을 추가하면, 해당 데이터가 뷰(여기서는 index.jsp)에서 사용 가능하게 됩니다.
 * @return String "index"는 뷰의 이름을 나타냅니다.
 * 스프링의 ViewResolver 설정에 따라 이 이름이 실제 뷰 경로로 변환됩니다.
 * 예를 들어, InternalResourceViewResolver를 사용하고 prefix가 "/WEB-INF/views/", suffix가 ".jsp"라면,
 * "index"는 "/WEB-INF/views/index.jsp"로 해석되어 해당 JSP 파일이 사용자에게 응답됩니다.
 */
/**
 @Controller: 이 클래스가 웹 요청을 처리하는 스프링의 '컨트롤러' 역할을 한다는 것을 알려줍니다.

@GetMapping: HTTP GET 방식의 요청을 이 메소드로 연결합니다. {} 안에 여러 경로를 지정하여 하나의 메소드로 다양한 URL을 처리할 수 있습니다.

Model: 컨트롤러와 뷰 사이의 데이터 전달 역할을 합니다. model.addAttribute()를 사용해 데이터를 담으면, 뷰(JSP, Thymeleaf 등)에서 그 데이터를 사용할 수 있습니다. 서블릿의 request.setAttribute()와 같은 기능이라고 생각하시면 이해가 빠르실 겁니다.

return "index": 이 코드는 사용자에게 보여줄 뷰의 이름을 반환합니다. 스프링의 ViewResolver는 이 "index"라는 이름을 가지고 /WEB-INF/views/ (prefix)와 .jsp (suffix)를 결합하여 /WEB-INF/views/index.jsp라는 실제 뷰 파일을 찾아 응답하게 됩니다.
 */
// 이 클래스가 스프링 MVC에서 컨트롤러 역할을 수행함을 나타냅니다.
@Controller 
public class HomeController {

    
	@GetMapping({"/home","/"})
	public String home(Model model) {
		// System.out.println("HomeController home");
		// 개발 시 로그를 확인하기 위해 사용될 수 있는 주석 처리된 코드입니다.
		
		// 아래는 dao와 같은 모델 계층과 연동한 결과를 request.setAttribute(name, value) 하는 코드
		// model.addAttribute("속성명", "값"); 을 사용하여 뷰로 데이터를 전달합니다.
		// 이는 서블릿의 request.setAttribute()와 유사한 역할을 합니다.
		model.addAttribute("result", "model 계층 연동 결과");

		/*
		 * <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		 * <property name="prefix"  value="/WEB-INF/views/"  />
		 * <property name="suffix" value=".jsp"/>
		 * </bean>
		 * * 위 스프링 ViewResolver 설정에 의해 아래 return viewName은
		 * /WEB-INF/views/index.jsp 로 응답하게 됩니다.
		 * * 이 주석은 ViewResolver의 동작 방식을 설명합니다.
		 * 컨트롤러가 반환하는 문자열("index")이 뷰의 논리적 이름이며,
		 * ViewResolver가 이 논리적 이름을 물리적 파일 경로로 변환하는 역할을 합니다.
		 */
		return "index";
	}

}