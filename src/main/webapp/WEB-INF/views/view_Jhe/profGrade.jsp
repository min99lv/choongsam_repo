<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강생 성적 조회</title>
</head>
<body>
<h2>수강생 성적 조회</h2>
<table>
	<tr>
		<th>수강생</th>
		<th>출석점수</th>
		<th>과제점수</th>
		<th>총 점수</th>
		<th>수료여부</th>
		<th>수정</th>
	</tr>
	<tr>
		<td>${gradeList.stud_name}</td>
		<td>${gradeList.atndc_scr}</td>
		<td>${gradeList.asmt_scr}</td>
		<td>${gradeList.last_scr}</td>
		<td>${gradeList.fnsh_yn}</td>
		<td><button type="submit">수정</button></td>
	</tr>
</table>
</body>
</html>