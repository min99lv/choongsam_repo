<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="headerAdmin.jsp"%>


<!DOCTYPE html>
<html>
<head>





<main>

    <form action="/view_Hjh/detailProfileEdit" method="post">
    <input type="hidden" name=user_seq value="${user_seq}">
        <input type="hidden" name=user_id value="${user_id}"> <!-- 사용자 ID -->
        <table>
 		    <tr>
                <th>ID</th>
                <td>${user_id}</td>
            </tr>        
            <tr>
                <th>이름</th>
                <td><input type="text" name="user_name" value="${user_name}" required /></td>
            </tr>
            <tr>
                <th>이메일</th>
                <td><input type="email" name="email" value="${email}" required /></td>
            </tr>
            <tr>
                <th>주소</th>
                <td><input type="text" name="address" value="${address}" required /></td>
            </tr>
            <tr>
                <th>핸드폰 번호</th>
                <td><input type="text" name="phone_num" value="${phone_num}" required /></td>
            </tr>
 		    <tr>
                <th>birth</th>
                <td><input type="text" name="birth" value="${birth}" required /></td>
            </tr>
        </table>
       <input type="submit" value="수정1">
    </form>

</main>
</body>
</html>
