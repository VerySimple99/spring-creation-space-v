package org.kosta.myproject.model;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemberServiceImpl implements MemberService {
	private final MemberDAO memberDAO;
	// PointDAO ... 등 
	@Autowired // DI 
	public MemberServiceImpl(MemberDAO memberDAO) {
		super();
		this.memberDAO = memberDAO;
	}
	@Override
	public MemberVO findMemberById(String id) throws SQLException {		
		return memberDAO.findMemberById(id);
	}
}
