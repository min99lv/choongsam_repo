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
            <th>No</th>
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
        <c:forEach var="lecture" items="${lectureList}" varStatus="status">
            <tr>
                <!-- 강의번호는 status로 변환하여 출력 -->
                <td>${status.index + 1}</td>

                <!-- 강의명 -->
                <td>${lecture.lctr_name}</td>

                <!-- 강의구분: 3001:취미, 3002:전문 -->
                <td>
                    <c:choose>
                        <c:when test="${lecture.lctr_category == '3001'}">취미</c:when>
                        <c:when test="${lecture.lctr_category == '3002'}">전문</c:when>
                        <c:otherwise>기타</c:otherwise>
                    </c:choose>
                </td>

                <!-- 대면 여부: 7001:대면, 7002:온라인 -->
                <td>
                    <c:choose>
                        <c:when test="${lecture.onoff == '7001'}">대면</c:when>
                        <c:when test="${lecture.onoff == '7002'}">온라인</c:when>
                        <c:otherwise>기타</c:otherwise>
                    </c:choose>
                </td>

                <!-- 모집인원 -->
                <td>${lecture.register_count}/${lecture.lctr_count}</td>

                <!-- 수강료 -->
                <td>${lecture.lctr_cost}</td>

                <!-- 개강일 -->
                <td>${lecture.lctr_start_date}</td>

                <!-- 상태: 4001:허가 대기중, 4002:모집전, 4003:모집중, 4004:강의전, 4005:강의중, 4006:폐강 -->
                <td>
                    <c:choose>
                        <c:when test="${lecture.lctr_state == '4001'}">허가 대기중</c:when>
                        <c:when test="${lecture.lctr_state == '4002'}">모집전</c:when>
                        <c:when test="${lecture.lctr_state == '4003'}">모집중</c:when>
                        <c:when test="${lecture.lctr_state == '4004'}">강의전</c:when>
                        <c:when test="${lecture.lctr_state == '4005'}">강의중</c:when>
                        <c:when test="${lecture.lctr_state == '4006'}">폐강</c:when>
                        <c:otherwise>기타</c:otherwise>
                    </c:choose>
                </td>

            </tr>
        </c:forEach>
    </tbody>
</table>

</main>
</body>
</html>
