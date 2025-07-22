<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>반응형 웹을 위한 CSS 미디어 쿼리</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
body {
	background-color: olive;
}
/*
        부트스트랩 미디어 쿼리 속성 
    	sm(576이상)  md(768이상)  lg(992이상)
    */
@media ( min-width :576px) {
	body {
		background-color: red;
	}
}
@media ( min-width :768px) {
	body {
		background-color: yellow;
	}
}
@media ( min-width :992px) {
	body {
		background-color: lime;
	}
}
</style>
</head>
<body>
	<div class="container pt-3"></div>
</body>
</html>









