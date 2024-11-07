<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>쪽지 상세</title>
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
		    height: 200px;
		    padding: 20px;
		    overflow-y: auto; /* 세로 스크롤 추가 */
    		word-wrap: break-word; /* 긴 단어 줄바꿈 */
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
       .contentsss {
		    padding: 20px;
		    font-size: 16px;
		    line-height: 1.6; /* 내용의 줄 간격 조정 */
		    white-space: normal; /* 텍스트 줄바꿈 정상 처리 */
		    word-wrap: break-word; /* 긴 단어는 자동으로 줄바꿈 처리 */
		    overflow-wrap: break-word; /* 단어가 길어지면 줄바꿈 처리 */
		}

        .answer-row {
            background-color: #f0f8ff;
        }
        
.contentsss, .answer-content {
  vertical-align: top; /* 내용 위쪽 정렬 */
    padding: 20px;
    font-size: 16px;
    line-height: 1.6;
    white-space: pre-wrap; /* 텍스트 줄바꿈 자동 처리 */
    word-wrap: break-word;
    overflow-wrap: break-word;
    overflow-y: auto;
    height: 200px;
    
}
    </style>
    <script type="text/javascript">
    async function fetchAskDetail() {
        try {
            const response = await fetch(`/api/asks/${dscsn_sn}`);
            if (!response.ok) {
                throw new Error(`Error fetching notice: ${response.status}`);
            }
            const note = await response.json();

            console.log(note); // note 객체를 콘솔에 출력하여 구조 확인

            // 제목과 내용을 가져와서 표시
            document.getElementById('dscsn_ttl').textContent = note.dscsn_ttl;
            document.getElementById('dscsn_cn').textContent = note.dscsn_cn;

            // 답변이 존재하면 답변 표시
            if (note.dscsn_ans_yn === 'Y' && note.dscsn_ans_cn) {
                // 답변 내용이 있을 경우에만 보여주기
                document.getElementById('dscsn_ans_cn').textContent = note.dscsn_ans_cn;
                // 답변 영역을 보이게 설정
                document.getElementById('dscsn_ans').style.display = 'table-row'; 
            } else {
                document.getElementById('dscsn_ans_cn').textContent = "답변이 아직 등록되지 않았습니다.";
                // 답변 영역을 숨김
                document.getElementById('dscsn_ans').style.display = 'none';
            }

        } catch (error) {
            console.error('Error:', error);
            document.getElementById('dscsn_ttl').textContent = "문의사항을 불러올 수 없습니다.";
            document.getElementById('dscsn_cn').textContent = error.message;
        }
    }

    window.onload = fetchAskDetail;
      </script>
</head>
<body>
    <header>
        <%@ include file="../header.jsp" %>
    </header>

    
    <div class="container">
        <div class="contents">
            <h1>1:1 문의사항</h1>
        </div>
            <table class="list">
                <tr>
                    <th>제목</th>
                    <td id="dscsn_ttl"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td class="contentsss" id="dscsn_cn" ></td>
                </tr>
			<tr id="dscsn_ans" class="answer-row" style="display: none;">
                <th>답변</th>
                <td class="answer-content" id="dscsn_ans_cn"></td>
            </tr>
		</table>
            
            <button onclick="history.back();">목록</button>
    </div>
    
</body>
</html>
