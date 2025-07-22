package org.kosta.myproject;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kosta.myproject.model.mapper.CustomerMapper;
import org.kosta.myproject.model.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//JUnit : 자바 단위 테스트 프레임워크 : DI 을 통해 단위 테스트를 할 수 있음
@SpringBootTest
public class UnitTestCustomer {
	private CustomerMapper customerMapper;
	@Autowired
	public UnitTestCustomer(CustomerMapper customerMapper) {
		super();
		this.customerMapper = customerMapper;
	}
	@Test
	public void findCustomerById() {
		String id="green";
		CustomerVO customerVO=customerMapper.findCustomerById(id);
		System.out.println(customerVO);
	}
	@Test
	public void registerCustomer() {
		CustomerVO customerVO=new CustomerVO("green","황희찬","울버햄튼");
		int result=customerMapper.registerCustomer(customerVO);		
		//System.out.println("고객 등록 완료:"+result);
		Assertions.assertEquals(1, result);// 예상값 : 1 , 실제값 : result  => 일치하면 green , 일치x : red 
	}
	@Test
	public void checkDuplicateId() {
		String id="java";
		int result=customerMapper.checkDuplicateId(id);
		Assertions.assertEquals(1, result);
	}
	@Test
	public void findCustomerListByAddress() {
		String address="도하";
		List<CustomerVO> list=customerMapper.findCustomerListByAddress(address);
		Assertions.assertEquals(3, list.size());
	}
}



















