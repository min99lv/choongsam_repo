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
    
    <h1>마이페이지</h1>

</header>

<nav>
    <ul>
    <h1>학생</h1>
        <li onclick="toggleSubMenu('myClassroomSubmenu')">나의 강의실</li>
        <ul class="submenu" id="myClassroomSubmenu">
            <li style="font-size: 13px;" onclick="location.href='../Jhe/myLecture'">현재 수강 중인 강의</li>
            <li style="font-size: 13px;" onclick="location.href='suganglistStd?user_seq=${user_seq}'">수강신청내역</li>
        </ul>
        <li onclick="toggleSubMenu('gradeManagementSubmenu')">성적 관리</li>
        <ul class="submenu" id="gradeManagementSubmenu">
            <li style="font-size: 13px;">하은</li>
            <li style="font-size: 13px;">하은</li>
            <li style="font-size: 13px;">하은</li>
        </ul>
        <li onclick="toggleSubMenu('userInfoSubmenu')">회원정보</li>
        <ul class="submenu" id="userInfoSubmenu">

            <li style="font-size: 13px;" onclick="location.href='updateProfile?user=${user}'">개인정보 수정</li>
            <li style="font-size: 13px;" onclick="location.href='changePW?user=${user}'">비밀번호 변경</li>
            <li style="font-size: 13px;" onclick="location.href='deleteStd?user=${user}'">회원탈퇴</li>
        </ul>
        <li onclick="toggleSubMenu('inquirySubmenu')">1:1 문의</li>
        <ul class="submenu" id="inquirySubmenu">
           <li style="font-size: 13px;" onclick="location.href='/asks/my'">문의 내역</li>
           <li style="font-size: 13px;" onclick="location.href='/asks/new'">문의 작성</li>
        </ul>
      <li onclick="toggleSubMenu('zzji')">쪽지함</li>
        <ul class="submenu" id="zzji">
           <li style="font-size: 13px;" onclick="location.href='/notes/received'">받은쪽지</li>
           <li style="font-size: 13px;" onclick="location.href='/notes/sent'">보낸쪽지</li>
           <li style="font-size: 13px;" onclick="location.href='/asks/my'">휴지통</li>
        </ul>
    </ul>
</nav>



</body>
</html>
