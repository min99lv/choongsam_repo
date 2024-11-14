<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리 시스템</title>
<style type="text/css">
    /* Base Styling */
    body {
        display: grid;
        grid-template-rows: 1fr auto;
        grid-template-columns: 320px 1fr;
        height: 100vh;
        margin: 0;
    }
    
    /* Navigation Styling */
    nav {
          position: fixed;
		  width: auto;
		  top:0;
		  height: 100vh;
		  z-index: 1;
		  background-color: #304742;
		  overflow:hidden;
		  transition:width .3s ease;
		  cursor:pointer;
		  @media screen and (min-width: 600px) {
		    width: 320px;
		  }
    }
    
    nav ul {
        list-style-type: none;
        padding: 0;
    }
    
    nav li {
        width: 320px;
	    height: 90px;
	    font-size: 30px;
	    color: white;
	    display: flex;
	    align-items: center;
	    border-bottom: solid 2px white;
	    transition: background-color 0.2s ease, transform 0.3s ease, box-shadow 0.3s ease;
    }

    nav li:hover {
        background-color: #00664F;
	    color: white;
	    transform: scale(1.1);
	    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
    }

    /* Submenu Styling */
    .submenu {
        display: none;
        padding-left: 25px;
        border-left: 2px solid #236147;
        color: white;
        margin-top: 5px;
    }

    .submenu li {
        font-size: 20px;
        padding: 10px 0;
        cursor: pointer;
        height: 30px;
        background-color: #00664F;
    }

    /* Header Styling */
    header {
        grid-column: 1 / span 2;
        background-color: #236147;
        color: white;
        padding: 10px;
        text-align: center;
    }

    /* Link Styling */
    a {
        color: white;
        text-decoration: none;
    }
    
    .txt {
    	margin-left: 50px;
    }
</style>




<script>
    function toggleSubMenu(menuId) {
        const submenu = document.getElementById(menuId);
        submenu.style.display = (submenu.style.display === "block") ? "none" : "block";
    }
</script>
</head>
<body>

<!-- <header>
    <h1>마이페이지</h1>
</header> -->

<%-- sideNav.jsp --%>
<c:choose>
<c:when test="${usertype == 1001}">
    <nav>
        <ul>
            <h1>&nbsp;</h1>
            <li onclick="toggleSubMenu('myClassroomSubmenu')"><label class="txt">내 강의실</label></li>
            <ul class="submenu" id="myClassroomSubmenu">
                <li onclick="location.href='../view_Hjh/suganglistStd?user_seq=${user_seq}'"><label class="txt">수강신청내역</li>
                <li onclick="location.href='../Jhe/myLecture'"><label class="txt">현재 수업중인 강의</label></li>
            </ul>
            <li onclick="toggleSubMenu('userInfoSubmenu')"><label class="txt">회원정보</label></li>
            <ul class="submenu" id="userInfoSubmenu">
           		 <li onclick="location.href='../view_Hjh/updateProfile?user=${user}'"><label class="txt">개인정보 수정</li>
           		 <li onclick="location.href='../view_Hjh/changePW?user=${user}'"><label class="txt">비밀번호 변경</li>
           		 <li onclick="location.href='../view_Hjh/deleteStd?user=${user}'"><label class="txt">회원탈퇴</li>
            </ul>
            <li onclick="toggleSubMenu('inquirySubmenu')"><label class="txt">1:1문의</label></li>
            <ul class="submenu" id="inquirySubmenu">
                <li onclick="location.href='/asks/my'"><label class="txt">문의내역</label></li>
                <li onclick="location.href='/asks/new'"><label class="txt">문의작성</label></li>
            </ul>
            <li onclick="toggleSubMenu('zzji')"><label class="txt">쪽지함</label></li>
            <ul class="submenu" id="zzji">
                <li onclick="location.href='/notes/received'"><label class="txt">받은쪽지</label></li>
                <li onclick="location.href='/notes/sent'"><label class="txt">보낸쪽지</label></li>
                <li onclick="location.href='/asks/my'"><label class="txt">휴지통</label></li>
            </ul>
        </ul>
    </nav>
    </c:when>
    </c:choose>

