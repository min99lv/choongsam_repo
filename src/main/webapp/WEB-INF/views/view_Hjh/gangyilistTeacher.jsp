<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="headerTeacher.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의 목록</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- CSS 스타일 파일 -->
</head>
<body>
<main>
<!-- 강의 리스트 테이블 -->
<table border="1" cellpadding="10" cellspacing="0">
    <thead>
        <tr>
            <th>강의번호</th>
            <th>강의명</th>
            <th>강의구분</th>
            <th>대면 여부</th>
            <th>모집인원</th>
            <th>수강료</th>
            <th>개강일</th>
            <th>상태</th>
        </tr>
    </thead>
    <tbody>
        <!-- 강의 목록을 출력 -->
        <c:forEach var="lecture" items="${lectureList}">
            <tr>
                <td>${lecture.lctr_id}</td>
                <td>${lecture.lctr_name}</td>
                <td>${lecture.lctr_category}</td>
                <td>${lecture.onoff}</td>
                <td>${lecture.lctr_count}</td>
                <td>${lecture.lctr_cost}</td>
                <td>${lecture.lctr_start_date}</td>
                <td>${lecture.lctr_state}</td>
                
            </tr>
        </c:forEach>
    </tbody>
    </main>
</table>

</body>
</html>