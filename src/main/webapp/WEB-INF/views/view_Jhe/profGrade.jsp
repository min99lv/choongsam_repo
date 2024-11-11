<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강생 성적 조회</title>
</head>
<body>
<h2>수강생 성적 조회</h2>
<table>
	<tr>
		<th>강의명</th>
		<th>수강생</th>
		<th>출석점수</th>
		<th>과제점수</th>
		<th>총 점수</th>
		<th>수료여부</th>
	</tr>
	<c:forEach var="studentScore" items="${studentScoreList}">
		<tr>
			<td>${studentScore.lctr_name}</td>
			<td>${studentScore.stud_name}</td>
			<td>${studentScore.atndc_scr}</td>
			<td>${studentScore.asmt_scr}</td>
			<td>${studentScore.last_scr}</td>
			<td>
				<c:choose>
					<c:when test="${studentScore.fnsh_yn == 'Y'}">수료</c:when>
					<c:otherwise>미수료</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>