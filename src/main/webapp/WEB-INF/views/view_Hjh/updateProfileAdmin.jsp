<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="headerAdmin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


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
                <!-- No 계산: 현재 페이지 번호 * 페이지당 항목 수 + 항목 번호 -->
                <td>${(page.currentPage - 1) * page.rowPage + status.index + 1}</td>
                <td><a href="/view_Hjh/detailProfile/${user.user_id}">${user.user_name}</a></td>
                <td>${user.user_id}</td>
                <td>${user.email}</td>
                <td>${user.address}</td>
                <td>${user.phone_num}</td>
                <td>${user.USER_STATUS}</td>

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
