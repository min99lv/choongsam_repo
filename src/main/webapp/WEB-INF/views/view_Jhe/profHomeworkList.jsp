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
		<input type="hidden" name="LCTR_ID" value="${LCTR_ID}">
    		<button type="submit">과제 등록</button>
	</form>
<form action="/Jhe/deleteHomework?LCTR_ID=${LCTR_ID}" method="post">
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
		<c:forEach var="profHomeworkList" items="${profHomeworkList}">
			<c:if test="${profHomeworkList.lctr_name != previousName}">
				<tr>
					<td>${profHomeworkList.lctr_name}</td>
					<td>${profHomeworkList.prof_name}</td>
					<td><a href="/Jhe/updateHomework?ASMT_NO=${profHomeworkList.asmt_no}">${profHomeworkList.asmt_nm}</a></td>
					<td>${profHomeworkList.asmtStatus}</td>
					<td>${profHomeworkList.sbmsn_bgng_ymd}</td>
					<td>${profHomeworkList.sbmsn_end_ymd}</td>
					<td>${profHomeworkList.submissionRate}%</td>
					<td><input type="checkbox" name="delCheck" value="${profHomeworkList.asmt_no}"></td>
				</tr>
				<c:set var="previousName" value="${profHomeworkList.lctr_name}" />
				<c:set var="previousprofNm" value="${profHomeworkList.prof_name}" />
				<c:set var="previousAsmtNm" value="${profHomeworkList.asmt_nm}" />
			</c:if>
			<c:if test="${profHomeworkList.lctr_name == previousName
						&& profHomeworkList.prof_name == previousprofNm
						&& profHomeworkList.asmt_nm != previousAsmtNm}">
				<tr>
					<td></td>
					<td></td>
					<td><a href="/Jhe/updateHomework?ASMT_NO=${profHomeworkList.asmt_no}">${profHomeworkList.asmt_nm}</a></td>
					<td>${profHomeworkList.asmtStatus}</td>
					<td>${profHomeworkList.sbmsn_bgng_ymd}</td>
					<td>${profHomeworkList.sbmsn_end_ymd}</td>
					<td>${profHomeworkList.submissionRate}%</td>
					<td><input type="checkbox" name="delCheck" value="${profHomeworkList.asmt_no}"></td>
				</tr>
				<c:set var="previousAsmtNm" value="${profHomeworkList.asmt_nm}" />
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