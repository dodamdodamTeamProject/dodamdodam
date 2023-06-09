// function triggerInputFile() {
//     document.getElementById('inputProfileImg').click();
// }

// function previewImage(event) {
//     const input = event.target;
//     if (input.files && input.files[0]) {
//         const reader = new FileReader();
//         reader.onload = function(e) {
//             document.getElementById('image_container').style.backgroundImage = 'url(' + e.target.result + ')';
//         }
//         reader.readAsDataURL(input.files[0]);
//     }
// }


/* 바꾸기,삭제하기 버튼 */
// const defaultImageUrl = 'https://static.wadiz.kr/assets/icon/profile-icon-6.png';

// document.getElementById('btn_updatePhoto').addEventListener('click', () => {
//     document.getElementById('inputProfileImg').click();
// });

// document.getElementById('btn_deletePhoto').addEventListener('click', () => {
//     document.getElementById('image_container').style.backgroundImage = `url(${defaultImageUrl})`;
// });

// function previewImage(event) {
//     const input = event.target;
//     if (input.files && input.files[0]) {
//         const reader = new FileReader();
//         reader.onload = function(e) {
//             document.getElementById('image_container').style.backgroundImage = 'url(' + e.target.result + ')';
//         }
//         reader.readAsDataURL(input.files[0]);
//     }
// }




/* 주소 */
function searchAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }

            } else {
            }

            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addressDetail").focus();
        }
    }).open();
}

$('#ok-button').on('click', function(){
    let flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false;
    // console.log($('#memberName').val().length);
    // console.log($('#memberEmail').val().length);
    // console.log($('#memberPhone').val().length);
    // console.log($('#address').val().length);
    // console.log($('#addressDetail').val().length);

    if($('#memberName').val().length > 0){
        flag1 = true;
    }

    if($('#memberEmail').val().length > 0){
        flag2 = true;
    }

    if($('#memberPhone').val().length > 0){
        flag3 = true;
    }

    if($('#address').val().length > 0){
        flag4 = true;
    }

    if($('#addressDetail').val().length > 0){
        flag5 = true;
    }


    if(flag1 && flag2 && flag3 && flag4 && flag5){
        document.userInfoForm.submit();
    } else if($('#memberName').val().length <= 0){
        $('#memberName').focus();
    } else if($('#memberEmail').val().length <= 0){
        $('#memberEmail').focus();
    } else if($('#memberPhone').val().length <= 0){
        $('#memberPhone').focus();
    } else if($('#address').val().length <= 0){
        $('#address').focus();
    } else if($('#addressDetail').val().length <= 0){
        $('#addressDetail').focus();
    }
    
})

/* 모달창 닫기 */
$('#closeModalBtn').on('click', function () {
    $('.modal').hide();
})
/* 추가 */