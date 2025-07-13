package test.step2;

import org.kosta.myproject.model.mapper.MovieMapper;
import org.kosta.myproject.model.vo.DirectorVO;
import org.kosta.myproject.model.vo.MovieVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRegisterMovie {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis-config.xml");
		MovieMapper mapper=(MovieMapper) ctx.getBean("movieMapper");
		MovieVO mvo=new MovieVO();
		mvo.setTitle("살인의추억");
		mvo.setGenre("스릴러");
		mvo.setAttendance(100);
		DirectorVO dvo=new DirectorVO();
		dvo.setDirectorId(1);
		mvo.setDirectorVO(dvo);
		int result=mapper.register(mvo);
		System.out.println(mvo.getTitle()+" 영화 등록 완료 "+result);
		ctx.close();
	}
}







