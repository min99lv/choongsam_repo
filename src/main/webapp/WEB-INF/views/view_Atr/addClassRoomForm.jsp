<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function saveTimeTbl(ahnIndex) {
		 alert("saveTimeTbl  Run...");  
		 alert("saveTimeTbl  ahnIndex->"+ahnIndex);  
			var lctr_id  = $('#lctr_id').val();
			var schd =  $('#schd'+ahnIndex).val();
			var lctr_room =  $('#lctr_room'+ahnIndex).val();
			 alert("saveTimeTbl  schd->"+schd);  
			 alert("saveTimeTbl  lctr_room->"+lctr_room);  

	}

</script>
</head>
<body>
   		<input type="hidden"  id="lctr_id"  name="lctr_id"    value="${lctr_id }">
	<table >
		<tr><th>No</th><th>시간</th><th>강의실</th></tr>
		<c:forEach var="schd"  items="${schdList}" varStatus="status">
		    <input  type="hidden"   id="schd${status.index}"  name="schd"  value="${schd }"  >
			<tr>
				  <td>${status.index}</td>
				  <td>${schd }</td>
			       <td><input  id="lctr_room${status.index}" name="lctr_room"   >
				          <input type="button"  value="강의실 등록"  onclick="saveTimeTbl(${status.index})">
				      </td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>