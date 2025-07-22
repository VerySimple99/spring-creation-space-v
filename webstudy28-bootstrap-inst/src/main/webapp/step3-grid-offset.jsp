<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bootstrap grid offset</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container pt-3">
<div class="row">
	<div class="col-sm-5 bg-success text-light">col sm 5</div>
	<%-- offset-sm-2 를 명시하면 오른쪽으로 2열만큼 이동해 시작 --%>
	<div class="col-sm-5  offset-sm-2  bg-danger text-light">col sm 5</div>
</div>
<%-- grid 총 12중 3만큼 건너뛰어 9 열 영역을 사용 bg-success --%>
<div class="row">
	<div class="col-sm-9 offset-sm-3  bg-success text-light">col sm 9</div>
</div>
</div>
</body>
</html>

















