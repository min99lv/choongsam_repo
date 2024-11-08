<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lecture List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        .pagination {
            margin-top: 20px;
            text-align: center;
        }
        .pagination a {
            display: inline-block;
            padding: 8px 16px;
            margin: 0 4px;
            border: 1px solid #ddd;
            text-decoration: none;
            color: #333;
        }
        .pagination a.active {
            font-weight: bold;
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Lecture List</h2>
    <table>
        <thead>
            <tr>
                <th>회원번호</th>
                <th>온/오프라인 여부</th>
                <th>강의구분명</th>
                <th>강의명</th>
                <th>모집인원수</th>
                <th>신청인원</th>
                <th>모집 시작일</th>
                <th>수강료</th>
              
                <th>개강일</th>
                <th>총 차시</th>
                <th>신청하기</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="lecture" items="${lectureList}">
                <tr>
                    <td>${lecture.user_name}</td>
                    <td>${lecture.onoff}</td>
                    <td>${lecture.lctr_category}</td>
                    <td>${lecture.lctr_name}</td>
                    <td>${lecture.lctr_count}</td>
                    <td <c:if test="${lecture.lctr_count eq lecture.register_count}"> style="color: red;"</c:if> >${lecture.register_count}</td>
                    <td>${lecture.rcrt_date}</td>
                    <td>${lecture.lctr_cost}</td>
                    <td>${lecture.lctr_start_date}</td>
                    <td>${lecture.lctr_cntschd}</td>
                    <td><a href="courseApplyDetail?lctr_id=${lecture.lctr_id}" <c:if test="${lecture.lctr_count eq lecture.register_count}"> hidden=""</c:if>>신청하기</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <c:if test="${paging.startPage > 1}">
            <a href="?page=1">First</a>
            <a href="?page=${paging.startPage - 1}">Previous</a>
        </c:if>
        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="page">
            <a href="?page=${page}" class="${page == paging.currentPage ? 'active' : ''}">${page}</a>
        </c:forEach>
        <c:if test="${paging.endPage < paging.totalPage}">
            <a href="?page=${paging.endPage + 1}">Next</a>
            <a href="?page=${paging.totalPage}">Last</a>
        </c:if>
    </div>
</body>
</html>
