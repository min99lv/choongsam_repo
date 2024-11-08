<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>진행중인 강의 과제 리스트</title>
</head>
<body>
	<h2>나는 진행중인 강의 리스트 (강사별)</h2>
	<table id="homeworkList">
		<tr>
			<th>강의명</th>
			<th>강사명</th>
		</tr>
		<c:forEach var="homeworkList" items="${homeworkList}">
				<tr>
					<td><a href="/Jhe/lectureManagement?LCTR_ID=${homeworkList.lctr_id}">${homeworkList.lctr_name}</a></td>
					<td>${homeworkList.user_name}</td>
				<tr>
		</c:forEach>
	</table>
</body>
</html>