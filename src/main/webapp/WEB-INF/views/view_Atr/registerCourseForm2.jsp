<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Registration</title>
</head>
<body>
	<form action="registerCourse2" method="post">
		강의 목표 : <input type="text" name="course0"><br>

		<c:forEach var="i" begin="1" end="${classCount}">
                 ${i}주차 강의 개요: <input type="text" name="course${i}">
			<br>
		</c:forEach>

	<input value="${classCount}" name="classCount">
	<input value="${CourseId}" name="lctrId">
		<input type="submit" value="Submit">
	</form>
</body>
</html>