package org.kosa.myproject.controller;

import org.kosa.myproject.model.mapper.CustomerMapper;
import org.kosa.myproject.model.vo.CustomerVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class CustomerController {
	private CustomerMapper customerMapper;

	public CustomerController(CustomerMapper customerMapper) {
		super();
		this.customerMapper = customerMapper;
	}
	@PostMapping("redirectTest")
	public String registerCustomer(CustomerVO customerVO) {
		//기존 db에 id가 존재하면 ( 아이디가 중복되면 ) 
		if(customerMapper.checkDuplicateId(customerVO.getId())>0) {
			return "duplicateId";
		}
		customerMapper.registerCustomer(customerVO);
		System.out.println("registerCustomer "+customerVO+" db insert 후 redirect로 응답");
		// forward 방식으로 응답하면 새로고침시 재동작 되어 문제 발생 
		//return "result8";
		//위 코드를 아래의 redirect 방식으로 변경 
		//client에게 registerResult url pattern 의 컨트롤러로 요청해서 
		//응답받도록 한다  
		return "redirect:registerResult?customerId="+customerVO.getId();
	}
	@PostMapping("registerResult")
	public String registerResult(String customerId,Model model) {
		//System.out.println("registerResult id:"+customerId);
		//System.out.println("registerResult 가 forward 방식으로 result8 html을 응답");
		model.addAttribute("customerVO", customerMapper.findCustomerById(customerId));
		return "result8";
	}
}







