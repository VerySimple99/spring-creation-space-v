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
<title>자동차 리스트 조회</title>
</head>
<body>
<div class="container pt-3">
<form>
	<select id="maker" onchange="findCarListByMaker()">
		<option value="">-제조사-</option>
		<option value="현대">현대</option>
		<option value="기아">기아</option>
		<option value="르노">르노</option>
	</select>
</form>
<br><br><br>
보유 수량 <span id="countView">-</span>대
<br><br><br>
<table class="table table-bordered">
<thead>
	<tr>
		<th>모델</th>
		<th>가격</th>
	</tr>
</thead>
<tbody id="carListView"></tbody>
</table>
<script type="text/javascript">
/*
 	 1. select option 을 선택하면 즉시 ajax 요청(CarJSONArrayServlet.do)해서 아래 보유수량에 
 	 	해당 제조사의 차 보유수량을 제공한다 
 	 2. 자동차 리스트의 model 명을 alert 으로 출력본다  
 	 3. 제조사 선택에 따라 자동차 수와 자동차 모델, 가격 리스트가 제공된다 
 */
	function findCarListByMaker() {
		let maker=document.getElementById("maker");
		if(maker.value==""){
			document.getElementById("countView").innerHTML="-";
			document.getElementById("carListView").innerHTML="";
			return;
		}
		let xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
				let carList=JSON.parse(xhr.responseText);
				document.getElementById("countView").innerHTML=carList.length;
				//let carInfo; // undefined : 초기화 되지 않아 타입이 결정되지 않은 상태 
				//alert(carInfo);
				let carInfo="";
				for(let i=0;i<carList.length;i++){
					//alert(carList[i].model);
					//tr td 정보를 carInfo에 누적  += 
					carInfo+="<tr>";	
					carInfo+="<td>"+carList[i].model+"</td>";
					carInfo+="<td>"+carList[i].price+"</td>";					
					carInfo+="</tr>";	
				}
				//tbody carListView 에 할당한다 
				document.getElementById("carListView").innerHTML=carInfo;
			}
		}
		xhr.open("get", "CarJSONArrayServlet.do?maker="+maker.value);
		xhr.send();
	}
</script>
</div>
</body>



</html>





