<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>

</head>

<body>

<nav>
	<div class="nav">
		<ol>
			<li>
				<a href="">내 강의실</a>
			</li>
			<li>
				<a href="">출석</a>
			</li>
			<li>
				<a href="">과제</a>
			</li>
			<li>
				<a href="">공지사항</a>
			</li>
		</ol>
	</div>
</nav>

</body>
</html>