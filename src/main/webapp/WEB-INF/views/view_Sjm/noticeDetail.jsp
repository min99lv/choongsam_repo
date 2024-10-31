<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 상세</title>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        .container {
            display: flex;
            flex-direction: column;
            position: relative;
            background-color: #fdfdfd;
            top: 120px;
            width: 1320px;
            margin: 0 auto;
            box-sizing: border-box;
            height: auto;
            padding: 20px; /* 여백 추가 */
            font-size: 20px;
        }

        .list {
            margin: 50px 0;
        }

        table {
            padding: 0;
            width: 1280px;
            border-top: 2px solid black;
        }

        th {
            width: 200px;
            color: gray;
            font-weight: bold;
            text-align: center;
            padding: 20px 0;
            border-bottom: 0.4px solid #e2e8ee;
            font-size: 20px;
        }

        td {
            border-bottom: 0.4px solid #e2e8ee;
            border-left: 0.4px solid #e2e8ee;
            cursor: pointer;
            font-weight: bold;
            text-align: center;
            color: #323232;
            font-size: 14px;
            height: 50px;
        }

        .manager_pagination {
            text-align: center;
        }

        h1 {
            font-size: 40px;
        }

        input {
            width: 950px;
            height: 50px;
            font-size: 20px;
            border: #949494 1px solid;
            border-radius: none;
        }

         /* 기존 스타일 및 추가 스타일 */
         .file-preview {
            margin-top: 10px;
            max-width: 100%;
            height: auto;
            border: 1px solid #ddd;
            padding: 10px;
        }

        textarea {
            width: 950px;
            height: 400px;
            font-size: 20px;
            border: #949494 1px solid;
            border-radius: none;

        }
    </style>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.css" />
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    <script type="text/javascript">


      </script>
</head>
<body>
    <header>
        <%@ include file="../header.jsp" %>
    </header>

    <div class="container">
        <div class="contents">
            <h1>공지사항 작성</h1>
        </div>

        <form id="noticeForm" method="post" action="your_action_url_here">

            <table class="list">
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td><input type="text" name="author" required></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>
                        <input type="file" name="attachment" onchange="uploadAndInsertImage()">
                        <div id="filePreview"></div>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" rows="10" required></textarea></td>
                </tr>
            </table>
            <button type="button" onclick="submitForm()">작성 완료</button>
        </form>
    </div>
</body>
</html>
