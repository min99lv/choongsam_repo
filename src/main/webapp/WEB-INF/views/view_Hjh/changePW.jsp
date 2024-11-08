<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="headerStd.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
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
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

	<div class="form-container">
	    <h2>비밀번호 변경</h2>
	${user_id}${user_id}
	    <form action="changePassword" method="post">
	        <div>
	            <label for="user_id">아이디 (사용자 ID):</label>
	            <input type="hidden" id="user" name="user_id" value="${user_id}" readonly />
	            <p>아이디: ${user_id}</p>  <!-- 사용자 아이디 출력 -->
	        </div>
	
	        <!-- 기존 비밀번호 입력 필드 -->
	        <div>
	            <label for="old_password">현재 비밀번호:</label>
	            <input type="password" id="old_password" name="old_password" required />
	        </div>
	
	        <!-- 새 비밀번호 입력 필드 -->
	        <div>
	            <label for="new_password">새 비밀번호:</label>
	            <input type="password" id="new_password" name="new_password" required />
	        </div>
	
	        <!-- 새 비밀번호 확인 입력 필드 -->
	        <div>
	            <label for="confirm_password">새 비밀번호 확인:</label>
	            <input type="password" id="confirm_password" name="confirm_password" required />
	        </div>
	
	        <div>
	            <button type="submit">비밀번호 변경</button>
	        </div>
	    </form>

	    <!-- 실패 메시지 또는 성공 메시지 출력 -->
	    <p style="color: red;">${msg}</p>
	</div>

</body>
</html>
