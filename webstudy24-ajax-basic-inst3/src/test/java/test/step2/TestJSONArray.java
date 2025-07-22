package test.step2;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestJSONArray {
	public static void main(String[] args) {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("nick", "이강인");
		jsonObject.put("address", "마드리드");
		System.out.println(jsonObject.toString());
		JSONArray jsonArray=new JSONArray();
		jsonArray.put("카스");
		jsonArray.put("테라");
		jsonArray.put("켈리");
		System.out.println(jsonArray.toString());
	}
}





