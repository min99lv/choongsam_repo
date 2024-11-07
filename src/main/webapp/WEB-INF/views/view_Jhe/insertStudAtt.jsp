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
<h2>차시별 출석 입력</h2>
<form action="/Jhe/insertStudAtt" method="post">
<input type="hidden" name="LCTR_ID" value="${LCTR_ID}">
<input type="hidden" name="LCTR_NO" value="${LCTR_NO}">
<table>
	<tr>
		<th>이름</th>
		<th>출석</th>
		<th>지각</th>
		<th>결석</th>
	</tr>
	<c:forEach var="student" items="${getStudAttList}">
	<tr>
		<td><input type="hidden" name="user_seq" value="${student.user_seq}">
			${student.user_name}</td>
		<td><input type="radio" name="att_status_${student.user_seq}" value=5001></td>  <!-- 출석 라디오 버튼 -->
		<td><input type="radio" name="att_status_${student.user_seq}" value=5002></td>  <!-- 지각 라디오 버튼 -->
		<td><input type="radio" name="att_status_${student.user_seq}" value=5003></td>  <!-- 결석 라디오 버튼 -->
	</tr>
	</c:forEach>
</table>
<button type="submit">등록</button>
</form>
</body>
</html>