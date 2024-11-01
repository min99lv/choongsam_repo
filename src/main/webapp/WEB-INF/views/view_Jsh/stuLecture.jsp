<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
	#listBody{
		width: 1560px;
		height: 100%;
		background-color: #F1F1F1;
		float: right;
	}
	
	.lectureName {
		width: 1560px;
		height: 119px;
		text-align: center;
		font-size: 45px;
	}
	
	.teachName {
		font-size: 30px;
		color: #D9D9D9;
	}
	
	.list {
		width: 1400px;
		height: 203px;
		background-color: white;
		margin-bottom: 11px;
		display: inline-block;
	}
	
	#thumbnail {
		width: 328px;
		height: 191px;
		margin-left: 5px;
	}
	
	.vdoInfor {
		width: 566px;
	}
	
	.button {
		width: 240px;
		height: 86px;
	}
	
</style>

<body>

	<div id="listBody">
		<div class="infor">
			<div id="lectureName">
				${lectName }
			</div>
			<div id="teachName">
				${teacherName }
			</div>
		</div>
		<div class="notice">
			
		</div>
	
		<c:forEach var="conts" items="${contentList}">
			<div class="list">
				<div>
					<img id="thumbnail" alt="유튜브 썸네일" src="https://img.youtube.com/vi/${conts.vdo_url_addr }/default.jpg">
				</div>
				
				<div class="vdoInfor">
					<div class="time">
    <div id="chashi">${conts.lctr_no}</div>
    <div id="gigan">
        <c:choose>
            <c:when test="${conts.lctr_no == 1}">
                ${conts.lctr_start_date} ~ ${conts.viewing_period}
            </c:when>
            <c:otherwise>
                ${conts.befor_period} ~ ${conts.viewing_period}
            </c:otherwise>
        </c:choose>
    </div>
</div>
					
					<div id="title">
						${conts.vdo_file_nm }
					</div>
					
					<div id="jindo">
						<c:choose>
						    <c:when test="${conts != null && conts.conts_max != null && conts.conts_max > 0}">
						        <meter min="0" max="${conts.vdo_length}" value="${conts.vdo_length != null && conts.vdo_length >= 0 ? conts.conts_max : 0}"></meter>
						    </c:when>
						    <c:otherwise>
						        <meter min="0" max="100" value="0"></meter>
						    </c:otherwise>
						</c:choose>
					</div>
					
					<div class="button">
						<div id="state"></div>
						<div id="start">학습 시작</div>
					</div>
					
				</div>
			</div>
		</c:forEach>
	</div>
	

	
	
</body>
</html>