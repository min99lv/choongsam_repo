<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../headerGreen.jsp" %>
<%@ include file="../myPageNav.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
#myLecture {
	color: black;
}
#door {
	width: 40px;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: white;
	margin: 0;
	padding: 0;
}

main {
	margin-left: -150px;
	padding: 150px 200px;
	background-color: white;
}

h2 {
	color: #333;
	font-size: 28px;
	margin-bottom: 20px;
	font-weight: bold;
}

	table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 12px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #00664F;
	color: white;
	font-size: 16px;
}

#lctrName{
	text-align: left;
}
</style>
<title>내 강의 리스트</title>
</head>
<body>
<main>
	<c:if test="${usertype == 1002}"><h2>내 강의 목록</h2></c:if>
	<c:if test="${usertype == 1001}"><h2>수강중인 강의 목록</h2></c:if>
	<table id="homeworkList">
		<tr>
			<th id="lctrName">강의명</th>
			<th><c:if test="${usertype == 1001}">강사명</c:if></th>
			<th><c:if test="${usertype == 1002}">수강인원</c:if></th>
			<th>강의실</th>
		</tr>
		<c:forEach var="homeworkList" items="${homeworkList}">
				<tr>
					<td id="lctrName"><strong>${homeworkList.lctr_name}</strong></td>
					<td><c:if test="${usertype == 1001}">${homeworkList.user_name}</c:if></td>
					<td><c:if test="${usertype == 1002}">${homeworkList.reg_count}</c:if></td>
					<td>
						<c:if test="${usertype == 1002}">
							<a href="/sh_lecture_teacher?lctr_id=${homeworkList.lctr_id}&user_seq=${user_seq}&onoff=${homeworkList.onoff}" id="myLecture">
								<img alt="강의실입장" src="../chFile/Homework/입장.png" id="door"></a>
						</c:if>
						<c:if test="${usertype == 1001}">
							<a href="/sh_lecture_student?lctr_id=${homeworkList.lctr_id}&user_seq=${user_seq}&onoff=${homeworkList.onoff}" id="myLecture">
								<img alt="강의실입장" src="../chFile/Homework/입장.png" id="door"></a>
						</c:if>
					</td>
				</tr>
		</c:forEach>
	</table>
	</main>
</body>
</html>