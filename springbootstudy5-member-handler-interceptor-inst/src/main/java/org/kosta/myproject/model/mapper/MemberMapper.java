package org.kosta.myproject.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.model.vo.MemberVO;

@Mapper // MapperProxy ( 해당 인터페이스를 구현하는 프록시 객체를 위한 설정 )
public interface MemberMapper {

	int getTotalMemberCount();

	MemberVO findMemberById(String id);

	List<String> getAddressKindList();

	List<MemberVO> findMemberListByAddress(String address);

	MemberVO login(MemberVO memberVO);

	void updateMember(MemberVO memberVO);

	int registerMember(MemberVO memberVO);
	
}





