<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>비대면 강의 차시별 수강생 출결현황</h2>
	<table>
		<tr>
			<th>차시</th>
			<th>출석</th>
			<th>결석</th>
			<th>출석률</th>
		</tr>
		<c:forEach var="onlineAtt" items="${onlineAttList}">
		<tr>
		 	<td><a href="/Jhe/insertStudAtt?LCTR_ID=${onlineAtt.lctr_id}&LCTR_NO=${onlineAtt.lctr_no}&onoff=${onlineAtt.onoff}">${onlineAtt.lctr_no}</a></td>
			<td>${onlineAtt.present_count}</td>
			<td>${onlineAtt.absent_count}</td>
			<td>${onlineAtt.attendance_rate}%</td>
		</tr>
		</c:forEach>
	</table>
</body>
</body>
</html>