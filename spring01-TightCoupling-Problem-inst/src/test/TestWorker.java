package test;

import model.Poclain;

public class TestWorker {
	public static void main(String[] args) {
		// 필요시 객체를 생성해 사용 
		// Hammer 에서 Spade 로 변경 
		//Hammer tool=new Hammer();
		// 필요로 하는 객체(컴포넌트 or Bean)를 직접 생성 ( 직접 제어 ) 
		//Spade tool=new Spade();
		//이후 툴이 추가될때마다 코드 변경은 불가피한 구조 , 결합도가 높다, 유지보수성이 낮다 
		Poclain tool=new Poclain();
		tool.work();
	}
}









