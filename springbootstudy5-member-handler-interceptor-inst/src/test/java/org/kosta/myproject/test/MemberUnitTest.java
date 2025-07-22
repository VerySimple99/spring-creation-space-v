package org.kosta.myproject.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kosta.myproject.model.mapper.MemberMapper;
import org.kosta.myproject.model.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // spring 과 junit 을 이용한 테스트를 지원 
public class MemberUnitTest {
	private Logger logger=LoggerFactory.getLogger(getClass());
	private MemberMapper memberMapper;
	// SpringBoot 2.2 대 버전부터 JUnit 5 를 지원하여 생성자를 통한 주입 가능 ( constructor injection ) 
	// JUnit 4 버전은 field injection 만 가능 	
	@Autowired
	public MemberUnitTest(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	@Test
	public void testMemberMapper() {
		logger.debug("test member mapper {}",memberMapper);
	}
	@Test
	public void getTotalMemberCount() {
		int totalCount=memberMapper.getTotalMemberCount();
		Assertions.assertNotEquals(0,totalCount);// 0 이 기대값 , totalCount 가 실제값 => 같지 않으면 통과 
	}
	@Test
	public void findMemberById() {
		String id="java";
		MemberVO memberVO=memberMapper.findMemberById(id);
		Assertions.assertNotNull(memberVO);
	}
	@Test
	public void getAddressKindList() {
		List<String> addressList=memberMapper.getAddressKindList();
		Assertions.assertEquals(3, addressList.size());
	}
	@Test
	public void findMemberListByAddress() {
		String address="토트넘";
		List<MemberVO> list=memberMapper.findMemberListByAddress(address);
		Assertions.assertEquals(2, list.size());
	}
	@Test
	public void login() {
		MemberVO memberVO=new MemberVO("java","a",null,null);
		MemberVO resultVO=memberMapper.login(memberVO);
	//	Assertions.assertNotNull(resultVO);
		logger.debug("login vo: {}",resultVO);
	}
	@Test
	public void updateMember() {
		MemberVO memberVO=new MemberVO("java","a","장기하","파주");
		logger.debug("수정전 member {}",memberMapper.findMemberById(memberVO.getId()));
		memberMapper.updateMember(memberVO);		
		logger.debug("수정후 member {}",memberMapper.findMemberById(memberVO.getId()));
	}
	@Test
	public void registerMember() {
		MemberVO memberVO=new MemberVO("spring1","a","이강인","파주");
		int result=memberMapper.registerMember(memberVO);
		Assertions.assertEquals(1, result);
	}
}















