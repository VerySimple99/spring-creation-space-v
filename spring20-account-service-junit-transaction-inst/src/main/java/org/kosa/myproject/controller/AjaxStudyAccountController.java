// AccountController.java - 계좌 관련 웹 요청 처리
package org.kosa.myproject.controller;

import java.util.List;

import org.kosa.myproject.domain.Account;
import org.kosa.myproject.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/accounts")  // api 명시 + 복수형 리소스명 
public class AjaxStudyAccountController {

	private final AccountService accountService;
	public AjaxStudyAccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	/**
     * 방법 1: Path Variable 사용 (REST 권장 방식)
     * URL: /api/accounts/12        ===> 12 계좌번호의 계좌를 조회 
     * 장점: 더 RESTful하고 직관적
     * 
     * @PathVariable은 RESTful 웹 서비스에서 URL 경로에 있는 값을 파라미터로 가져올 때 사용하는 어노테이션
     */
    @GetMapping("/{accountNumber}")
    @ResponseBody  // Spring Boot의 @ResponseBody + Jackson이 Account를 JSON Object로 자동 변환
    public Account getAccountByNumberPath(@PathVariable Long accountNumber) {
    	 // Service에서 계좌 조회 : 존재하지 않는 계좌에 대한 처리를 위해   AccountService의 getAccountByNumber()에
    	//  예외 처리 로직을 추가한다 
        return accountService.getAccountByNumber(accountNumber);
    }
    /**
     * 방법 2: Query Parameter 사용 (현재 방식 유지)
     * URL: /api/accounts?number=12345
     * 장점: 기존 코드 변경 최소화
     */
//    @GetMapping
//    @ResponseBody
//    public Account getAccountByNumber(@RequestParam("number") Long accountNumber) {
//        return accountService.getAccountByNumber(accountNumber);
//    }
    
    
    /**
     * 고객별 계좌 목록 조회
     * URL: /api/accounts/customer/123
     * 리소스 관계를 명확히 표현
     */
    @GetMapping("/customer/{customerId}")
    @ResponseBody //Spring Boot의 @ResponseBody + Jackson이 Account를 JSON Object로 자동 변환
    public List<Account> getAccountsByCustomer(@PathVariable Long customerId) {
        return accountService.getAccountListByCustomerId(customerId);
    }
}







