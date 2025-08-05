/**
 * 
 */
function startAjax() {  
	let xhr = new XMLHttpRequest(); //Javascript 통신을 위한 객체 ( Ajax 통신 담당 객체 )
	//console.log("xhr :"+xhr);
	//서버에서 응답하면 동작될 함수를 지정 : 익명함수(anonymous function) 방식으로 구현 
	/*
	자바스크립트 콜백 함수는 다른 함수의 인수로 전달되어, 해당 함수의 작업이 완료된 후 호출되는 함수를 말합니다. 
	쉽게 말해, "나중에 다시 불러줘" 하고 다른 함수에게 넘겨주는 함수입니다. 
	*/
	xhr.onreadystatechange = function() {//callback 익명함수 : 서버에서 응답완료되고 정상 수행되면 실행될 함수  
		// 4 : 응답완료  , 200 : 정상수행 
		if (xhr.readyState == 4 && xhr.status == 200) {
			//alert("ajax 응답 ");
			//서버가 응답한 데이터 : xhr.responseText
			document.getElementById("result").innerHTML = "ajax 응답 정보 : " + xhr.responseText;
		} else if (xhr.readyState < 4) { // 응답완료전까지는 움직이는 아이콘을 제공
			// 0~3 요청 진행중인 상태 (응답완료전 상태) 
			document.getElementById("result").innerHTML = "<img src=/images/working.gif>";
		}
	}
	let userId = document.getElementById("userId");
	if(userId.value===""){
		alert("사용자 아이디를 입력하세요");
		return; 
	}
	xhr.open("get", "/test-ajax1?userId=" + userId.value);
	xhr.send();
}