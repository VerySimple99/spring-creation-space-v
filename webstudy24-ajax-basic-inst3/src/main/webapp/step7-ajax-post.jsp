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
<title>ajax post방식</title>
</head>
<body>
<div class="container pt-3">
<form>
	<input type="email" id="email" placeholder="고객이메일">
	<input type="text" id="message" placeholder="고객메세지">
		<button type="button" onclick="register()">등록</button>
</form>
<script type="text/javascript">
	function register() {
		let email=document.getElementById("email");
		let message=document.getElementById("message");
		if(email.value==""){
			alert("이메일을 입력하세요");
			email.focus();			
		}else if(message.value==""){
			alert("메세지를 입력하세요");
			message.focus();			
		}else{ // ajax 요청시작 
			let xhr=new XMLHttpRequest();
			//callback anonymous function 익명함수 binding 등록 
			xhr.onreadystatechange=function(){
				// 응답완료 4 되고  정상수행 200 일때 
				if(xhr.readyState==4&&xhr.status==200){
					alert(xhr.responseText);//responseText : 응답정보 
					message.value=""; //메세지 입력란을 비워준다 
					message.focus();
				}//if
			}//callback			
			xhr.open("post", "RegisterController.do");
			//ajax post 방식일 때 content type을 지정해야 한다. 
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			//post 방식일때에는 send 함수에 정보를 전송한다 
			//정보 전송은 query string 형식으로 해야 한다
			// ex)  id=java&name=아이유&address=판교
			xhr.send("message="+message.value+"&email="+email.value);
		}
	}//function
</script>
</div>
</body>
</html>

















