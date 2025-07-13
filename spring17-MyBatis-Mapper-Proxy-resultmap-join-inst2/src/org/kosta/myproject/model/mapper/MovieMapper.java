package org.kosta.myproject.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.model.vo.MovieVO;

@Mapper
public interface MovieMapper {

	int register(MovieVO mvo);

	List<MovieVO> findMovieList();

	MovieVO findMovieDetailInfo(long movieId);

}
