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
        color: white; /* 글씨 색상 변경 */
    }
    nav ul {
        list-style-type: none;
        padding: 0;
    }
    nav li {
        margin: 50px 0; /* 항목 간의 간격 */
        cursor: pointer;
        font-size: 20px; /* 상위 메뉴 글자 크기 */
        transition: background-color 0.3s, transform 0.3s; /* 배경색 및 크기 전환 효과 */
        display: flex; /* 플렉스 박스 사용 */
        justify-content: center; /* 수평 중앙 정렬 */
        align-items: center; /* 수직 중앙 정렬 */
        height: 40px; /* 항목 높이 설정 (필요 시 조정) */
    }
    nav li:hover {
        background-color: #a0d5a7; /* 마우스 오버 시 배경색 */
        color: white; /* 글자색 변경 (선택 사항) */
        transform: scale(1.2); /* 마우스 오버 시 크기 확대 */
    }
    nav .submenu {
        display: none;
        padding-left: 25px; /* 하위 메뉴 들여쓰기 */
        margin-top: 0px; /* 하위 메뉴와 상위 메뉴 간격 */
        border-left: 2px solid #236147; /* 계층 구조 표시 */
        padding-left: 10px; /* 하위 메뉴 패딩 */
        color: #fff; /* 하위 메뉴 글자 색상 변경 */
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
        grid-column: span 2;
        padding: 10px;
        background-color: #236147;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    button:hover {
        background-color: #a0d5a7;
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
</head>
<body>

<header>
    <div class="logo"></div>
    <h1>마이페이지</h1>
    <div class="user-info"></div>
</header>

<nav>
    <ul>
        <h1>학생</h1>
        <li onclick="toggleSubMenu('myClassroomSubmenu')">나의 강의실</li>
        <ul class="submenu" id="myClassroomSubmenu">
            <li style="font-size: 13px;">현재 수강 중인 강의</li>
            <li style="font-size: 13px;">수강 신청 내역</li>
        </ul>
        <li onclick="toggleSubMenu('gradeManagementSubmenu')">성적 관리</li>
        <ul class="submenu" id="gradeManagementSubmenu">
            <li style="font-size: 13px;">하은</li>
            <li style="font-size: 13px;">하은</li>
            <li style="font-size: 13px;">하은</li>
        </ul>
        <li onclick="toggleSubMenu('userInfoSubmenu')">회원정보</li>
        <ul class="submenu" id="userInfoSubmenu">
            <li style="font-size: 13px;" onclick="location.href='updateProfile'">개인정보 수정</li>
            <li style="font-size: 13px;">비밀번호 변경</li>
            <li style="font-size: 13px;">회원 탈퇴</li>
        </ul>
        <li onclick="toggleSubMenu('inquirySubmenu')">1:1 문의</li>
        <ul class="submenu" id="inquirySubmenu">
            <li style="font-size: 13px;">문의 내역</li>
            <li style="font-size: 13px;">문의 작성</li>
        </ul>
    </ul>
</nav>

<main>

<form action="updateProfile" method="post" enctype="multipart/form-data">
    <h2>개인정보수정</h2>
    <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
        <!-- 왼쪽: 프로필 사진 -->
        <div class="form-group" style="text-align: center;">
            
            <img id="profilePreview" src="[현재 프로필 사진 경로]" alt="프로필 사진" style="width: 150px; height: 150px; border-radius: 75px; margin-bottom: 10px;">
            <input type="file" id="profileImage" name="profileImage" accept="image/*">
        </div>
        
        <!-- 오른쪽: 정보 수정 -->
        <div class="form-group">
            <label for="userStatus">회원 분류</label>
            <span id="userStatus">[회원 분류 값]</span> <!-- 회원 분류 표시 -->
            <input type="hidden" name="user_status" value="[회원 분류 값]" /> <!-- 서버로 전송 -->
        </div>
        <div class="form-group">
            <label for="userId">아이디</label>
            <span id="userIdDisplay">[아이디 값]</span> <!-- 아이디 표시 -->
            <input type="hidden" name="user_id" value="user_id" /> <!-- 서버로 전송 -->
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
            <input type="text" id="phone" name="phone_num">
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="address">주소</label>
            <input type="text" id="address" name="address">
        </div>
    </div>
    <button type="submit">수정 완료</button>
</form>
</main>

</body>
</html>
