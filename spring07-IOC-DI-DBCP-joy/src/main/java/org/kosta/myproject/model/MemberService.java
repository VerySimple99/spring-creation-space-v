package org.kosta.myproject.model;

import java.sql.SQLException;

public interface MemberService {
	public MemberVO findMemberById(String id) throws SQLException;
}
