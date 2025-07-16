package org.kosa.myproject.model;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
	//private Logger logger=LoggerFactory.getLogger(getClass());

	public void findMemberById(String id) {
		System.out.println("core findMemberById id:"+id);
		//logger.debug("cross cutting MemberServiceImpl findMemberById 검색어 {}",id);
	}

	public void register(String memberInfo) {
		System.out.println("core register memberInfo:"+memberInfo);		
	}

	public void findMemberListByAddress(String address) {
		System.out.println("core findMemberListByAddress:"+address);
		//logger.debug("cross cutting MemberServiceImpl findMemberListByAddress 검색어 {}",address);
	}
}
