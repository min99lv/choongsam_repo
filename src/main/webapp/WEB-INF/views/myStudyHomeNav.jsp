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
		  width: auto;
		  top:0;
		  height: 100vh;
		  z-index: 1;
		  background-color: #304742;
		  overflow:hidden;
		  transition:width .3s ease;
		  cursor:pointer;
		  @media screen and (min-width: 600px) {
		    width: 320px;
		  }
	}
	
	.butt:hover {
        background-color: #00664F; /* 마우스 오버 시 배경색 */
        color: white; /* 글자색 변경 (선택 사항) */
        transform: scale(1.1); /* 마우스 오버 시 크기 확대 */
         box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3); /* 마우스 오버 시 그림자 */
    }
	
	.butt { 
	    width: 320px;
	    height: 90px;
	    font-size: 30px;
	    color: white;
	    display: flex;            /* 플렉스박스 사용 */
	    align-items: center;      /* 수직 중앙 정렬 */
		 border-bottom: solid 2px white;
	}

	
	.link{
		margin-left: 50px;
	}
	
	a {
		color: white;
		text-decoration: none;
	}
	
	.detail {
		width:300px;
		/* margin-left: 50px; */
		margin-top: 100px;
	}
	
	.info{
		margin-top: 50px;
		margin-bottom: 50px;
		margin-left: 50px;
		color: white;
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
				<a href="" class="link">내 강의실</a>
			</div>
			<div class="butt">
				<a href="" class="link">출석</a>
			</div>
			<div class="butt">
				<a href="/Jhe/studHomeworkList" class="link">과제</a>
			</div>
			<div class="butt">
				<a href="" class="link">공지사항</a>
			</div>
		</div>
		
	</div>
</nav>

</body>
</html>
