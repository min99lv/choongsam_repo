<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>과제 등록</title>
</head>
<body>
	<h2>나는 과제 등록 (강사)</h2>
	<form action="/Jhe/insertHomework" method="post" id="insertHWForm">
		<table>
			<tr>
				<th>과제명</th>
				<td><input type="text" name="ASMT_NM" required></td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="text" name="SBMSN_BGNG_YMD" required></td>
			</tr>
			<tr>
				<th>마감일</th>
				<td><input type="text" name="SBMSN_END_YMD"></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="text" name="addFile" value="attach"></td>
			</tr>
			<tr>
				<th>과제내용</th>
				<td><textarea name="ASMT_CN"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="등록">
		<a href="../Jhe/profHomeworkList"><button type="button">취소</button></a>
	</form>
</body>
</html>