package org.kosta.myproject.model;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Override
	public void findMemberById() {
		System.out.println("**공통관심사항: 특정요구사항을 수행 findMemberById**");
		System.out.println(getClass().getName()+" core concern findMemberById");
	}
	@Override
	public void findMemberByAddress() {
		System.out.println("**공통관심사항: 특정요구사항을 수행 findMemberByAddress**");
		System.out.println(getClass().getName()+" core concern findMemberByAddress");
	}
	@Override
	public void findMemberList() {
		System.out.println("**공통관심사항: 특정요구사항을 수행 findMemberList**");
		System.out.println(getClass().getName()+" core concern findMemberList");
	}
	@Override
	public void register() {
		System.out.println(getClass().getName()+" core concern register");
	}
	// 그외 많은 메서드가 있다고 가정 
}
