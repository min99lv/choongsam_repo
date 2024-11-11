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
	<form action="/Jhe/insertHomework" method="post" id="insertHomework" enctype="multipart/form-data">
		<table>
			<tr>
				<th>강의명</th>
				<td>${findByLctrName.lctr_name}</td>
			</tr>
			<tr>
				<th>과제명</th>
				<td><input type="text" name="asmt_nm" required></td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="text" name="sbmsn_bgng_ymd" required></td>
			</tr>
			<tr>
				<th>마감일</th>
				<td><input type="text" name="sbmsn_end_ymd"></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<div class="file_list">
						<div>
							<div class="file_input">
								<input type="file" name="files" onchange="selectFile(this);" />
								<button type="button" onclick="removeFile(this);" class="btns del_btn">
									<span>삭제</span>
								</button>
								<button type="button" onclick="addFile();" class="btns fn_add_btn">
									<span>파일 추가</span>
								</button>
							</div>
						</div>
					</div>
				</td>
			<tr>
				<th>과제내용</th>
				<td><textarea name="asmt_cn"></textarea>
				<input type="hidden" name="LCTR_ID" value="${findByLctrName.lctr_id}"></td>
			</tr>
		</table>
		<input type="submit" value="등록">
		<a href="../Jhe/profHomeworkList?LCTR_ID=${findByLctrName.lctr_id}"><button type="button">취소</button></a>
	</form>
</body>
</html>