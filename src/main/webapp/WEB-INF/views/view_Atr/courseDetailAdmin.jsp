<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의 상세</title>
    <style>
        /* General Page Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f3;
            color: #333;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h2 {
            color: #3e4a2c;
            margin-bottom: 20px;
            text-align: center;
        }

        /* Table Styling */
        table {
            width: 80%;
            border-collapse: collapse;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            font-size: 15px;
            text-align: left;
        }

        th {
            background-color: #78866b;
            color: #ffffff;
            font-weight: bold;
        }

        td {
            background-color: #f4f6e7;
        }

        /* Zebra Stripes for Rows */
        tr:nth-child(even) td {
            background-color: #e8eadb;
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
</html>
