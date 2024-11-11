<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>내 강의 리스트</title>
</head>
<body>
	<c:if test="${usertype == 1002}"><h2>내 강의 리스트 (강사)</h2></c:if>
	<c:if test="${usertype == 1001}"><h2>내 강의 리스트 (수강생)</h2></c:if>
	<table id="homeworkList">
		<tr>
			<th>강의명</th>
			<th>강사명</th>
		</tr>
		<c:forEach var="homeworkList" items="${homeworkList}">
				<tr>
					<td>
						<c:if test="${usertype == 1002}">
							<a href="/Jhe/profLectureMain?LCTR_ID=${homeworkList.lctr_id}">${homeworkList.lctr_name}</a>
						</c:if>
						<c:if test="${usertype == 1001}">
							<a href="/Jhe/studLectureMain?LCTR_ID=${homeworkList.lctr_id}">${homeworkList.lctr_name}</a>
						</c:if>
					</td>
					<td>${homeworkList.user_name}</td>
				<tr>
		</c:forEach>
	</table>
</body>
</html>