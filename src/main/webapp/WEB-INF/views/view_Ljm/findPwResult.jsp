<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style type="text/css">
	 body {
        margin: 0;
        padding: 0;
        font-family: 'Noto Sans KR', sans-serif;
    }

    header {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        background-color: white; /* 배경색을 추가하여 가려지지 않게 함 */
        z-index: 1000; /* 다른 요소보다 위에 위치하도록 */
        border-bottom: 1px solid #ccc; /* 헤더 아래쪽 경계선 */
    }

    footer {
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        background-color: white; /* 배경색 추가 */
        z-index: 1000; /* 다른 요소보다 위에 위치하도록 */
        border-top: 1px solid #ccc; /* 푸터 위쪽 경계선 */
    }

    .main_container {
        margin-top: 60px; /* 헤더 높이에 맞게 마진 설정 */
        margin-bottom: 60px; /* 푸터 높이에 맞게 마진 설정 */
        margin-left: 100px;
        margin-right: 100px;
        padding: 20px; /* 콘텐츠 여백 */
        text-align: center; /* 가운데 정렬 */
    }
    
    #btnLogin {
		width: 250px;
        height: 36px;
        padding: 5px 10px 5px 10px;
        background-color: #00664F;
        color: white;
        border: 1px solid #00664F;
        text-align: center;
	}
</style>
</head>
<body>
	<header>
        <%@ include file="../header.jsp" %>
    </header>
    
    <main>
      <div class="main_container">
    	<form action="findIdResult" method="post">
		  
	        <h2 style="color: #00664F;">비밀번호 찾기</h2>
	        <hr style="width: 400px; margin: 0 auto;">
	        <br>	
	
	        <br>
	
	        <!-- 아이디 찾기 결과 -->
	        <div class="result_message">
	            <p style="font-size: 18px; font-weight: bold; color: #00664F;">
	                ${userCheckMessage}
	            </p>
	
	            <c:if test="${not empty find_id}">
	                <p style="font-size: 16px; color: #00664F;">
	                    <strong>아이디: </strong>${find_id}
	                </p>
	            </c:if>
	        </div>	
	        <br>		              	      
	    </form>
	    	<form action="loginForm" method="post">
		    	<div class="button_section">
			        <input id="btnLogin" type=submit value="로그인 화면으로">
			    </div>  	
		    </form>
	      </div>
    </main>
</body>
</html>