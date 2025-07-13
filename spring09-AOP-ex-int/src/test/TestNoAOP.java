package test;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberService;
import org.kosta.myproject.model.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 		요구사항 
 		현 시스템의 검색(find계열) 기능 메서드들을 대상으로 
 		특정 업무(로깅)를 수행하도록 작업을 추가해야 한다 
 		대상 Target 클래스와 메서드가 다수 존재 
 		
 		참고) 로깅 : 실행시간, 어떤 주체가 실행했는지, 발생 에러 등에 관련 정보를 파일에 기록
 		
 		방안 1 :  직접 코드에 기능을 기술 => 공통적이고 반복적인 작업이 불가피 -> 낮은 생산성 
 		    									 만약 이후 공통정책이 변경될 때는 다시 반복작업 -> 낮은 유지보수성
 		
 		spring07-AOP-solution 에서 AOP를 적용해 비교해본다 
 */
public class TestNoAOP {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService ms=(MemberService) ctx.getBean("memberServiceImpl");
		ProductService ps=(ProductService)ctx.getBean("productServiceImpl");
		ms.findMemberByAddress();
		ms.findMemberById();
		ms.findMemberList();
		ms.register();
		ps.findProductById();
		ps.findProductByName();
		ps.findProductListByMaker();
		ps.updateProduct();
		ctx.close();
	}
}
















