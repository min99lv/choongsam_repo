<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
        body{
            margin: 0;
            padding: 0;
        }

        .header__{
            display: block;
            width: 1920px;
            background-color:white;
            height: 70px;
            font-size: 20px;
            color: black;
            position: static;
        }

        .header__color{
            margin: 0;
            padding: 0;
            background-color: #00664F;
            width: 1920px;
            height: 28px;
        }
        
        .header__nav{
            display: flex;
            justify-content: space-between;;
            align-items: center;
            padding: 0 50px;
            font-weight: bold;
            height: 42px;
            border-bottom: 0.1px solid rgb(186, 186, 186);
        }


        .header__navBar a {
            text-decoration: none;
            color: black;
            width: 100px;
            margin: 0 125px;
        }
        
        .header_login a, .header_join a {
        	text-decoration: none;
            color: black;
        }
       

    </style>
</head>
<body>
    <div class="header__">
        <div class="header__color"></div>
        <div class="header__nav">
        <div class="header__logo"><a href="/notes">로고</a></div>
        <div class="header__navBar">
            <a href="#">수강신청</a>
			<c:choose>
			    <c:when test="${usertype == 1001}">
			        <a href="view_Hjh/myPageStd?user=${user}">마이페이지</a>
			    </c:when>
			    <c:when test="${usertype == 1002}">
			        <a href="view_Hjh/myPageTeacher?user=${user}">마이페이지</a>
			    </c:when>
			    <c:otherwise>
			        <a href="view_Ljm/loginForm">마이페이지</a>
			    </c:otherwise>
			</c:choose>
			
            <a href="/api/notice">공지사항</a>
            <a href="/video-details?videoId=H8BqV91Mhe0&userId=user48">문의사항</a>
        </div>
            <div class="header_login"><a href="/view_Ljm/loginForm">로그인</a></div>
            <div class="header_join"><a href="/view_Ljm/signup1">회원가입</a></div>
        </div>
    </div>
</body>
</html>