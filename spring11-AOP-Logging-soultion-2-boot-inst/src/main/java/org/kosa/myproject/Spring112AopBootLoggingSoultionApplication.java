package org.kosa.myproject;

import org.kosa.myproject.model.MemberService;
import org.kosa.myproject.model.NoticeService;
import org.kosa.myproject.model.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
요구사항 
현 시스템에서 서비스 중인  회원 서비스 , 상품 서비스  등을 대상으로
Service 인터페이스 또는 클래스로 끝나는  접근 제어자 및 리턴타입 관계없이 
find로 시작되는 메서드를 대상으로  
검색 기능에 대한 검색어를 특정 파일( report.log ) 에  기록 ( 대상 클래스명 메서드명 검색일시 검색어 )
이 파일은 연월일별로 백업이 되어야 한다 

1안 )  각 Core에 IO 프로그램 로직을 직접 삽입 
2안 )  Logging Framework or Library ( Log4j , Logback .. ) 를 이용해 구현 
		  spring08 에서 Logging Unit Test : logback , slf4j 
		  
		  현 프로젝트 spring 09 => Logging Framework 을 이용해 각 Core 에 로깅을 적용 
		  
		  2안과 AOP 를 적용한 3안과 비교 
		  
3안 ) 2안 + AOP 를 적용해 구현 	  	
       spring 10 => AOP 적용 Logging 
*/
@SpringBootApplication
public class Spring112AopBootLoggingSoultionApplication implements CommandLineRunner {
	private final MemberService ms;
	private final ProductService ps;
	private final NoticeService ns;

	public Spring112AopBootLoggingSoultionApplication(MemberService ms, ProductService ps,NoticeService ns) {
		super();
		this.ms = ms;
		this.ps = ps;
		this.ns = ns;
	}

	public static void main(String[] args) {
		SpringApplication.run(Spring112AopBootLoggingSoultionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// AOP 적용 전에는 실제 Core 객체가 스프링 컨테이너로부터 전달된다
		System.out.println(ms.getClass().getName());
		System.out.println(ps.getClass().getName());
		ms.findMemberById("java");
		ms.findMemberListByAddress("오리");
		ms.register("손흥민 토트넘");
		ps.findProductById("1");
		ps.findProductListByMaker("오뚜기");
		ps.findProductListByPriceAndMaker(1000, "롯데");
		ps.deleteProduct("2");	
		ns.findNotice("불금");
		ns.findNoticeList("회식", "최상");
	}

}
