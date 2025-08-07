/**
 Ajax 테스트 (Fetch API 버전 - 이벤트 바인딩)
 
 자바스크립트의 표준 비동기 통신 API인 Fetch를 사용합니다.
 async/await 문법을 통해 비동기 코드를 동기 코드처럼 순차적으로 읽고 처리할 수 있습니다.
이벤트 핸들러 함수 앞에 'async' 키워드를 붙여서,
이 함수 내에서 'await' 키워드를 사용할 수 있도록 합니다.
async 함수는 항상 Promise를 반환합니다

비동기 웹 개발의 두 가지 핵심 개념
fetch와 async/await

fetch: 네트워크 요청을 보내는 기능
async/await: 그 요청의 응답을 다루는 방법

fetch	
네트워크 요청 시작	함수 호출 시 즉시 Promise 반환, 메인 스레드를 막지 않음

Promise	
비동기 작업의 결과(미래의 값)를 담는 객체	대기', '성공', '실패' 상태를 가짐

async	
await 사용 가능 함수 선언	함수 전체가 Promise를 반환하며, await를 만나면 일시 중지됨

await	
Promise의 완료를 기다림	
해당 async 함수만 일시 중지, 메인 스레드는 계속 다른 작업 수행       
.
 */
// 계좌번호로 단일 계좌 조회 
async function getAccountByNumber() {
	const accountNumber = document.getElementById('accountNumber').value; // 계좌 번호
	//        const resultDiv = document.getElementById('result'); // ajax 응답 결과             

	// Falsy :  Boolean 문맥에서 false로 평가되는 값입니다.
	if (!accountNumber) {
		// alert('계좌번호를 입력해주세요.');
		showError('계좌번호를 입력하셔야 조회할 수 있어요')
		return;
	}

	try {
		// REST API 호출 - Path Variable 방식
		// 1단계: HTTP 요청 및 응답 헤더만 받음
		const response = await fetch(`/api/accounts/${accountNumber}`);

		// 응답 상태 확인
		if (!response.ok) {
			//Spring Boot 에러 응답에서 message만 추출
			// 2단계: 응답 본문(body)을 실제로 읽어옴
			const errorData = await response.json();
			throw new Error(errorData.message);
		}

		// 2단계: 응답 본문(body)을 실제로 읽어옴 , 또 await 가 필요:  response에는 status, ok 등만 있고 JSON 데이터는 아직 없음!
		const account = await response.json();//response.json()은 JSON 텍스트를 JavaScript 객체로 파싱                
		// 결과 표시
		showSuccess(`
                    <p><strong>계좌번호:</strong> ${account.accountNumber}</p>
                    <p><strong>계좌유형:</strong> ${account.accountType}</p>
                    <p><strong>잔액:</strong> ${account.balance.toLocaleString()}원</p>   
                   <p><strong>계좌주명:</strong> ${account.customer.name}</p>
                `);
	} catch (error) {
		console.error('Error:', error);
		showError(error.message);
	}
}
// 성공 메시지 표시
function showSuccess(message) {
	const resultDiv = document.getElementById('result');
	resultDiv.innerHTML = message;
}

// 에러 메시지 표시
function showError(message) {
	const resultDiv = document.getElementById('result');
	resultDiv.innerHTML = `<p>${message}</p>`;

}


// 고객ID로 계좌 목록 조회
async function getAccountsByCustomer() {
	const customerId = document.getElementById('customerId').value;
	//  const resultDiv = document.getElementById('result');

	if (!customerId) {
		showError('고객ID를 입력해주세요.');
		return;
	}

	try {
		// REST API 호출 
		// 1단계: HTTP 요청 및 응답 헤더만 받음
		const response = await fetch(`/api/accounts/customer/${customerId}`);

		// 응답 상태 확인
		if (!response.ok) {
			//Spring Boot 에러 응답에서 message만 추출
			// 2단계: 응답 본문(body)을 실제로 읽어옴
			const errorData = await response.json();
			throw new Error(errorData.message);
		}
		// 2단계: 응답 본문(body)을 실제로 읽어옴
		const accounts = await response.json();

		if (accounts.length === 0) {
			showSuccess('<p>해당 고객의 계좌가 없습니다.</p>');
			return;
		}

		let accountListHtml = `
				    <h3>계좌 목록 (총 ${accounts.length}개)</h3>
				    <table class="table table-bordered">
				        <thead>
				            <tr><th>계좌번호</th><th>계좌유형</th><th>잔액</th><th>개설일</th></tr>
				        </thead>
				        <tbody>
				`;
		accounts.forEach(account => {
			accountListHtml += `
				        <tr>
				            <td>${account.accountNumber}</td>
				            <td>${account.accountType}</td>
				            <td>${account.balance.toLocaleString()}원</td>
				            <td>${new Date(account.createdAt).toLocaleDateString()}</td>
				        </tr>
				    `;
		});
		accountListHtml += `</tbody></table>`;
		showSuccess(accountListHtml);

	} catch (error) {
		console.error('Error:', error);
		showError(` ${error.message}`);
	}
}



