<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="css/myhome.css">	
</head>
<body>
<div class="container-fluid pt-3">
<%-- header 영역 --%>
<div class="row header">
	<div class="col-sm-8 offset-sm-2" align="right">
		<c:import url="header.jsp"></c:import>
	</div>
</div>
<%-- main  영역 --%>
<div class="row">
	<div class="col-sm-8 offset-sm-2">
		<!-- 메인영역을 동적으로 import 해옴 ( ex : list.jsp , post-detail.jsp ...  -->
		<c:import url="${requestScope.url}"></c:import>
	</div>
</div>
</div>
</body>
</html>

















