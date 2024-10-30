<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
    
    hr {
	    width: 400px; /* 원하는 너비 */
	    margin: 0 auto; /* 자동 마진으로 가운데 정렬 */
	}

    .signup_section {
	    display: flex;          
	    justify-content: center; /* 자식 요소를 가운데 정렬 */
	    width: 100%;           /* 전체 너비 사용 */   
    }
	
	.signup_step {
	    display: flex;          
	    align-items: center;   
	    height: 100px;    
	    
	}
	
	.step1, .step2, .step3 {
	    width: 270px;          
	    height: 35px;         
	    background-color: #00664F; /* step1의 배경색 */
	    color: white;
	    text-align: center;    
	    line-height: 35px;     
	    border: 1px solid #00664F;
	    margin: 0; /* 마진 제거 */
	}
	
	.step1, .step3 {
	    background-color: #FFFFFF;
	    color: #BCBCBC;
	    border: 1px solid #BCBCBC;
	}
	
	.select {
        width: 250px;
        height: 33px;
        padding: 5px 10px 5px 10px;
        outline: 0 none;
        margin-left: 20px;
    }

    .select option {
        background: white;
        color: black;
        padding: 3px 0;
    }

    input[type="text"], input[type="password"] {
    	width: 205px;
        height: 22px;
        padding: 5px 30px 5px 10px;
        
        margin-left: 20px;
    }
    
    .join_write {
    	 display: flex;           /* Flexbox 사용 */
	    flex-direction: column;  /* 세로 방향으로 정렬 */
	    align-items: center;     /* 자식 요소를 가운데 정렬 */
	    text-align: left;        /* 테이블 셀 안의 텍스트는 왼쪽 정렬 */
	}
	
	tr {
		margin-bottom: 20px;
	}
	
     
     #btnNext {
		width: 250px;
        height: 36px;
        padding: 5px 10px 5px 10px;
        background-color: #00664F;
        color: white;
        border: 1px solid #00664F;
	}
	
	#btnCheck {
		width: 80px;
        height: 36px;
        padding: 5px 10px 5px 10px;
        background-color: #00664F;
        color: white;
        border: 1px solid #00664F;
	}
	
	#btnAddress {
		width: 80px;
        height: 36px;
        padding: 5px 10px 5px 10px;
        background-color: #00664F;
        color: white;
        border: 1px solid #00664F;
	}
</style>
</head>
<body>
        <header>
        	<%@ include file="../header.jsp" %>
        </header>
        
        <main>
          <form action="">
	        <div class="main_container">
			<h2 style="color: #00664F ">회원가입</h2>
			<hr style="width: 400px; margin: 0 auto;">
			<br>
			<p>
	           <div class="signup_section">
	            	<div class="signup_step">
	            		<div class="step1">이용약관</div>
	            		<div class="step2">정보입력</div>
	            		<div class="step3">가입완료</div>
	           		</div>			
	           </div>	
           
           
	            <div class="join_write">
	           		<div class="text_guide">* 필수항목이므로 꼭 작성해주세요</div>
	           		<p>
	           		<table class="">
	           			<tr>
	           				<td>회원유형 *</td>
	           				<td><select name="user_status" class="select" required="required">
		  							<option disabled selected>회원 유형을 선택하세요</option>
		  							<option value="student">학생</option>
		  							<option value="professor">강사</option>
								</select>
							</td>
	           			</tr>
	           			
	           			<tr>
	           				<td>아이디 *</td>
	           				<td><input type="text" placeholder="영문 또는 숫자 입력" required="required"></td>
	           				<td><input id="btnCheck" type="button" value="중복확인">
	           			</tr>
	           			
	           			<tr>
	           				<td>비밀번호 *</td>
	           				<td><input type="password" placeholder="영문 또는 숫자 입력" required="required"></td>
	           			</tr>
	           			
	           			<tr>
	           				<td>비밀번호 확인 *</td>
	           				<td><input type="password" required="required"></td>
	           			</tr>
	           			
	           			<tr>
	           				<td>생년월일 *</td>
	           				<td><input type="text" placeholder="0000-00-00 형식으로 입력" required="required"></td>
	           			</tr>
	           			
	           			<tr>
	           				<td>주소 *</td>
	           				<td><input type="text" required="required"></td>
	           				<td><input id="btnAddress" type="button" value="찾기">
	           			</tr>
	           			
	           			<tr>
	           				<td>메일 *</td>
	           				<td><input type="text" required="required"></td>
	           			</tr>
	           			
	           			<tr>
	           				<td>전화번호 *</td>
	           				<td><input type="text" placeholder="000-0000-0000 형식으로 입력" required="required"></td>
	           			</tr>
	           				           		           			
	           		</table>
	            </div>
           
	       </div>
	        	
	        	<div class="btnSection" align="center" style="margin-bottom: 20px;">
					<input id="btnNext" type="submit" onclick="location.href='signup3'"value="회원가입">
	            </div>
	      </form>
		</main>
        <footer>
        </footer>
    </body>
</html>