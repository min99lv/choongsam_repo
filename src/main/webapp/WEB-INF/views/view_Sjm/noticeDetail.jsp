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
            color: #323232;
            font-size: 14px;
            height: 50px;
             padding: 20px;
              font-size: 20px;
        }
        
		.contentsss{
		    vertical-align: top; /* 내용 위쪽 정렬 */
		    height: 500px;
		    padding: 20px;
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
        
         button {
            width: 200px;
            text-align: center;
            /* 버튼 가운데 정렬을 위한 추가 스타일 */
            margin: 20px auto; /* 버튼을 가운데 정렬 */
            display: block; /* 블록으로 설정 */
            height: 50px;
            background-color: #00664F;
            border: none;
            color: white;
        }
    </style>
    <script type="text/javascript">

    // URL에서 공지사항 번호(ntc_mttr_sn) 추출
    const urlParams = new URLSearchParams(window.location.search);
    const ntc_mttr_sn = "${ntc_mttr_sn}";  // 서버에서 전달받은 파라미터를 JavaScript 변수로 사용
    console.log(ntc_mttr_sn); // 확인용

    // 공지사항 데이터 가져오기
    async function fetchNoticeDetail() {
        try {
            const response = await fetch(`/api/notice/${ntc_mttr_sn}`);
            if (!response.ok) {
                throw new Error(`Error fetching notice: ${response.status}`);
            }
            const notice = await response.json();

            // 가져온 데이터를 HTML 요소에 넣기
            document.getElementById('notice.ntc_mttr_ttl').textContent = notice.ntc_mttr_ttl;
            document.getElementById('notice.ntc_mttr_cn').textContent = notice.ntc_mttr_cn;
        } catch (error) {
            console.error('Error:', error);
            document.getElementById('notice.ntc_mttr_ttl').textContent = "공지사항을 불러올 수 없습니다.";
            document.getElementById('notice.ntc_mttr_cn').textContent = error.message;
        }
    }

    // 페이지가 로드되면 공지사항 데이터를 가져옵니다.
    window.onload = fetchNoticeDetail;
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
            <table class="list">
                <tr>
                    <th>제목</th>
                    <td id="notice.ntc_mttr_ttl"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td class="contentsss" id="notice.ntc_mttr_cn" ></td>
                </tr>
            </table>
            
            <button onclick="history.back();">목록</button>
    </div>
    
</body>
</html>
