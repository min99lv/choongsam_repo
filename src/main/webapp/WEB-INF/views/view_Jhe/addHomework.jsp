<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
</style>
<title>Insert title here</title>
</head>
<body>
	<h2>나는 과제 등록</h2>
	<form action="/Jhe/insertHomework" method="post" >
		<table>
			<tr>
				<th>과제명</th>
				<td><input type="text" name="ASMT_NM" required></td>
			</tr>
			<tr>
				<th>과제내용</th>
				<td><input type="text" name="ASMT_CN" required></td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="text" name="SBMSN_BGNG_YMD" required></td>
			</tr>
			<tr>
				<th>마감일</th>
				<td><input type="text" name="SBMSN_END_YMD" required></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="text" name="addFile" value="attach"></td>
			</tr>
		</table>
		<input type="submit" value="등록">
	</form>
</body>
</html>