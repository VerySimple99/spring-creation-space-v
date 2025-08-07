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

document.addEventListener("DOMContentLoaded", function() {
    // HTML 문서가 완전히 로드되고 파싱되었을 때 이 코드가 실행됩니다.
    // 이는 JavaScript가 존재하지 않는 HTML 요소를 참조하려 시도하는 것을 방지하는 안전한 방법입니다.
    
    const fetchButton = document.getElementById("fetchButton");

    if (fetchButton) {
        // 'fetchButton' 이라는 ID를 가진 요소에 클릭 이벤트 리스너를 추가합니다.
        // 이벤트 핸들러 함수 앞에 'async' 키워드를 붙여서,
        // 이 함수 내에서 'await' 키워드를 사용할 수 있도록 합니다.
        // async 함수는 항상 Promise를 반환합니다.
        fetchButton.addEventListener("click", async function() {
            let userIdInput = document.getElementById("userId");
            let userId = userIdInput.value;
            let resultDiv = document.getElementById("result");

            if (userId === "") {
                alert("사용자 아이디를 입력하세요");
                return;
            }

            // AJAX 요청 시작 전에 로딩 이미지를 표시합니다.
            resultDiv.innerHTML = '<img src="/images/working.gif">';
            
            // try...catch 블록을 사용하여 비동기 작업 중 발생할 수 있는 에러를 처리합니다.
            // fetch() 호출 중 네트워크 문제, 서버 응답 오류 등이 발생하면 catch 블록으로 이동합니다.
            try {
                // Fetch API를 사용한 GET 요청을 시작합니다.
                // 'await' 키워드는 Promise가 완료(서버로부터 응답이 도착)될 때까지
                // 이 함수의 실행을 일시 중지시킵니다.
                // 응답이 도착하면, response 변수에 Response 객체가 할당됩니다.
                const response = await fetch(`/test-ajax3?userId=${userId}`);
                
                // response.ok는 응답 상태 코드가 200-299 범위에 있는지 확인합니다.
                // 이 조건으로 서버가 정상 응답을 했는지 쉽게 체크할 수 있습니다.
                if (!response.ok) {
                    // 응답이 정상이 아니면 Error를 발생시켜 catch 블록으로 보냅니다.
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                
                // 응답 본문(body)을 텍스트로 읽어들입니다.
                // 이 과정도 비동기적이므로 'await'를 사용하여 데이터가 모두 읽힐 때까지 기다립니다.
                const data = await response.text();
                
                // 최종 결과를 화면에 표시합니다.
                resultDiv.innerHTML = `ajax 응답 정보 : ${data}`;

            } catch (error) {
                // try 블록에서 에러가 발생하면 이 부분이 실행됩니다.
                console.error("Fetch Error:", error);
                resultDiv.innerHTML = `에러 발생: ${error.message}`;
            }
        });
    }
});
console.log("다른 작업 진행");