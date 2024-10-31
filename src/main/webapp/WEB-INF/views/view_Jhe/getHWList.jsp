<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제리스트</title>
</head>
<body>
	<h2>나는 과제리스트</h2>
	<table id="homeworkList">
		<tr>
			<th>강의명</th>
			<th>강사명</th>
			<th>과제명</th>
		</tr>
		<c:set var="previousName" value="" />
		<c:set var="previousAsmtNm" value="" />
		<c:set var="previousprofNm" value="" />
		<c:forEach var="HWList" items="${HWList}">
			<c:if test="${HWList.lctr_name != previousName}">
				<tr>
					<td>${HWList.lctr_name}</td>
					<td>${HWList.user_name}</td>
					<td>${HWList.ASMT_NM}</td>
				</tr>
				<c:set var="previousName" value="${HWList.lctr_name}" />
				<c:set var="previousprofNm" value="${HWList.user_name}" />
				<c:set var="previousAsmtNm" value="${HWList.ASMT_NM}" />
			</c:if>
			<c:if
				test="${HWList.lctr_name == previousName && HWList.user_name == previousprofNm && HWList.ASMT_NM != previousAsmtNm}">
				<tr>
					<td></td>
					<td></td>
					<td>${HWList.ASMT_NM}</td>
				</tr>
				<c:set var="previousAsmtNm" value="${HWList.ASMT_NM}" />
			</c:if>
		</c:forEach>
	</table>
	<a href="/Jhe/insertHomework"><button type="button">과제등록</button></a>
</body>
</html>