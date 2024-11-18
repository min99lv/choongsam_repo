<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../headerGreen.jsp" %>
<%@ include file="../myPageNav.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의 목록</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: white;
            margin: 0;
            padding: 0;
        }

        main {
            margin-left: -150px; /* 내비게이션 바 너비 고려 */
            padding: 200px;
            background-color: white;
        }

        h2 {
            color: #333;
            font-size: 28px;
            margin-bottom: 20px;
            font-weight: bold;
        }

        /* 테이블 스타일 */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #00664F;
            color: white;
            font-size: 16px;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        /* 페이지네이션 */
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination a, .pagination .current {
            padding: 10px 15px;
            margin: 0 5px;
            background-color: #fff;
            color: #00664F;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            border: 1px solid #ddd;
        }

        .pagination .current {
            background-color: #00664F;
            color: white;
            pointer-events: none;
        }

        .pagination a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<main>
<h2>강의 목록</h2>

<!-- 강의 리스트 테이블 -->
<table>
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
        <c:forEach var="lecture" items="${lectureList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${lecture.lctr_name}</td>
                <td>
                    <c:choose>
                        <c:when test="${lecture.lctr_category == '3001'}">취미</c:when>
                        <c:when test="${lecture.lctr_category == '3002'}">전문</c:when>
                        <c:otherwise>기타</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${lecture.onoff == '7001'}">대면</c:when>
                        <c:when test="${lecture.onoff == '7002'}">온라인</c:when>
                        <c:otherwise>기타</c:otherwise>
                    </c:choose>
                </td>
                <td>${lecture.register_count}/${lecture.lctr_count}</td>
                <td>${lecture.lctr_cost}</td>
                <td>${lecture.lctr_start_date}</td>
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

<div class="pagination">
    <c:if test="${page.startPage > page.pageBlock}">
        <a href="/view_Hjh/suganglistStd?currentPage=${page.startPage - page.pageBlock}&keyword=${keyword}">이전</a>
    </c:if>
    <c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
        <c:choose>
            <c:when test="${i == page.currentPage}">
                <span class="current">${i}</span>
            </c:when>
            <c:otherwise>
                <a href="/view_Hjh/suganglistStd?currentPage=${i}&keyword=${keyword}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${page.endPage < page.totalPage}">
        <a href="/view_Hjh/suganglistStd?currentPage=${page.startPage + page.pageBlock}&keyword=${keyword}">다음</a>
    </c:if>
</div>

</main>

</body>
</html>
