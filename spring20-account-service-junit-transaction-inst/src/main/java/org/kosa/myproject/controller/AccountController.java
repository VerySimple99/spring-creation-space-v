// AccountController.java - 계좌 관련 웹 요청 처리
package org.kosa.myproject.controller;

import org.kosa.myproject.domain.Account;
import org.kosa.myproject.domain.Customer;
import org.kosa.myproject.service.AccountService;
import org.kosa.myproject.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

/**
 * 계좌 관련 웹 요청 처리 컨트롤러
 * - PRG Pattern 구현: POST → Redirect → GET
 * - 고객별 계좌 목록 조회 및 계좌 등록 기능
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {
    
    private final AccountService accountService;
    private final CustomerService customerService;
    
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }
    
    /**
     * 고객별 계좌 목록 조회 (GET)
     * - PRG Pattern의 마지막 단계 (GET)
     * - 계좌 목록과 새 계좌 등록 폼을 함께 제공
     * 
     * @param customerId 조회할 고객 ID
     * @param model 뷰에 전달할 데이터
     * @return 계좌 목록 템플릿
     */
    @GetMapping("/customer/{customerId}")
    public String getAccountList(@PathVariable Long customerId, Model model) {
        try {
            // 고객 정보 조회
            Customer customer = customerService.getCustomerById(customerId);
            if (customer == null) {
                model.addAttribute("errorMessage", "존재하지 않는 고객입니다. (ID: " + customerId + ")");
                return "error";
            }
            
            // 해당 고객의 계좌 목록 조회
            List<Account> accountList = accountService.getAccountListByCustomerId(customerId);
            
            // 뷰에 전달할 데이터 설정
            model.addAttribute("customer", customer);
            model.addAttribute("accountList", accountList);
      
            
            return "accounts/list"; // templates/accounts/list.html
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", "계좌 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
            return "error";
        }
    }
    
    @PostMapping("/register")
    public String registerAccount(@RequestParam Long customerId,
                                @RequestParam String accountType,
                                @RequestParam BigDecimal balance,
                                RedirectAttributes redirectAttributes) {
        try {
            // 서버에서 추가 검증: 고객 존재 여부 확인
            Customer customer = customerService.getCustomerById(customerId);
            if (customer == null) {
                redirectAttributes.addFlashAttribute("errorMessage", 
                    "유효하지 않은 고객 정보입니다.");
                return "redirect:/accounts";
            }
            
            // 계좌 등록 처리
            Account newAccount = new Account(customerId, accountType, balance);
            Account registeredAccount = accountService.registerAccount(newAccount);
            
            redirectAttributes.addFlashAttribute("successMessage", 
                "계좌가 성공적으로 등록되었습니다. (계좌번호: " + registeredAccount.getAccountNumber() + ")");
            
            return "redirect:/accounts/customer/" + customerId;
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "계좌 등록 실패: " + e.getMessage());
            return "redirect:/accounts/customer/" + customerId;
        }
    }
    
}