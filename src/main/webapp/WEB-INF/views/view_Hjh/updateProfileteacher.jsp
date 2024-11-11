<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="headerTeacher.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 수정</title>

<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 0;
    }

    .container {
        display: flex;
        justify-content: center;
        align-items: flex-start;
        height: 100vh;
        padding: 20px;
    }

    .user-info {
        display: grid;
        grid-template-columns: 1fr;
        gap: 30px;
        max-width: 900px;
        margin: 40px;
        padding: 30px;
        background-color: #ffffff;
        border-radius: 8px;
        border: 1px solid #ddd;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    /* 프로필 이미지 스타일 */
    .profile-section {
        display: flex;
        flex-direction: row;
        align-items: center;
        gap: 20px;
    }

    .profile-preview {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        margin-bottom: 20px;
        border: 2px solid #ccc;
    }

    .profile-section input[type="file"] {
        margin-top: 10px;
    }

    /* 사용자 정보 폼 스타일 */
    .form-section {
        display: grid;
        grid-template-columns: 1fr; /* 기본 1열로 시작 */
        gap: 20px;
    }

    h2 {
        text-align: center;
        margin-bottom: 30px;
        color: #236147;
    }

    label {
        font-size: 16px;
        color: #333;
        margin-bottom: 5px;
    }

    input[type="text"],
    input[type="email"],
    input[type="date"],
    input[type="file"],
    input[type="button"] {
        width: 100%;
        padding: 10px;
        margin: 10px 0 20px 0;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
    }

    input[type="file"] {
        width: auto;
        display: inline-block;
    }

    button {
        width: 100%;
        padding: 12px;
        background-color: #236147;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #a0d5a7;
    }

    /* 전화번호 입력 박스 스타일 */
    .phone-input {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
    }

    .address-container {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .address-container input {
        width: 85%;
    }

    .address-container button {
        width: 12%;
    }

    /* 프로필과 아이디, 회원번호를 한 줄로 배치 */
    .profile-id-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 20px;
        align-items: center;
    }

    .profile-id-grid .form-group {
        margin: 0;
    }

    /* 이름, 생년월일, 전화번호, 주소, 이메일을 길게 배치 */
    .details-grid {
        display: grid;
        grid-template-columns: 1fr;
        gap: 20px;
    }

    /* 저장 버튼 위치 */
    .form-section button {
        grid-column: span 2;
    }

    /* 아이디와 회원번호를 오른쪽에 배치 */
    .profile-id-grid .form-group {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
    }

</style>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function previewImage(event) {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.getElementById('profilePreview');
            img.src = e.target.result;
            img.style.display = 'block'; // 이미지를 보이게 설정
        }
        if (file) {
            reader.readAsDataURL(file);
        }
    }

    function formatPhoneNumber(input) {
        let value = input.value.replace(/[^0-9]/g, "");  // 숫자만 남기기
        if (value.length < 4) {
            input.value = value;  // 3자리까지만 입력
        } else if (value.length < 7) {
            input.value = value.slice(0, 3) + "-" + value.slice(3);  // 3-4자리
        } else if (value.length < 11) {
            input.value = value.slice(0, 3) + "-" + value.slice(3, 7) + "-" + value.slice(7);  // 3-4-4자리
        } else {
            input.value = value.slice(0, 3) + "-" + value.slice(3, 7) + "-" + value.slice(7, 11);  // 3-4-4자리까지
        }
    }

    function findAddr() {
        new daum.Postcode({
            oncomplete: function (data) {
                var roadAddr = data.roadAddress;
                document.getElementById('address').value = roadAddr;
                document.getElementById('address').focus();
            }
        }).open();
    }
</script>

</head>
<body>


<div class="container">
    <div class="user-info">
    <form action="updateUserInfo1" method="post" enctype="multipart/form-data">
    			    <input type="hidden" value="${profile_addr}" name="profile_addr">
			    <input type="hidden" value="${profile_name}" name="profile_name">
        <!-- 프로필 이미지와 아이디, 회원번호 (위 그리드) -->
		<div class="profile-id-grid">
		    <div class="profile-section">
		        <!-- 프로필 이미지를 미리보기로 표시하는 이미지 요소 -->
		        <img id="profilePreview" src="${profile_addr}" alt="${profile_name}" class="profile-preview" style="display: block;">
		        
		        <!-- 파일 업로드를 위한 input 태그 -->
		        <input type="file" id="profileImage" name="profileImage" accept="image/*" onchange="previewImage(event)">
		    </div>
            <!-- 회원번호와 아이디 -->
            <div class="form-group">
                <label for="userSeq">회원번호:</label>
                <input type="text" id="userSeq" name="user_seq" value="${user_seq}" readonly>
            </div>

            <div class="form-group">
                <label for="userId">아이디:</label>
                <input type="text" id="userId" name="user_id" value="${user_id}" readonly>
            </div>
        </div>

        <!-- 이름, 생년월일, 전화번호, 주소, 이메일 (아래 그리드) -->
        <div class="details-grid">
            <div class="form-group">
                <label for="userName">이름:</label>
                <input type="text" id="userName" name="user_name" value="${user_name}" required>
            </div>

            <div class="form-group">
                <label for="birthDate">생년월일:</label>
                <input type="date" id="birthDate" name="birth" value="${birth}" required>
            </div>

            <div class="form-group">
                <label for="phone">전화번호:</label>
                <input type="text" id="phone" name="phone_num" value="${phone_num}" oninput="formatPhoneNumber(this)" placeholder="010-xxxx-xxxx" required>
            </div>
            <div class="form-group address-container">
                <label for="address">주소:</label>
                
                <input type="text" id="address" name="address" value="${address}" placeholder="주소 입력" required>
                <button type="button" onclick="findAddr()">주소 찾기</button>
            </div>

            <div class="form-group">
                <label for="email">이메일:</label>
                <input type="email" id="email" name="email" value="${email}" required>
            </div>
        </div>

        <!-- 저장 버튼 -->
        <button type="submit">저장</button>
        </form>
    </div>
    
</div>

</body>
</html>
