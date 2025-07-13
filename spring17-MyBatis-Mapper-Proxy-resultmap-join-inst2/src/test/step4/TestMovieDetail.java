package test.step4;

import org.kosta.myproject.model.mapper.MovieMapper;
import org.kosta.myproject.model.vo.MovieVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMovieDetail {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis-config.xml");
		MovieMapper mapper=(MovieMapper) ctx.getBean("movieMapper");
		// 영화 아이디에 의한 영화 상세정보( 감독정보까지 포함 )를 조회 
		long movieId=1;
		MovieVO vo=mapper.findMovieDetailInfo(movieId);
		if(vo!=null) {
		System.out.println(vo.getMovieId());
		System.out.println(vo.getTitle());
		System.out.println(vo.getGenre());
		System.out.println(vo.getAttendance());
		System.out.println(vo.getDirectorVO().getDirectorId());
		System.out.println(vo.getDirectorVO().getDirectorName());
		System.out.println(vo.getDirectorVO().getIntro());
		}else {
			System.out.println(movieId+"아이디에 대한 영화정보가 없음");
		}
		ctx.close();
	}
}







