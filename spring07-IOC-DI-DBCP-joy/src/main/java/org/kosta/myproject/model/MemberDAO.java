package org.kosta.myproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface MemberDAO {

	void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException;

	MemberVO findMemberById(String id) throws SQLException;

}