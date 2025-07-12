package org.kosa.myproject.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
@Service
public class WorkerService {
	//field injection 도 가능 ( field 는 변수 상수를 포함 ) 
	//@Autowired
	//@Qualifier("poclain")
	private final Tool tool;// 추상에 의존해야 함 
	//@Autowired // 타입에 맞는 bean(spring 관리 객체)을 IOC 컨테이너가 주입한다 DI
	//@Qualifier("poclain") //: 동일한 타입의 bean이 여러개 존재시 특정 bean을 지정해 DI 	
	public WorkerService(@Qualifier("poclain")   Tool tool) {
		super();  
		this.tool = tool;
		System.out.println(getClass().getName()+" 객체 생성, tool DI 주입받음 "+tool);
	}
	/**/
	//setter 도 di 가능
	/*
	@Autowired
	@Qualifier("poclain")
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	*/
	//@PostConstruct
	// 빈 초기화 시점
    @EventListener(ContextRefreshedEvent.class)
	public void init() {
		System.out.println(getClass().getName()+" init 초기화 작업");
	}
	
	
	public void service() {
		System.out.println("일꾼이 서비스를 시작하다");
		//Hammer or Spade or Poclain 을 이용해 작업한다 
		tool.work();
		System.out.println("일꾼이 서비스를 마무리하다");
	}
	//@PreDestroy
	// 빈 소멸 시점
    @EventListener(ContextClosedEvent.class)
	public void destroy() {
		System.out.println(getClass().getName()+" destroy 소멸직전 작업");
	}
}


