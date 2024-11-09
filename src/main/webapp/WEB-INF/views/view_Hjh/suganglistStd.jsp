<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="headerStd.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>수강신청 목록</title>
    <style>
        /* 간단한 스타일 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<main>
<body>

<h2>수강신청 목록</h2>

<!-- 수강신청 리스트 테이블 -->
<table>

    <thead>
        <tr>
            <th>강의번호</th>
            <th>회원번호 (학생)</th>
            <th>신청일</th>
            <th>수강상태</th>
            <th>결제일</th>
            
            <th>결제상태 변경</th> <!-- 결제상태 변경 버튼 추가 -->
        </tr>
    </thead>
    <tbody>
        <!-- 수강신청 리스트를 출력 -->
        <c:forEach var="course" items="${sugangStu}">
            <tr>
                <td>${course.lctr_id}</td>
                <td>${course.user_seq}</td>
                <td>${course.aply_date}</td>
                <td>${course.reg_state}</td>
                <td>${course.pay_date}</td>

                <td>
                    <!-- 결제상태를 'Y'로 변경하는 폼 -->
                    <c:choose>
                        <c:when test="${course.pay_state == 'N'}">
                            <!-- 결제 상태가 'N'이면 버튼 표시 -->
                            <form action="updatePayState" method="POST">
                                <input type="hidden" name="lctr_id" value="${course.lctr_id}" />
                                <input type="hidden" name="user_seq" value="${course.user_seq}" />
                                <button type="submit" name="pay_state" value="Y">수납완료로 변경</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <!-- 결제 상태가 'Y'이면 버튼 안보이게 처리 -->
                            <span>수납완료</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>
    
</table>
</main>
</body>
</html>
