package org.kosa.myproject.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
/*
 * Spring 설정 방식 Annotation : 코드 상에 설정 정보를 기술하는 형식 
 * @Component : Spring Container 에  bean 임을 알려  instance(bean) 생성을 하라고 명시 
 * 					아래 Poclain class가 컴포넌트 or Bean 으로 생성되어 관리되어야 함을 명시 
 */
// 신규 추가된 툴 , 컴포넌트 
@Component
@Primary //@Primary로 기본 구현체 설정, 스프링 IOC 컨테이너가 동일한 타입의 빈(Bean)이 여러 개 존재할 때, 어떤 빈을 우선적으로 주입해야 할지 모호한 경우에 우선순위를 지정해주는 역할
//@Component("tool") // bean name을 직접 명시 
public class Poclain implements Tool{
	public void work() {
		System.out.println("포크레인으로 일하다");
	}
}
