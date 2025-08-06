package org.kosa.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @RestController :  @Controller + @ResponseBody  ==> 이후 공부 예정 
 */
@Controller
public class AjaxStudyController {
	@GetMapping("/test-ajax1")
	@ResponseBody // ajax 응답을 위한 어노테이션 ( 페이지가 아니라 데이터로 응답 ) 
	public String testAjax1(String userId) {
		try {
			Thread.sleep(3000);// 테스트를 위해 3초간 시간 지연
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello ajax "+userId;
	}
	@GetMapping("/test-ajax2")
	@ResponseBody // ajax 응답을 위한 어노테이션 ( 페이지가 아니라 데이터로 응답 ) 
	public String testAjax2(String userId) {
		try {
			Thread.sleep(3000);// 테스트를 위해 3초간 시간 지연
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello jquery ajax "+userId;
	}
	@GetMapping("/test-ajax3")
	@ResponseBody // ajax 응답을 위한 어노테이션 ( 페이지가 아니라 데이터로 응답 ) 
	public String testAjax3(String userId) {
		try {
			Thread.sleep(3000);// 테스트를 위해 3초간 시간 지연
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello fetch ajax "+userId;
	}
}
