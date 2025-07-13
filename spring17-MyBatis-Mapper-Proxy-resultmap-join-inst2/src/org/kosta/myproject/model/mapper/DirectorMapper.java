package org.kosta.myproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.model.vo.DirectorVO;

@Mapper
public interface DirectorMapper {

	int register(DirectorVO dvo);

	DirectorVO findDirectorById(long id);

}
