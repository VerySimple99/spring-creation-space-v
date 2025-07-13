package test.step3;

import java.util.List;

import org.kosta.myproject.model.mapper.MovieMapper;
import org.kosta.myproject.model.vo.MovieVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMovieList {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis-config.xml");
		MovieMapper mapper=(MovieMapper) ctx.getBean("movieMapper");
		// 영화 리스트 화면에는 영화타이틀과 감독명이 제공( 이후 상세정보보기를 위해 영화아이디) 가 필요
		List<MovieVO> list=mapper.findMovieList();
		for(MovieVO vo:list)
			System.out.println(vo.getTitle()+" "+vo.getMovieId()+" "+vo.getDirectorVO().getDirectorName());
		ctx.close();
	}
}







