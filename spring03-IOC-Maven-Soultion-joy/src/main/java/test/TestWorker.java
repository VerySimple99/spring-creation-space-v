package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Tool;

public class TestWorker {
	public static void main(String[] args) {
		// 필요시 객체를 생성해 사용
		// Hammer 에서 Spade 로 변경
		// Hammer tool=new Hammer();
		// 필요로 하는 객체(컴포넌트 or Bean)를 직접 생성 ( 직접 제어 )
		// Spade tool=new Spade();
		// tool.work();
		/***** 위 코드는 기존 제어 방식, 아래는 IOC 적용 제어방식 ****/
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("spring ioc container start");
		System.out.println(ctx.getBean("tool"));
		System.out.println(ctx.getBean("tool"));
		System.out.println(ctx.getBean("tool"));// 동일한 객체의 주소값이 출력 : 스프링 컨테이너는 Singleton 방식으로 객체bean를 운용
		Tool tool = (Tool) ctx.getBean("tool");
		tool.work();
		ctx.close();
	}
}
