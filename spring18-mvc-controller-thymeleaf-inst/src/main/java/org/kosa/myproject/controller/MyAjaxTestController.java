package org.kosa.myproject.controller;

import java.util.List;

import org.kosa.myproject.model.mapper.CustomerMapper;
import org.kosa.myproject.model.vo.CustomerVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyAjaxTestController {
	private CustomerMapper customerMapper;

	public MyAjaxTestController(CustomerMapper customerMapper) {
		super();
		this.customerMapper = customerMapper;
	}
	@GetMapping("ajaxTestView")
	public String ajaxTestView(Model model) {
		model.addAttribute("message", "spring ajax test");
		return "ajax-test";// view name 리턴 
	}
	@GetMapping("testAjax1")
	@ResponseBody // ajax 응답을 위한 spring mvc 어노테이션 : view name에 의한 페이지 응답이 아니라 필요한 데이터로 응답 
	public String testAjax1(String id) { 
		System.out.println("ajax 요청에 대한 응답 id:"+id);
		return "ajax 응답 "+id; // view name 리턴이 아니라 응답할 데이터를 리턴 
	}
	@GetMapping("testAjax2")
	@ResponseBody // spring controller 가 ajax 응답을 위해 사용 : 응답할 데이터를 반환( 페이지가 아님 )
	public CustomerVO testAjax2(String id) {
		CustomerVO customerVO=customerMapper.findCustomerById(id);
		return customerVO; // jackson databind 에 의해 VO가 자동 변환되어  JSONObject 로 응답 
	}	
	@GetMapping("testAjax3")
	@ResponseBody // ajax 응답 
	public List<CustomerVO> testAjax3(String address){
		return customerMapper.findCustomerListByAddress(address);
	}
}













