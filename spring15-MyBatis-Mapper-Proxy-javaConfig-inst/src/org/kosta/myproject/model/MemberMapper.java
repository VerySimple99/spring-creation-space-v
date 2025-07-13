package org.kosta.myproject.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper // MyBatis Mapper Proxy 설정 
public interface MemberMapper {

	List<MemberVO> findAllMemberList();

	Map<String, String> findMemberMapById(String id);

	int register(HashMap<String, String> paramMap);

}
