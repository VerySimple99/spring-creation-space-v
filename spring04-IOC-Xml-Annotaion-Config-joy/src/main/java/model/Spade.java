package model;

import org.springframework.stereotype.Component;

@Component
public class Spade implements Tool{
	public void work() {
		System.out.println("삽 도구로 일하다");
	}
}
