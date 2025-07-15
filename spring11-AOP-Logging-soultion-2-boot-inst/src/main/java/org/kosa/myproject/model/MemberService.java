package org.kosa.myproject.model;

public interface MemberService {

	void findMemberById(String id);

	void register(String memberInfo);

	void findMemberListByAddress(String address);

}