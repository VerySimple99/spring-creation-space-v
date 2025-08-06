/**
 * Ajax 테스트 (Fetch API 버전)
 */
async function startAjax() {
    let userIdInput = document.getElementById("userId");
    let userId = userIdInput.value;
    let resultDiv = document.getElementById("result");

    if (userId === "") {
        alert("사용자 아이디를 입력하세요 fetch");
        return;
    }

    // AJAX 요청 시작 전에 로딩 이미지 표시
    resultDiv.innerHTML = '<img src="/images/working.gif">';
    
    try {
        // Fetch API를 사용한 GET 요청
        const response = await fetch(`/test-ajax3?userId=${userId}`);
        
        // 응답 상태가 정상이 아니면 에러를 발생시킴
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        // 서버 응답을 텍스트로 변환
        const data = await response.text();
        
        // 결과 표시
        resultDiv.innerHTML = `ajax 응답 정보 : ${data}`;

    } catch (error) {
        // 에러 발생 시 처리
        console.error("Fetch Error:", error);
        resultDiv.innerHTML = `에러 발생: ${error.message}`;
    }
}