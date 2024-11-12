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
	<form action="/Jhe/updateHomework" method="post" id="updateHomework" enctype="multipart/form-data">
		<table>
			<tr>
				<th><input type="hidden" name="asmt_no" value="${upHomework.asmt_no}">
					강의명</th>
				<td>${upHomework.lctr_name}</td>
			</tr>
			<tr>
				<th>과제명</th>
				<td><input type="text" name="asmt_nm" value="${upHomework.asmt_nm}" required></td>
			</tr>
			<tr>
				<th>과제 시작일</th>
				<td><input type="text" name="sbmsn_bgng_ymd" value="${upHomework.sbmsn_bgng_ymd}" required></td>
			</tr>
			<tr>
				<th>과제 마감일</th>
				<td><input type="text" name="sbmsn_end_ymd" value="${upHomework.sbmsn_end_ymd}" required></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><c:if test="${not empty upHomework.fileName}">
					<a href="/Jhe/downloadFile?fileId=${upHomework.fileId}">${upHomework.fileName}</a></c:if>
					<input type="file" name="file_nm" accept="application/pdf, image/*, .doc, .docx, .txt"></td>
			</tr>
			<tr>
				<th>과제 내용</th>
				<td><textarea name="asmt_cn">${upHomework.asmt_cn}</textarea>
				<input type="hidden" name="lctr_id" value="${upHomework.lctr_id}"></td>
			</tr>
		</table>
			<button type="submit">수정</button>
			<a href="../Jhe/profHomeworkList?lctr_id=${upHomework.lctr_id}"><button type="button">취소</button></a>
	</form>
</body>
</html>