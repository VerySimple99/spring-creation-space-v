package org.kosa.myproject.model.member;

public interface MemberService {

	void findMember();

	void findAllMember();

	void register(String id, String name) throws DuplicateIdException;

}