<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌별 수강생 출결현황</title>
</head>
<body>
	<h2>강좌별 수강생 출결현황</h2>
	<table id="studAttByLecture">
		<tr>
			<th>강의명</th>
			<th>수강인원</th>
			<th>출석</th>
			<th>지각</th>
			<th>결석</th>
			<th>출석률</th>
		</tr>
		<tr>
			<td>${studAttByLecture.lctr_name}</td>
			<td>${studAttByLecture.totStudNum}</td>
			<td>${studAttByLecture.attendance}</td>
			<td>${studAttByLecture.lateness}</td>
			<td>${studAttByLecture.absence}</td>
			<td>${studAttByLecture.attendanceRate}</td>
		</tr>
	</table>
</body>
</html>