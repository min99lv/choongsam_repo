<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="adminPage.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리 시스템</title>


</head>
<body>

<main>
    <div class="search-container">
        <form action="/view_Hjh/updateProfileAdmin" method="get" id="searchForm">
            <input type="text" name="keyword" placeholder="아이디 or 이름" />
            <button type="submit">검색</button>
        </form>
    </div>

    <table class="tg" border="1">
        <tr>
            <th>No</th>
            <th>이름</th>
            <th>아이디</th>
            <th>이메일</th>
            <th>주소</th>
            <th>핸드폰 번호</th>
            <th>회원 상태</th>
        </tr>

		<c:forEach var="user" items="${userList}" varStatus="status">
		    <tr>
		        <td>${status.index + 1}</td>
		        <td><a href="/view_Hjh/detailProfile/${user.user_id}">${user.user_name}</a></td>
		        <td>${user.user_id}</td>
		        <td>${user.email}</td>
		        <td>${user.address}</td>
		        <td>${user.phone_num}</td>
		        <td>
		            <c:choose>
		                <c:when test="${user.user_status == 10001}">학생</c:when>
		                <c:when test="${user.user_status == 1002}">강사</c:when>
		                <c:otherwise>기타</c:otherwise>
		            </c:choose>
		        </td>
		    </tr>
		</c:forEach>

    </table>

    <!-- Pagination Controls -->
    <div class="pagination">
        <c:if test="${page.startPage > page.pageBlock}">
            <a href="/view_Hjh/updateProfileAdmin?currentPage=${page.startPage - page.pageBlock}&keyword=${keyword}">이전</a>
        </c:if>

        <c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
            <c:choose>
                <c:when test="${i == page.currentPage}">
                    <span>${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="/view_Hjh/updateProfileAdmin?currentPage=${i}&keyword=${keyword}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${page.endPage < page.totalPage}">
            <a href="/view_Hjh/updateProfileAdmin?currentPage=${page.startPage + page.pageBlock}&keyword=${keyword}">다음</a>
        </c:if>
    </div>
</main>
</body>
</html>
