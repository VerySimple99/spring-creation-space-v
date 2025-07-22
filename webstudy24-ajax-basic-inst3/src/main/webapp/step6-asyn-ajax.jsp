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
<title>step6-asyn-ajax</title>
</head>
<body>
<div class="container pt-3">
<%-- 비동기  AJAX 통신의 예 : step5와 비교해본다  --%>
<form>
	<input type="text" id="nickName">
	<button type="button" onclick="findMemberInfo()">검색</button>
</form>
<br><br>
<h3 id="result"></h3>
<script type="text/javascript">
	function findMemberInfo() {
		let nickName=document.getElementById("nickName");
		if(nickName.value==""){
			alert("닉네임을 입력하세요!");
			nickName.focus();
			return; //함수 실행을 중단한다 
		}
		
		let xhr=new XMLHttpRequest();//ajax 통신 객체
		// readystate 가 변경될 때 실행될 익명함수 ( callback 함수 ) 를 등록 ( binding )
		xhr.onreadystatechange=function(){
			//xhr.readyState 4 : 응답완료상태 , xhr.status 200 : 정상수행
			if(xhr.readyState==4&&xhr.status==200){
				let resultInfo=xhr.responseText;
				document.getElementById("result").innerHTML=resultInfo;
				nickName.value="";
				nickName.focus();
			}//if
		}//callback
		
		xhr.open("get", "AjaxController.do?nickName="+nickName.value);
		xhr.send();
	}//function 
</script>
</div>
</body>
</html>









