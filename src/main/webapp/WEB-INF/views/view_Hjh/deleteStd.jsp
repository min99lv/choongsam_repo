<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="headerStd.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원탈퇴</title>
    <style>
        .form-container {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
        }
        .form-container label {
            font-size: 16px;
            margin-bottom: 10px;
        }
        .form-container input {
            width: 100%;
            padding: 8px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>

		<div class="form-container">
		    <h2>회원 탈퇴</h2>
		
		    <form action="deleteStd1" method="post">
		        <div>
		            <label for="user_id">아이디 (사용자 ID):</label>
		            <input type="hidden" id="user" name="user_id" value="${user_id}" readonly />
		            <!-- user_id 출력 -->
		            <p>아이디: ${user_id}</p>  <!-- user_id가 정상적으로 전달되었는지 확인 -->
		        </div>
		
		        <!-- 비밀번호 입력 필드 -->
		        <div>
		            <label for="password">비밀번호:</label>
		            <input type="password" id="password" name="password" required />
		        </div>
		
		        <div>
		            <button type="submit">탈퇴하기</button>
		        </div>
    </form>

    <!-- 탈퇴 실패 메시지 또는 기타 안내 메시지 출력 -->
    <p style="color: red;">${msg}</p>
</div>

  


</body>
</html>
