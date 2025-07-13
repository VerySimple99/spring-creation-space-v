package org.kosta.myproject.model;

import java.util.List;

public interface MemberDAO {
	public int getTotalMemberCount();

	public List<MemberVO> findMemberList();

	public int register(MemberVO memberVO);
	/*
	 	java interface 는  public static final 상수와 구현부 없는 abstract 메서드만 정의 가능
	 	jdk1.8 이상에서는 구현부가 있는 default , static 메서드가 지원된다 
	 	유지보수성 차원에서  
	 */
	public default int deleteMember(String id) {
		return 0;
	}
}