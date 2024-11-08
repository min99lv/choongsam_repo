<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>강의 상세</h2>
	<table>
		<tr>
			<th>강사ID</th>
			<td>${lecture.user_seq}</td>
		</tr>
		<tr>
			<th>대면여부</th>
			<td>${lecture.onoff}</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>${lecture.lctr_category}</td>
		</tr>
		<tr>
			<th>강의명</th>
			<td>${lecture.lctr_name}</td>
		</tr>
		<tr>
			<th>모집인원</th>
			<td>${lecture.lctr_count}</td>
		</tr>
		<tr>
			<th>모집시작일</th>
			<td>${lecture.rcrt_date}</td>
		</tr>
		<tr>
			<th>수강료</th>
			<td>${lecture.lctr_cost}</td>
		</tr>
		<tr>
			<th>강의상태</th>
			<td>${lecture.lctr_state}</td>
		</tr>
		<tr>
			<th>평가기준</th>
			<td>${lecture.eval_criteria}</td>
		</tr>
		<tr>
			<th>개강일</th>
			<td>${lecture.lctr_start_date}</td>
		</tr>
		<tr>
			<th>차시 수</th>
			<td>${lecture.lctr_cntschd}</td>
		</tr>
		</table>

</body>
</html>