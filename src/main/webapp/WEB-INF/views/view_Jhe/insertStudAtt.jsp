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
<title>Insert title here</title>
</head>
<body>
<h2>차시별 출석 입력</h2>
<form action="/Jhe/insertStudAtt" method="post">
<input type="hidden" name="LCTR_ID" value="${LCTR_ID}">
<input type="hidden" name="LCTR_NO" value="${LCTR_NO}">
<input type="hidden" name="onoff" value="${onoff}">
<button type="button" id="markAllPresent">전체 출석</button>
<table>
	<tr>
		<th>이름</th>
		<th>출석</th>
		<c:if test="${onoff != 7002}">
		<th>지각</th>
		</c:if>
		<th>결석</th>
	</tr>
	<c:forEach var="student" items="${getStudAttList}">
	<tr>
		<td><input type="hidden" name="user_seq" value="${student.user_seq}">
			${student.user_name}</td>
		<td><input type="radio" name="att_status_${student.user_seq}" value=5001
			<c:if test="${student.att_status == 5001}">checked</c:if>></td>  <!-- 출석 라디오 버튼 -->
		<c:if test="${onoff != 7002}">
		<td><input type="radio" name="att_status_${student.user_seq}" value=5002
			<c:if test="${student.att_status == 5002}">checked</c:if>></td>  <!-- 지각 라디오 버튼 -->
		</c:if>
		<td><input type="radio" name="att_status_${student.user_seq}" value=5003
			<c:if test="${student.att_status == 5003}">checked</c:if>></td>  <!-- 결석 라디오 버튼 -->
	</tr>
	</c:forEach>
</table>
<button type="submit">등록</button>
</form>
<script type="text/javascript">
document.getElementById("markAllPresent").addEventListener("click", function() {
	const radioButtons = document.querySelectorAll('input[type="radio"][name^="att_status_"]');

	radioButtons.forEach(function(button) {
		if (button.value === "5001") {
			button.checked = true;
		}
	});
});
</script>
</body>
</html>