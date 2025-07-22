package org.kosta.myproject;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kosta.myproject.model.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springbootstudy4ThymeleafLayoutDialectApplicationTests {
	private MemberMapper memberMapper;
	@Autowired
	public Springbootstudy4ThymeleafLayoutDialectApplicationTests(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	@Test
	void contextLoads() {
		Assertions.assertNotNull(memberMapper);
	}
	@Test
	void findAllMemberMapList() {
		List<Map<String,String>> list=memberMapper.findAllMemberMapList();
		for(int i=0;i<list.size();i++) {
			Map<String,String> map=list.get(i);
			System.out.println(map);
			 // mybatis 에서 반환하는 map의 key는 대문자 컬럼명
			System.out.println(map.get("ID")); 
			System.out.println(map.get("NAME")); 
			System.out.println(map.get("ADDRESS"));
			System.out.println("====================");
		}
	}
}











