<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>ajax json member</title>
</head>
<body>
<div class="container pt-3">
<button type="button" onclick="testJson()">json-test</button>
<script type="text/javascript">
	function testJson(){
		let jsonObject={"maker":"현대","name":"소나타","price":3000};
		alert(jsonObject.maker);
		alert(jsonObject.price);
	}
</script>
<br><br>
<hr>
<select id="memberId">
	<option value="">-회원아이디-</option>
	<option value="java">java</option>
	<option value="spring">spring</option>
	<option value="ajax">ajax</option>
</select>
<button type="button" onclick="findMember()">검색</button>
<br><br><br><br>
<%--  아래와 같이 응답받은 정보를 분리해서 표현해야 한다 => 구조화된 자료형식 필요 : json 을 이용  --%>
회원명 <span id="memberName"></span>
<br><br><br><br>
회원주소 <span id="memberAddress"></span>
<%-- 그 외 여러 회원 정보가 있다고 가정한다  --%>
<script type="text/javascript">
	function findMember() {
		//memberId 요소의 value가 공란이면 아이디를 선택하세요! alert 
		let memberId=document.getElementById("memberId");
		if(memberId.value==""){
			alert("아이디를 선택하세요!");
			return;
		}
		//ajax 통신을 위한 자바스크립트 객체 
		let xhr=new XMLHttpRequest();
		// callback 함수 등록 ( callback function : ajax 응답 완료시 동작될 함수 )
		xhr.onreadystatechange=function(){
			//응답완료 4 , 정상수행 200
			if(xhr.readyState==4&&xhr.status==200){
				//alert(xhr.responseText);
				//JSON.parse() : 응답받은 JSON 형식의 텍스트를 자바스크립트 객체로 변환 
				let memberJson=JSON.parse(xhr.responseText);
				// span 에 알맞게 회원명과 주소를 할당해서 제공한다 
				document.getElementById("memberName").innerHTML=memberJson.name;
				document.getElementById("memberAddress").innerHTML=memberJson.address;
			}
		}
		//url 에 query string 방식으로 데이터를 전송한다 
		xhr.open("get", "MemberJSONServlet.do?memberId="+memberId.value);
		xhr.send();
	}
</script>
</div>
</body>
</html>
























