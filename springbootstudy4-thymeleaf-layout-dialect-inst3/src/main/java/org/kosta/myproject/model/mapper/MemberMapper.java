package org.kosta.myproject.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	List<Map<String, String>> findAllMemberMapList();
	
}
