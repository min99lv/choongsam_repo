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
<title>진행중인 강의 과제 수정</title>
</head>
<body>
	<h2>진행중인 강의 과제 수정 (강사)</h2>
	<form action="/Jhe/updateHomework" method="post" id="updateHWForm">
		<table>
			<tr>
				<th><input type="hidden" name="ASMT_NO" value="${upHomework.ASMT_NO}">
					과제명</th>
				<td><input type="text" name="ASMT_NM" value="${upHomework.ASMT_NM}" required></td>
			</tr>
			<tr>
				<th>과제 시작일</th>
				<td><input type="text" name="SBMSN_BGNG_YMD" value="${upHomework.SBMSN_BGNG_YMD}" required></td>
			</tr>
			<tr>
				<th>과제 마감일</th>
				<td><input type="text" name="SBMSN_END_YMD" value="${upHomework.SBMSN_END_YMD}"></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="text" name="FILE_GROUP" value="${upHomework.FILE_GROUP}"></td>
			</tr>
			<tr>
				<th>과제 내용</th>
				<td><textarea name="ASMT_CN">${upHomework.ASMT_CN}</textarea></td>
			</tr>
		</table>
			<button type="submit">수정</button>
			<a href="../Jhe/profHomeworkList"><button type="button">취소</button></a>
	</form>
</body>
</html>