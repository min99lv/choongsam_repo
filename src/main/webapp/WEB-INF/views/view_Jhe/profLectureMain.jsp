<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>강의 정보 (강사)</h2>
<form action="/Jhe/studLectureMain" method="get">
	<table id="lectureManage">
		<tr>
			<th>강의명</th>
			<th>강사명</th>
			<th>수강인원</th>
		</tr>
		<c:forEach var="lecture" items="${studLectureList}">
			<tr>
				<td>${lecture.lctr_name}</td>
				<td>${lecture.user_name}</td>
				<td>${lecture.reg_count}</td>
			</tr>
		</c:forEach>
	</table>
</form>

<form action="/Jhe/profAttMain" method="get">
	<input type="hidden" name="LCTR_ID" value="${LCTR_ID}" />
	<input type="hidden" name="onoff" value="${onoff}" />
		<c:if test="${onoff == 7001}">
			<button type="submit">출석</button>
		</c:if>
		<c:if test="${onoff == 7002}">
			<button type="submit">온라인출석</button>
		</c:if>
</form>

<form action="/Jhe/profHomeworkList" method="get">
    <input type="hidden" name="LCTR_ID" value="${LCTR_ID}" />
    <button type="submit">과제</button>
</form>

<form action="/Jhe/profGrade" method="get">
    <input type="hidden" name="LCTR_ID" value="${LCTR_ID}" />
    <button type="submit">성적</button>
</form>
</body>
</html>