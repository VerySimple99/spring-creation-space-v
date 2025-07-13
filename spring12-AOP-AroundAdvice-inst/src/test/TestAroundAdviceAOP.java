package test;

import java.sql.SQLException;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.InventoryException;
import org.kosta.myproject.model.PersonService;
import org.kosta.myproject.model.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAroundAdviceAOP {
	/*
	 *  Around Advice 를 학습하기 위한 예제 
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		PersonService personService=(PersonService) ctx.getBean("personService");
		ProductService productService=(ProductService)ctx.getBean("productService");
		// 인터페이스 기반이 아닌 클래스도 AOP 적용이 가능하다 
		// 아래 출력 결과를 보면 클래스 기반 Proxy 객체가 스프링 컨테이너에 의해 전달되었음을 확인할 수 있음
		System.out.println(personService.getClass().getName());
		System.out.println(productService.getClass().getName());
		personService.register();
		System.out.println(personService.getNick());
		productService.register("도미노피자");
		System.out.println(productService.getMaker());
		System.out.println("**특정 예외(InventoryException) 발생시 Cross Cutting Concern Logic 실행 테스트**");
		try {
			productService.buyProduct(0);
		} catch (SQLException e) {
			System.out.println("main:"+e.getMessage());
		}
		try {
			productService.sellProduct(12); // 이 시점에 InventoryException 발생됨 -> AOP 적용 
		} catch (InventoryException e) { 
			System.out.println("main:"+e.getMessage());
		}
		ctx.close();
	}
}








