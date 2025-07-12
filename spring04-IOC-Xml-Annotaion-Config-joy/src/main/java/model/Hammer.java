package model;

import org.springframework.stereotype.Component;

// 시스템의 주요 컴포넌트 component / bean 
@Component
public class Hammer implements Tool{
	public void work() {
		System.out.println("망치 도구로 일하다");
	}
}
