<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bootstrap grid</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container pt-3">
<div style="background-color:green;">div test</div>
<div style="background-color:orange;">div test</div>
<div class="row">
    <div class="col-sm-8" style="background-color:lavender;">.col-sm-8</div>
    <div class="col-sm-4" style="background-color:lavenderblush;">.col-sm-4</div>
 </div> 
 <%-- bootstrap grid 는 12로 구성 : sm 은 576px 이상일때 적용(css media query가 동작됨) --%>
 <div class="row">
 	<div class="col-sm-3 bg-primary">left</div>
 	<div class="col-sm-6 bg-warning">main</div>
 	<div class="col-sm-3 bg-success">right</div>
 </div>
</div>
</body>
</html>






















