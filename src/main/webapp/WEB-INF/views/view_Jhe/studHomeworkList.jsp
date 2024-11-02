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
<script type="text/javascript">
	alert('${status == "success" ? "과제가 제출되었습니다." : "과제 제출에 실패했습니다. 다시 시도해 주세요."}');
</script>
<title>수강중인 강의 과제 리스트</title>
</head>
<body>
	<h2>나는 수강중인 강의 과제 리스트 (학생)</h2>
	<table id="homeworkList">
		<tr>
			<th>강의명</th>
			<th>강사명</th>
			<th>과제명</th>
			<th></th>
		</tr>
		<c:set var="previousName" value="" />
		<c:set var="previousAsmtNm" value="" />
		<c:set var="previousprofNm" value="" />
		<c:forEach var="HWList" items="${HWList}">
			<c:if test="${HWList.lctr_name != previousName}">
				<tr>
					<td>${HWList.lctr_name}</td>
					<td>${HWList.user_name}</td>
					<td><a href="/Jhe/updateHomework?ASMT_NO=${HWList.ASMT_NO}">${HWList.ASMT_NM}</a></td>
					<td><input type="checkbox" name="delCheck" value="${HWList.ASMT_NO}"></td>
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
					<td><a href="/Jhe/updateHomework?ASMT_NO=${HWList.ASMT_NO}">${HWList.ASMT_NM}</a></td>
					<td><input type="checkbox" name="delCheck" value="${HWList.ASMT_NO}"></td>
				</tr>
				<c:set var="previousAsmtNm" value="${HWList.ASMT_NM}" />
			</c:if>
		</c:forEach>
	</table>
	<a href="/Jhe/insertHomework"><button type="button">과제등록</button></a>
	<button type="submit">삭제</button>
</body>
</html>