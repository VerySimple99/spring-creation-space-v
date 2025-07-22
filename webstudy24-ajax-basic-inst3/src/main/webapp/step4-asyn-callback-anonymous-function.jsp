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
<title>step2-asyn.jsp</title>
</head>
<body>
<div class="container pt-3">
<form>
	<input type="text" id="userId" required="required" placeholder="고객아이디">
	<button type="button" onclick="startAjax()">비동기테스트</button>
	<%-- ajax 응답결과를 아래 span에 보여줄 예정 --%>
	<span id="result"></span>
</form>
<br><br>
<textarea rows="25" cols="50" placeholder="본문내용"></textarea>
<script type="text/javascript">
 	let xhr;
 	function startAjax() {
		xhr=new XMLHttpRequest(); //Javascript 통신을 위한 객체 ( Ajax 통신 담당 객체 )
 		//console.log("xhr :"+xhr);
		//서버에서 응답하면 동작될 함수를 지정 : 익명함수(anonymous function) 방식으로 구현 
		xhr.onreadystatechange=function(){//callback 익명함수 : 서버에서 응답완료되고 정상 수행되면 실행될 함수  
			// 4 : 응답완료  , 200 : 정상수행 
			if(xhr.readyState==4&&xhr.status==200){
				//alert("ajax 응답 ");
				//서버가 응답한 데이터 : xhr.responseText
				document.getElementById("result").innerHTML=xhr.responseText;
			}else if(xhr.readyState<4){ // 응답완료전까지는 움직이는 아이콘을 제공 
				// 0~3 요청 진행중인 상태 (응답완료전 상태) 
				document.getElementById("result").innerHTML="<img src=working.gif>";
			}
		}
		let id=document.getElementById("userId");
		xhr.open("get", "ASynServlet?id="+id.value);
		xhr.send();
	} 	
</script>
</div>
</body>
</html>