<c:choose>
<c:when test="${usertype == 1002}">
    <nav>
        <ul>
            <h1>&nbsp;</h1>
            <li onclick="toggleSubMenu('myClassroomSubmenu')"><label class="txt">나의 강의실</label></li>
            <ul class="submenu" id="myClassroomSubmenu">
                <li onclick="location.href='../Jhe/myLecture'"><label class="txt">현재 수업중인 강의</label></li>
                <li onclick="location.href='../view_Hjh/gangyilistTeacher?user_seq=${user_seq}'"><label class="txt">강의 목록</label></li>
                <li onclick="location.href='/registerCourseForm'"><label class="txt">강의 등록</label></li>
            </ul>
            <li onclick="toggleSubMenu('userInfoSubmenu')"><label class="txt">회원정보</label></li>
            <ul class="submenu" id="userInfoSubmenu">
                <li onclick="location.href='../view_Hjh/updateProfileteacher?user=${user}'"><label class="txt">개인정보 수정</label></li>
                <li onclick="location.href='../view_Hjh/changePW?user=${user}'"><label class="txt">비밀번호 변경</li>
                <li onclick="location.href='../view_Hjh/deleteTeacher?user=${user}'"><label class="txt">회원탈퇴</label></li>
            </ul>
            <li onclick="toggleSubMenu('inquirySubmenu')"><label class="txt">1:1문의</label></li>
            <ul class="submenu" id="inquirySubmenu">
                <li onclick="location.href='/asks/my'"><label class="txt">문의내역</label></li>
                <li onclick="location.href='/asks/new'"><label class="txt">문의작성</label></li>
            </ul>
            <li onclick="toggleSubMenu('zzji')"><label class="txt">쪽지함</label></li>
            <ul class="submenu" id="zzji">
                <li onclick="location.href='/notes/received'"><label class="txt">받은쪽지</label></li>
                <li onclick="location.href='/notes/sent'"><label class="txt">보낸쪽지</label></li>
                <li onclick="location.href='/asks/my'"><label class="txt">휴지통</label></li>
            </ul>
        </ul>
    </nav>
</c:when>
</c:choose>
<c:choose>
<c:when test="${usertype == 1003}">
    <nav>
        <ul>
            <h1>&nbsp;</h1>
            <li onclick="toggleSubMenu('myClassroomSubmenu')"><label class="txt">회원관리</label></li>
            <ul class="submenu" id="myClassroomSubmenu">
                 <li onclick="location.href='../view_Hjh/updateProfileAdmin'">회원정보수정</li>
             </ul>
            <li onclick="toggleSubMenu('gradeManagementSubmenu')"><label class="txt">강의</label></li>
            <ul class="submenu" id="gradeManagementSubmenu">
           		  <li onclick="location.href='/courseApproveList'">강의 승인</li>
          		  <li>강의 목록</li>
            </ul>
            
            
            
            <li onclick="toggleSubMenu('inquirySubmenu')"><label class="txt">문의사항 관리</label></li>
        	    <ul class="submenu" id="inquirySubmenu">
                <li onclick="location.href='/asks/new'"><label class="txt">문의작성</label></li>
                <li onclick="location.href='/asks/my'"><label class="txt">문의내역</label></li>
            </ul>
            
            
            <li onclick="toggleSubMenu('inquiSubmenu')"><label class="txt">공지사항 관리</label></li>
        	    <ul class="submenu" id="inquiSubmenu">
                <li onclick="location.href='/api/notice/new'"><label class="txt">공지사항 작성</label></li>
                <li onclick="location.href='/api/notice'"><label class="txt">공지사항 목록</label></li>
            </ul>

        </ul>
    </nav>
</c:when>
</c:choose>




</body>
</html>
