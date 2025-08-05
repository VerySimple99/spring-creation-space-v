/**
 * 
 */
/**
 * Ajax 테스트 (jQuery 버전)
 */
function startAjax() {
    let userId = $('#userId').val();

    if (userId === "") {
        alert("사용자 아이디를 입력하세요 jquery");
        return;
    }
    
    // AJAX 요청 시작 전에 로딩 이미지 표시
    $('#result').html('<img src="/images/working.gif">');
    
    $.ajax({
        type: "GET",
        url: "/test-ajax1",
        data: { userId: userId },
        success: function(response) {
            // 통신 성공 시, 서버로부터 받은 응답을 result div에 표시
            $('#result').text("ajax 응답 정보 : " + response);
        },
        error: function(xhr, status, error) {
            // 통신 실패 시, 에러 메시지 표시
            console.error("AJAX Error: " + status, error);
            $('#result').text("에러 발생: " + error);
        },
        complete: function() {
            // 성공/실패 여부와 관계없이 통신 완료 시 실행
            // 여기서는 특별한 동작 없음
        }
    });
}