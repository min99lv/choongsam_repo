<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="headerStd.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<body>




<nav>
    <ul>
    <h1>${user_name}학생</h1>
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





</body>
</html>
