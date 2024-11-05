<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>수강중인 강의 과제 리스트</title>
</head>
<body>
	<h2>나는 수강중인 강의 과제 리스트 (학생)</h2>
	<table id="homeworkList">
		<tr>
			<th>강의명</th>
			<th>강사명</th>
			<th>과제명</th>
			<th>진행</th>
			<th>제출</th>
			<th>마감일</th>
		</tr>
		<c:set var="previousName" value="" />
		<c:set var="previousAsmtNm" value="" />
		<c:set var="previousprofNm" value="" />
		<c:forEach var="homeworkList" items="${homeworkList}">
			<c:if test="${homeworkList.lctr_name != previousName}">
				<tr>
					<td>${homeworkList.lctr_name}</td>
					<td>${homeworkList.profName}</td>
					<td><a href="/Jhe/submitHomework?ASMT_NO=${homeworkList.ASMT_NO}">${homeworkList.ASMT_NM}</a></td>
					<td>${homeworkList.asmtStatus}</td>
					<td><c:choose>
						<c:when test="${homeworkList.sbmsn_yn == 'Y'}">제출</c:when>
						<c:when test="${homeworkList.sbmsn_yn == 'N'}">미제출</c:when>
					</c:choose></td>
					<td>${homeworkList.SBMSN_END_YMD}</td>
				</tr>
				<c:set var="previousName" value="${homeworkList.lctr_name}" />
				<c:set var="previousprofNm" value="${homeworkList.user_name}" />
				<c:set var="previousAsmtNm" value="${homeworkList.ASMT_NM}" />
			</c:if>
			<c:if
				test="${homeworkList.lctr_name == previousName && homeworkList.user_name == previousprofNm && homeworkList.ASMT_NM != previousAsmtNm}">
				<tr>
					<td></td>
					<td></td>
					<td><a href="/Jhe/submitHomework?ASMT_NO=${homeworkList.ASMT_NO}">${homeworkList.ASMT_NM}</a></td>
					<td>${homeworkList.asmtStatus}</td>
					<td><c:choose>
						<c:when test="${homeworkList.sbmsn_yn == 'Y'}">제출</c:when>
						<c:when test="${homeworkList.sbmsn_yn == 'N'}">미제출</c:when>
					</c:choose></td>
					<td>${homeworkList.SBMSN_END_YMD}</td>
				</tr>
				<c:set var="previousAsmtNm" value="${homeworkList.ASMT_NM}" />
			</c:if>
		</c:forEach>
	</table>
</body>
</html>