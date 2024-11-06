<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="headerStd.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리 시스템</title>

<style>
 

    .user-info {
        max-width: 600px;
        margin: 0 auto;
        display: flex;
        flex-direction: column;
        align-items: center;
        border: 1px dashed #ccc;
        padding: 20px;
    }
    .user-info label {
        margin-top: 10px;
    }
    .user-info input {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
    }
    .save-button {
        margin-top: 20px;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        border: none;
        cursor: pointer;
    }
    .profile-preview {
        width: 100px;
        height: auto;
        border-radius: 5px;
        margin-bottom: 10px;
    }
</style>
<script>
    function toggleSubMenu(menuId) {
        const submenu = document.getElementById(menuId);
        if (submenu.style.display === "block") {
            submenu.style.display = "none";
        } else {
            submenu.style.display = "block";
        }
    }
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function findAddr() {
        new daum.Postcode({
            oncomplete: function (data) {
                var roadAddr = data.roadAddress;
                document.getElementById('address').value = roadAddr;
                document.getElementById('address').focus();
            }
        }).open();
    }

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
</script>
</head>
<body>






<div class="user-info">
    <h2>사용자 정보 수정</h2>
    <form action="updateUserInfo" method="post" enctype="multipart/form-data">
		<div>
		    <label for="profileImage">프로필 사진:</label>
		    
		    <!-- 기존 이미지 미리보기 -->
		    <img id="profilePreview" src="${profile_addr}" alt="${profile_name}" class="profile-preview" style="display: block;">
		    
		    <!-- 파일 업로드 -->
		    <input type="file" id="profileImage" name="profileImage" accept="image/*" onchange="previewImage(event)">
		</div>
        <div>
            <label for="userSeq">회원번호:</label>
            <input type="text" id="userSeq" name="user_seq" value="${user_seq}" readonly>
        </div>
        <div>
            <label for="userId">아이디:</label>
            <input type="text" id="userId" name="user_id" value="${user_id}" readonly>
        </div>
        <div>
            <label for="userName">이름:</label>
            <input type="text" id="userName" name="user_name" value="${user_name}" required>
        </div>
        <div>
            <label for="birthDate">생년월일:</label>
            <input type="date" id="birthDate" name="birth" value="${birth}" required>
        </div>

        <div>
            <label for="address">주소</label>
            <input type="text" id="address" name="address" value="${address}" placeholder="주소 입력" required>
            <button type="button" onclick="findAddr()">주소 찾기</button>
        </div>
        <div>
            <label for="email">이메일:</label>
            <input type="email" id="email" name="email" value="${email}" required>
        </div>
        <div>
            <label for="phone">전화번호:</label>
            <input type="text" id="phone" name="phone_num" value="${phone_num}" oninput="formatPhoneNumber(this)" placeholder="010-xxxx-xxxx" required>
        </div>
        <button type="submit" class="save-button">저장</button>
    </form>
</div>

</body>
</html>
