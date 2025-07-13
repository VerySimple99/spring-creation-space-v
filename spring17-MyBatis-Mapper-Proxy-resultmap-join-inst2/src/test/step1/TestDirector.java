package test.step1;

import org.kosta.myproject.model.mapper.DirectorMapper;
import org.kosta.myproject.model.vo.DirectorVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDirector {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis-config.xml");
		DirectorMapper dm=(DirectorMapper)ctx.getBean("directorMapper");
		DirectorVO dvo=new DirectorVO("봉준호","아카데미 수상");
		int result=dm.register(dvo);
		System.out.println("감독 정보 등록 result:"+result);
		DirectorVO rvo=dm.findDirectorById(1);
		System.out.println(rvo);// 봉준호 감독 정보 ..
		ctx.close();
	}
}







