<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../headerGreen.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의 상세</title>
    <style>
        /* General Page Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #fff;  /* 배경 흰색 */
            color: #333;
            margin: 0;
            flex-direction: column;
            align-items: center;
            box-sizing: border-box;
        }

        h2 {
            color: #00664F;  /* 포인트 색상 */
            margin-bottom: 30px;
            text-align: center;
            font-size: 2em;
            text-shadow: 1px 1px 5px rgba(0, 102, 79, 0.3);
        }

        /* Table Styling */
        table {
        	margin-left:auto;
        	margin-right:auto;
            width: 40%;
            border-collapse: collapse;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
            margin-top: 20px;
            margin-bottom: 50px;
        }

        th, td {
            padding: 15px;
            font-size: 16px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #00664F;  /* 포인트 색상 */
            color: #ffffff;
            font-weight: bold;
            text-transform: uppercase;
        }

        td {
            background-color: #f4f9f5;
            color: #333;
        }

    

        tr:hover td {
            background-color: #e2f7e1;  /* Hover effect */
        }

        /* Box Shadow for the Table */
        table {
            box-shadow: 0 4px 12px rgba(0, 102, 79, 0.2);
        }

        /* Add a Bottom Border for the Table */
        td:last-child {
            border-bottom: none;
        }

        /* Add a Button Style (if needed in the future) */
        .btn {
            background-color: #00664F;  /* 포인트 색상 */
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .btn:hover {
            background-color: #004e3c;  /* Darker shade of the point color */
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <h2>강의 상세</h2>
    <table>
        <tr>
            <th>강사ID</th>
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


</body>
<footer><%@ include file="../footer.jsp"%></footer>
</html>
