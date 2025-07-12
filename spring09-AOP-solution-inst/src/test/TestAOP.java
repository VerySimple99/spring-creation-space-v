package test;

import org.kosa.myproject.config.AppConfig;
import org.kosa.myproject.model.MemberService;
import org.kosa.myproject.model.NoticeService;
import org.kosa.myproject.model.ProductService;
import org.kosa.myproject.model.ProductServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 		요구사항 
 		현 시스템의 검색(find계열) 기능 메서드들을 대상으로 
 		특정 업무(로깅)를 수행하도록 작업을 추가해야 한다 
 		대상 Target 클래스와 메서드가 다수 존재 
 		
 		참고) 로깅 : 실행시간, 어떤 주체가 실행했는지, 발생 에러 등에 관련 정보를 파일에 기록
 		
 		방안 1 :  직접 코드에 기능을 기술 => 공통적이고 반복적인 작업이 불가피 -> 낮은 생산성 
 		    									 만약 이후 공통정책이 변경될 때는 다시 반복작업 -> 낮은 유지보수성
 		
 	   방안 2 : 현 프로젝트	spring07-AOP-solution 에서 AOP를 적용 
 	    
 	    AOP 적용단계 
 	    1.  maven pom.xml : aspectjweaver 추가 => 의존 라이브러리 확보 
 	    2.  cross cutting concern 공통 관심사 로직 정의 클래스 
 	    3.  AOP 설정 : AppConfig의 Annotation or xml 에 AOP 를 설정한다 
 	   
 	    공통적이고 반복적인 작업을 피할 수 있음 => 생산성 증대 
 	    이후 공통관심사 업무가 업데이트 되어도 core 측에 별도 작업없이 
 	    AOP 영역만 업데이트하면 됨 => 높은 유지보수성 
 	    신규 서비스가 추가되어도 별도 작업은 없다 => 확장성이 좋다 
 	    
 */
public class TestAOP {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService ms=(MemberService) ctx.getBean("memberServiceImpl");
		// Spring Container에서 반환해준 객체는 MemberServiceImpl 객체가 아니라 
		// Proxy (대리인) 객체이다 
		System.out.println(ms.getClass().getName());//com.sun.proxy.$Proxy19
		// IOC 적용 ( Spring Container로부터 확보한 객체 )
		//ProductService ps=(ProductService)ctx.getBean("productServiceImpl");
		// IOC 적용 X  객체 
		ProductService ps=new ProductServiceImpl();// AOP 적용 X : AOP 는 IOC를 전제로 한다 
		ms.findMemberByAddress();
		ms.findMemberById();
		ms.findMemberList();
		ms.register();
		ps.findProductById();
		ps.findProductByName();
		ps.findProductListByMaker();
		ps.updateProduct();
		System.out.println("**신규 NoticeService 추가**");
		NoticeService ns=(NoticeService)ctx.getBean("noticeServiceImpl");
		ns.findNotice();
		ns.findNoticeList();		
		ctx.close();
	}
}
















