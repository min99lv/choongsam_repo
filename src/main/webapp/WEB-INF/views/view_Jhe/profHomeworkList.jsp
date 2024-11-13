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
<title>진행중인 강의 과제 리스트</title>
</head>
<body>
	<h2>나는 진행중인 내 강의 과제 리스트 (강사)</h2>
	<form action="/Jhe/insertHomework" method="get">
		<input type="hidden" name="lctr_id" value="${lctr_id}">
    		<button type="submit">과제 등록</button>
	</form>
<form action="/Jhe/deleteHomework?lctr_id=${lctr_id}" method="post">
	<table id="homeworkList">
		<tr>
			<th>강의명</th>
			<th>강사명</th>
			<th>과제명</th>
			<th>진행</th>
			<th>시작일</th>
			<th>마감일</th>
			<th>제출</th>
			<th></th>
		</tr>
		<c:set var="previousName" value="" />
		<c:set var="previousAsmtNm" value="" />
		<c:set var="previousprofNm" value="" />
		<c:forEach var="profHomework" items="${profHomeworkList}">
			<c:if test="${profHomework.lctr_name != previousName}">
				<tr>
					<td>${profHomework.lctr_name}</td>
					<td>${profHomework.prof_name}</td>
					<td><a href="/Jhe/updateHomework?ASMT_NO=${profHomework.asmt_no}">${profHomework.asmt_nm}</a></td>
					<td>${profHomework.asmtStatus}</td>
					<td>${profHomework.sbmsn_bgng_ymd}</td>
					<td>${profHomework.sbmsn_end_ymd}</td>
					<td>${profHomework.submissionRate}%</td>
					<td><input type="checkbox" name="delCheck" value="${profHomework.asmt_no}"></td>
				</tr>
				<c:set var="previousName" value="${profHomework.lctr_name}" />
				<c:set var="previousprofNm" value="${profHomework.prof_name}" />
				<c:set var="previousAsmtNm" value="${profHomework.asmt_nm}" />
			</c:if>
			<c:if test="${profHomework.lctr_name == previousName
						&& profHomework.prof_name == previousprofNm
						&& profHomework.asmt_nm != previousAsmtNm}">
				<tr>
					<td></td>
					<td></td>
					<td><a href="/Jhe/updateHomework?ASMT_NO=${profHomework.asmt_no}">${profHomework.asmt_nm}</a></td>
					<td>${profHomework.asmtStatus}</td>
					<td>${profHomework.sbmsn_bgng_ymd}</td>
					<td>${profHomework.sbmsn_end_ymd}</td>
					<td>${profHomework.submissionRate}%</td>
					<td><input type="checkbox" name="delCheck" value="${profHomework.asmt_no}"></td>
				</tr>
				<c:set var="previousAsmtNm" value="${profHomework.asmt_nm}" />
			</c:if>
		</c:forEach>
	</table>
	<button type="submit">선택한 과제 삭제</button>
	<h2>수강중인 학생 과제 제출 현황</h2>
	<table>
		<tr>
			<th>과제명</th>
			<th>학생 이름</th>
			<th>제출 상태</th>
		</tr>
		<c:forEach var="studList" items="${studList}">
			<tr>
				<td>${studList.asmt_nm}</td>
				<td>${studList.stud_name}</td>
				<td>${studList.sbmsn_yn}</td>
			</tr>
		</c:forEach>
	</table>
</form>
</body>
</html>