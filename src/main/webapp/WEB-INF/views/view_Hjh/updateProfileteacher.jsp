<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리 시스템</title>
<style>
    body {
        display: grid;
        grid-template-rows: auto 1fr;
        grid-template-columns: 200px 1fr;
        height: 100vh;
        margin: 0;
    }
    header {
        grid-column: 1 / span 2;
        background-color: #236147;
        color: white;
        padding: 10px;
        text-align: center;
    }
    nav {
        background-color: #7F7F7F;
        padding: 30px;
        color: white;
    }
    nav ul {
        list-style-type: none;
        padding: 0;
    }
    nav li {
        margin: 50px 0;
        cursor: pointer;
        font-size: 20px;
        transition: background-color 0.3s, transform 0.3s;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 40px;
    }
    nav li:hover {
        background-color: #a0d5a7;
        color: white;
        transform: scale(1.2);
    }
    nav .submenu {
        display: none;
        padding-left: 25px;
        margin-top: 0px;
        border-left: 2px solid #236147;
        padding-left: 10px;
        color: #fff;
    }
    main {
        padding: 20px;
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 20px;
    }
    .form-group {
        display: flex;
        flex-direction: column;
    }
    label {
        margin-bottom: 5px;
        font-weight: bold;
    }
    input[type="text"], input[type="email"], input[type="date"], input[type="file"] {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    button {
        padding: 5px 10px; /* 크기 조정 */
        background-color: #236147;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px; /* 폰트 크기 조정 */
    }
    button:hover {
        background-color: #a0d5a7;
    }
    h2 {
        margin-bottom: 60px;
    }
        .dashed-border {
        border: 2px dashed #7F7F7F; /* 점선 테두리 */
        padding: 50px; /* 내부 여백 */
        border-radius: 5px; /* 모서리 둥글게 */
        margin-bottom: 20px; /* 아래쪽 여백 */
    }
        .save-button {
        margin-top: 40px; /* 상단 마진 추가 */
    }
</style>
<script>
    function toggleSubMenu(menuId) {
        const submenu = document.getElementById(menuId);
        submenu.style.display = submenu.style.display === "block" ? "none" : "block";
    }
</script>
<script>
    function formatPhoneNumber(input) {
        let value = input.value.replace(/[^0-9]/g, '');
        if (value.length > 3) {
            value = value.slice(0, 3) + '-' + value.slice(3);
        }
        if (value.length > 8) {
            value = value.slice(0, 8) + '-' + value.slice(8);
        }
        input.value = value;
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
</script>
<script>
    function updateEmail() {
        const emailInput = document.getElementById('email');
        const emailDomain = document.getElementById('emailDomain').value;
        if (emailDomain) {
            emailInput.value = emailInput.value.split('@')[0] + emailDomain;
        }
    }
</script>
</head>
<body>
<header>
    <div class="logo"></div>
    <h1>마이페이지</h1>
    <div class="user-info"></div>
</header>

<nav>
    <ul>
    <h1>강사</h1>
        <li onclick="toggleSubMenu('myClassroomSubmenu')">나의 강의실</li>
        <ul class="submenu" id="myClassroomSubmenu">
            <li style="font-size: 13px;">현재 수업 중인 강의</li>
            <li style="font-size: 13px;">강의 목록</li>
        </ul>
        <li onclick="toggleSubMenu('gradeManagementSubmenu')">성적 관리</li>
        <ul class="submenu" id="gradeManagementSubmenu">
            <li style="font-size: 13px;">하은</li>
            <li style="font-size: 13px;">하은</li>
            <li style="font-size: 13px;">하은</li>
        </ul>
        <li onclick="toggleSubMenu('userInfoSubmenu')">회원정보</li>
        <ul class="submenu" id="userInfoSubmenu">
            <li style="font-size: 13px;" onclick="location.href='updateProfileteacher'">개인정보 수정</li>

            <li style="font-size: 13px;">비밀번호 변경</li>
           <li style="font-size: 13px;" onclick="location.href='deleteUser?user=${user}'">회원 탈퇴</li>
        </ul>
        <li onclick="toggleSubMenu('inquirySubmenu')">1:1 문의</li>
        <ul class="submenu" id="inquirySubmenu">
           <li style="font-size: 13px;">문의 내역</li>
           <li style="font-size: 13px;">문의 작성</li>
        </ul>
      <li onclick="toggleSubMenu('zzji')">쪽지함</li>
        <ul class="submenu" id="zzji">
           <li style="font-size: 13px;">받은쪽지</li>
           <li style="font-size: 13px;">보낸쪽지</li>
           <li style="font-size: 13px;">휴지통</li>
        </ul>
    </ul>
    
</nav>

<main style="display: flex; justify-content: center; align-items: center; height: 100%;">
    <div class="dashed-border"> <!-- 점선 테두리 추가 -->
    <form action="updateProfile" method="post" enctype="multipart/form-data" style="max-width: 600px; width: 100%; display: flex; flex-direction: column; align-items: center;">
        <h2>개인정보수정</h2>
        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px; width: 100%;">
            <div class="form-group" style="text-align: center;">
                <c:choose>
                    <c:when test="${not empty profileImage}">
                        <img id="profilePreview" src="${profileImage}" alt="Profile Picture" style="width: 150px; height: 150px; border-radius: 75px; margin-bottom: 10px; margin-left: 20px; border: 2px solid #ccc;">
                    </c:when>
                    <c:otherwise>
                        <img id="profilePreview" src="upload/face.png" style="width: 200px; height: 200px; border-radius: 75px; margin-bottom: 30px; margin-left: 30px; border: 2px solid #ccc;">
                    </c:otherwise>
                </c:choose>
                <input type="file" id="profileImage" name="profileImage" accept="image/*">
            </div>

			<div class="form-group" style="margin-top: 50px;"> <!-- 상단 마진 추가 -->
			    <label for="userStatus">회원 상태</label>
			    <span id="userStatus" style="margin-bottom: 50px; display: block;">[회원 분류 값]</span>
			    <input type="hidden" name="user_status" value="[회원 분류 값]" />
			    <label for="userId">아이디</label>
			    <span id="userIdDisplay">[아이디 값]</span>
			    <input type="hidden" name="user_id" value="user_id" />
			</div>

            <div class="form-group">
                <label for="userName">성명</label>
                <input type="text" id="userName" name="user_name" required>
            </div>
            <div class="form-group">
                <label for="birthDate">생년월일</label>
                <input type="date" id="birthDate" name="birth" required>
            </div>
            <div class="form-group">
                <label for="phone">전화번호</label>
                <input type="text" id="phone" name="phone_num" oninput="formatPhoneNumber(this)" placeholder="010-xxxx-xxxx">
            </div>
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="address">주소</label>
                <input type="text" id="address" name="address" placeholder="주소 입력" required>
                <button type="button" onclick="findAddr()">주소 찾기</button>
            </div>
        </div>
	<button type="submit" class="save-button">저장</button>
    </form>
</main>

</body>
</html>

                
