package org.kosta.myproject.model.member;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Override
	public void findMember() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("find member");
	}
	@Override
	public void findAllMember() {
		try {
			Thread.sleep(1800);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("find all member");
	}
	@Override
	public void register(String id,String name) throws DuplicateIdException {
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}		
		if(id.equals("java"))
			throw new DuplicateIdException("회원 아이디 중복됩니다!");
		System.out.println("register member");
	}
}



