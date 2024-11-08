<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강생 출결현황</title>
</head>
<body>
	<h2>차시별 수강생 출결현황</h2>
	<table>
		<tr>
			<th>차시</th>
			<th>출석</th>
			<c:if test="${onoff != 7002}">
			<th>지각</th>
			</c:if>
			<th>결석</th>
			<th>출석률</th>
		</tr>
		<c:forEach var="AttMain" items="${profAttMainList}">
		<tr>
		 	<td><c:if test="${onoff != 7002}">
		 		<a href="/Jhe/insertStudAtt?LCTR_ID=${AttMain.lctr_id}&LCTR_NO=${AttMain.lctr_no}&onoff=${AttMain.onoff}">
		 		${AttMain.lctr_no}</a>
		 		</c:if>
		 		<c:if test="${onoff == 7002}">
		 			${AttMain.lctr_no}
		 		</c:if>
		 	</td>
			<td>${AttMain.present_count}</td>
			<c:if test="${onoff != 7002}"><td>${AttMain.late_count}</td></c:if>
			<td>${AttMain.absent_count}</td>
			<td>${AttMain.attendance_rate}%</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>