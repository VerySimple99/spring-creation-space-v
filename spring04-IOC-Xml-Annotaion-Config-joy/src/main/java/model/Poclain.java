package model;

import org.springframework.stereotype.Component;
/*
 * Spring 설정 방식 Annotation : 코드 상에 설정 정보를 기술하는 형식 
 * @Component : Spring Container 에  bean 임을 알려  instance(bean) 생성을 하라고 명시 
 * 					아래 Poclain class가 컴포넌트 or Bean 으로 생성되어 관리되어야 함을 명시 
 */
// 신규 추가된 툴 , 컴포넌트 
@Component
//@Component("tool") // bean name을 직접 명시 
public class Poclain implements Tool{
	public void work() {
		System.out.println("포크레인으로 일하다");
	}
}
