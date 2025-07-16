package org.kosa.myproject;

import java.util.List;

import org.kosa.myproject.domain.Member;
import org.kosa.myproject.mapper.MemberMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyBatisStudyRunner implements CommandLineRunner{
	private final MemberMapper memberMapper;
	
	public MyBatisStudyRunner(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public void run(String... args) throws Exception {
	//	System.out.println(memberMapper.getTotalMemberCount());
		List<Member> list=memberMapper.findMemberList();
		for(Member vo:list)
			System.out.println(vo);
	}

}
