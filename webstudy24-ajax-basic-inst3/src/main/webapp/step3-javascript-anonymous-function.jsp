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
<title>자바스크립트 익명함수</title>
</head>
<body>
<div class="container pt-3">
<script type="text/javascript">
// 일반적인 자바스크립트 함수 
/*
	function hello() {
		alert("hello");
	}
	hello();
*/
	//익명함수를 hello2 변수에 할당 
	let hello2=function(){
		alert("hello2");
	}
	hello2();
</script>
</div>
</body>
</html>









