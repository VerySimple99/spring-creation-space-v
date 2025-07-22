package test.step1;

import org.json.JSONObject;

import model.MemberVO;

public class TestJSONObject {
	public static void main(String[] args) {
		JSONObject json=new JSONObject();
		json.put("id", "javaking");
		json.put("name", "아이유");
		System.out.println(json.toString());
		MemberVO vo=new MemberVO("angel","이강인","토트넘");
		JSONObject json2=new JSONObject(vo);
		System.out.println(json2.toString());
	}
}







