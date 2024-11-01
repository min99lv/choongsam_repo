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
	alert('${status == "success" ? "과제가 등록되었습니다." : "과제 등록에 실패했습니다. 다시 시도해 주세요."}');
</script>
<title>진행중인 강의 과제 리스트</title>
</head>
<body>
<form action="/Jhe/deleteHomework" method="post">
	<h2>나는 진행중인 내 강의 과제 리스트 (강사)</h2>
	<table id="homeworkList">
		<tr>
			<th>과제명</th>
			<th>진행</th>
			<th>제출</th>
			<th>마감일</th>
			<th></th>
		</tr>
		<c:forEach var="HWList" items="${HWList}">
			<tr>
				<td><a href="/Jhe/updateHomework?ASMT_NO=${HWList.ASMT_NO}">${HWList.ASMT_NM}</a></td>
				<td>${HWList.SBMSN_BGNG_YMD}</td>
				<td>50%</td>
				<td>${HWList.SBMSN_END_YMD}</td>
				<td><input type="checkbox" name="delCheck" value="${HWList.ASMT_NO}"></td>
			</tr>
		</c:forEach>
	</table>
	<a href="/Jhe/insertHomework"><button type="button">과제등록</button></a>
	<button type="submit">삭제</button>
</form>
</body>
</html>