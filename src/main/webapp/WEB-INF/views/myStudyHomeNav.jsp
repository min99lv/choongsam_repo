<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	.nav{
		  position: fixed;
		  width: 320px;
		  top:0;
		  height: 100vh;
		  z-index: 1;
		  background-color: #7B7B7B;
		  overflow:hidden;
		  transition:width .3s ease;
		  cursor:pointer;
		  @media screen and (min-width: 600px) {
		    width: 320px;
		  }
	}
	
	a {
		color: white;
		text-decoration: none;
	}
	
	.detail {
		margin-left: 50px;
	}
	
	.butt{
		width: 320px;
		height: 90px;
		font-size: 30px;
		color: white;
	}
	
	.info{
		margin-top: 50px;
		margin-bottom: 50px;
	}
</style>

</head>

<body>

<nav>
	<div class="nav">
	
		<div class="detail">
			<div class="info">
				<label class="user">사용자 정보 </label>
				<div class="userName">
					<c:out value="${sessionScope.username}" />
				</div>
			</div>
		
			<div class="butt">
				<a href="">내 강의실</a>
			</div>
			<div class="butt">
				<a href="">출석</a>
			</div>
			<div class="butt">
				<a href="/Jhe/studHomeworkList">과제</a>
			</div>
			<div class="butt">
				<a href="">공지사항</a>
			</div>
		</div>
		
	</div>
</nav>

</body>
</html>
