<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의 신청 상세</title>
    <style>
        /* Page styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f3;
            color: #333;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h2 {
            color: #3e4a2c;
            text-align: center;
            margin-bottom: 20px;
        }

        /* Table styling */
        table {
            width: 60%;
            border-collapse: collapse;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 15px;
            text-align: left;
            font-size: 16px;
        }

        th {
            background-color: #78866b;
            color: #ffffff;
        }

        td {
            background-color: #f4f6e7;
        }

        /* Submit button styling */
        form {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #6d805a;
            color: #ffffff;
            font-size: 16px;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #4e623e;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>강사명</th>
            <td>${lecture.user_name}</td>
        </tr>
        <tr>
            <th>대면여부</th>
            <td>${lecture.onoff}</td>
        </tr>
        <tr>
            <th>분류</th>
            <td>${lecture.lctr_category}</td>
        </tr>
        <tr>
            <th>강의명</th>
            <td>${lecture.lctr_name}</td>
        </tr>
        <tr>
            <th>모집인원</th>
            <td>${lecture.lctr_count}</td>
        </tr>
        <tr>
            <th>모집시작일</th>
            <td>${lecture.rcrt_date}</td>
        </tr>
        <tr>
            <th>수강료</th>
            <td>${lecture.lctr_cost}</td>
        </tr>
        <tr>
            <th>강의상태</th>
            <td>${lecture.lctr_state}</td>
        </tr>
        <tr>
            <th>평가기준</th>
            <td>${lecture.eval_criteria}</td>
        </tr>
        <tr>
            <th>개강일</th>
            <td>${lecture.lctr_start_date}</td>
        </tr>
        <tr>
            <th>차시 수</th>
            <td>${lecture.lctr_cntschd}</td>
        </tr>
    </table>
    <form action="applyCourse">
        <input type="hidden" value="${lecture.lctr_id}" name="lctr_id">
        <input type="hidden" value="10004" name="student_id">
        <input type="submit" value="신청하기">
    </form>
</body>
</html>
