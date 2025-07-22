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
<link rel="stylesheet" href="css/myhome.css">
<title>상세글보기</title>
</head>
<body>
<div class="container-fluid pt-3">
<%-- header 영역 --%>
<div class="row header">
	<div class="col-sm-8 offset-sm-2" align="right">
		<c:import url="header.jsp"></c:import>
	</div>
</div>
<%-- main (게시판 상세글보기) 영역 --%>
<div class="row">
	<div class="col-sm-8 offset-sm-2">
		<table class="table">			
		<tr>
			<td>글번호 </td><%-- db에서 조회한 글번호 --%>	
			<td>제목 </td>
			<td>작성자 </td>
			<td>조회수</td>
			<td><%-- 작성일시 --%></td>	
		</tr>
		<tr>
			<%-- 
			  pre tag : db에 저장된 글형식 그대로 표현( 줄바꿈 등이 표현됨 ) 
				         pre tag 라인인 행변경없이 한라인으로 표현해야 함   
			 --%>
			<td colspan="5">
				<pre><font size="4">본문내용<%-- 게시글 본문내용 --%></font></pre>
			</td>
		</tr>
		</table>
	</div>
</div>
</div>
</body>
</html>






