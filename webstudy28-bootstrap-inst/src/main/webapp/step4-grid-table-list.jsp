<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>step4-grid-table</title>
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
<%-- main (게시판) 영역 --%>
<div class="row">
	<div class="col-sm-8 offset-sm-2">
		<table class="table table-boarderd table-hover boardlist">
			<thead>
				<tr style="background-color: #ccffee">
					<th>번호</th>
					<th class="title">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>불금</td>
					<td>이강인</td>
					<td>2023.6.16</td>
					<td>0</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

</div>
</body>
</html>

















