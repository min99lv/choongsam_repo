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
	<h2>수강생 출결현황</h2>
	<table>
		<tr>
			<th>강의명</th>
			<th>총 차시</th>
			<th>수강인원</th>
			<th>출석</th>
			<th>지각</th>
			<th>결석</th>
			<th>출석률</th>
		</tr>
		<c:forEach var="AttMain" items="${profAttMainList}">
		<tr>
			<td>${AttMain.lctr_name}</td>
			<td>${AttMain.lctr_cntschd}</td>
			<td>${AttMain.reg_count}</td>
			<td>${AttMain.present_count}</td>
			<td>${AttMain.late_count}</td>
			<td>${AttMain.absent_count}</td>
			<td>${AttMain.attendance_rate}%</td>
		</tr>
		</c:forEach>
	</table>

	<h2>차시별 수강생 출결현황</h2>
	<table>
		<tr>
			<th>차시</th>
			<th>출석</th>
			<th>지각</th>
			<th>결석</th>
			<th>출석률</th>
		</tr>
		<c:forEach var="AttMain" items="${profAttMainList}">
		<tr>
		 	<td><a href="/Jhe/insertStudAtt?LCTR_ID=${AttMain.lctr_id}&LCTR_NO=${AttMain.lctr_no}">${AttMain.lctr_no}</a></td>
			<td>${AttMain.present_count}</td>
			<td>${AttMain.late_count}</td>
			<td>${AttMain.absent_count}</td>
			<td>${AttMain.attendance_rate}%</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>