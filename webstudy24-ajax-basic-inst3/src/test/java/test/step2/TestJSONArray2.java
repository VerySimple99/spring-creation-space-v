package test.step2;

import java.util.ArrayList;

import org.json.JSONArray;

import model.CarVO;

public class TestJSONArray2 {
	public static void main(String[] args) {
		ArrayList<CarVO> list=new ArrayList<CarVO>();
		list.add(new CarVO("소나타","현대",2000));
		list.add(new CarVO("K5","기아",3000));
		list.add(new CarVO("SM6","르노",2000));
		JSONArray jsonArray=new JSONArray(list);
		System.out.println(jsonArray.toString());
		//위를 확인해보면 javascript 배열 요소로  javascript 객체가 표현된다 
		//JSONArray 의 요소로 JSONObject가 저장된 자료구조이다 
	}
}





