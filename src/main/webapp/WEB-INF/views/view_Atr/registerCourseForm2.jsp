<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../headerGreen.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Registration</title>
    
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* General Styles */
        body {
            background-color: #fff;  /* 배경 흰색 */
            color: #333;
            font-family: Arial, sans-serif;

        }

        h2 {
            text-align: center;
            color: #00664F; /* 포인트 색상 */
            font-size: 2em;
            font-weight: bold;
            margin-bottom: 20px;
            text-shadow: 1px 1px 5px rgba(0, 102, 79, 0.6);
        }

        form {
            width: 70%;
            margin: auto;
            background-color: #ffffff;
            padding: 20px 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);
        }

        label {
            font-weight: bold;
            color: #00664F; /* 포인트 색상 */
            margin-top: 10px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f8f5;
            color: #333;
        }

        input[type="submit"] {
            background: linear-gradient(90deg, #00664F, #008C60); /* 포인트 색상 */
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            width: 100%;
            margin-top: 20px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            box-shadow: 0 4px 10px rgba(0, 102, 79, 0.3);
        }

        input[type="submit"]:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(0, 102, 79, 0.4);
            background: linear-gradient(90deg, #008C60, #00664F); /* 포인트 색상 */
        }

        /* Hidden input styling */
        input[name="classCount"], input[name="lctrId"] {
            display: none;
        }
    </style>
</head>
<body>
    <h2>Course Registration</h2>
    <form action="registerCourse2" method="post">
        <label>강의 목표:</label>
        <input type="text" name="course0" >

        <c:forEach var="i" begin="1" end="${classCount}">
            <label>${i}주차 강의 개요:</label>
            <input type="text" name="course${i}" placeholder="${i}주차 강의 계획">
        </c:forEach>

        <input type="hidden" value="${classCount}" name="classCount">
        <input type="hidden" value="${CourseId}" name="lctrId">
        
        <input type="submit" value="Submit">
    </form>
</body>
<footer><%@ include file="../footer.jsp"%></footer>
</html>
