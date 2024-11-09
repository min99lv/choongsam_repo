<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차시별 수강생 출결 현황</title>
</head>
<body>
	<h2>차시별 수강생 출결 현황</h2>
	<table>
		<tr>
			<th>수강생명</th>
			<th>출석 상태</th>
		</tr>
		<c:forEach var="profAttDetail" items="${profAttDetailList}">
		<tr>
		 	<td>${profAttDetail.stud_name}</td>
			<td>
				<c:choose>
					<c:when test="${profAttDetail.att_status == 5001}">출석</c:when>
					<c:when test="${profAttDetail.att_status == 5002}">지각</c:when>
					<c:when test="${profAttDetail.att_status == 5003}">결석</c:when>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>