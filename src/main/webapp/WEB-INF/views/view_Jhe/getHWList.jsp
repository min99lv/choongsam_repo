<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제리스트</title>
</head>
<body>
<h2>나는 과제리스트</h2>
<p>
<table id="homeworkList">
	<tr>
		<th>강의명</th>
		<th>과제 번호</th>
		<th>과제명</th>
	</tr>
	<c:forEach var="HWList" items="${HWList}">
		<tr>
			<td>${HWList.lctr_name}</td>
			<td>${HWList.asmt_no}</td>
			<td>${HWList.asmt_nm}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>