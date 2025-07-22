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
<title>step9-ajax-JSONArray</title>
</head>
<body>
<div class="container pt-3">
<button type="button" class="btn btn-primary" onclick="testJSONArray()">JsonArrayTest</button>
<script type="text/javascript">
	function testJSONArray() {
		let xhr=new XMLHttpRequest();
		//callback
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				//alert(xhr.responseText);
				let menuList=JSON.parse(xhr.responseText);
				for(let i=0;i<menuList.length;i++){
					alert(menuList[i]);
				}
			}
		}
		xhr.open("get", "JSONArrayTestServlet.do");
		xhr.send();
	}
</script>
</div>
</body>
</html>





