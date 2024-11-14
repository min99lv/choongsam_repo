<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../headerGreen.jsp" %>
<%@ include file="../myStudyHomeNav.jsp" %>
<link rel="stylesheet" type="text/css" href="/css/heStd.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
	#insHomework{
		margin-left: 982px;
		margin-bottom: 10px;
	}
	#delHomework {
		margin-left: 940px;
		margin-top: 10px;
	}
	#asmtName {
		padding: 0px 250px;
	}
	#asmt {
		padding: 20px;
	}
</style>
<title>진행중인 강의 과제 리스트</title>
</head>
<body>
	<div class="container">
	<div class="contents">
		<h1>과제 관리</h1>
	</div>
	<form action="/Jhe/insertHomework" method="get">
		<input type="hidden" name="lctr_id" value="${lctr_id}">
    		<button type="submit" id="insHomework">과제 등록</button>
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
			<th>삭제</th>
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
	<button type="submit" id="delHomework">선택한 과제 삭제</button>
	<h2>과제 제출 현황</h2>
	<table>
		<tr>
			<th id="asmtName">과제명</th>
			<th id="asmt">학생 이름</th>
			<th id="asmt">제출 상태</th>
		</tr>
		<c:forEach var="studList" items="${studList}">
			<tr>
				<td id="asmtName">${studList.asmt_nm}</td>
				<td id="asmt">${studList.stud_name}</td>
				<td id="asmt">${studList.sbmsn_yn}</td>
			</tr>
		</c:forEach>
	</table>
</form>
</div>
</body>
</html>