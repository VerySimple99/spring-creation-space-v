package org.kosta.myproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Override
	public void findMemberById(String id) {
		System.out.println("core findMemberById id:"+id);
		logger.debug("cross cutting MemberServiceImpl findMemberById 검색어 {}",id);
	}
	@Override
	public void register(String memberInfo) {
		System.out.println("core register memberInfo:"+memberInfo);		
	}
	@Override
	public void findMemberListByAddress(String address) {
		System.out.println("core findMemberListByAddress:"+address);
		logger.debug("cross cutting MemberServiceImpl findMemberListByAddress 검색어 {}",address);
	}
}
