<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>쪽지 작성</title>
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
            margin-bottom: 200px;
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
    
        button{
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
        
        
        	/* 모달 기본 스타일 */
			.modal {
				display: none;
				/* 기본적으로 숨김 */
				position: fixed;
				z-index: 1;
				left: 0;
				top: 0;
				width: 100%;
				height: 100%;
				background-color: rgba(0, 0, 0, 0.5);
			}

			/* 모달 콘텐츠 스타일 */
			.modal-content {
				background-color: #fff;
				margin: 15% auto;
				padding: 20px;
				border: 1px solid #888;
				width: 50%;
			}

			.close {
				color: #aaa;
				float: right;
				font-size: 28px;
				font-weight: bold;
			}

			.close:hover,
			.close:focus {
				color: #000;
				text-decoration: none;
				cursor: pointer;
			}
        
        

    </style>
    <script type="text/javascript">

    
    
    function openRecipientModal(userSeq) {
    	  console.log("openRecipientModal called with userSeq:", userSeq);
        fetch(`/api/lectures/my?userSeq=${userSeq}`)
            .then(response => response.json())
            .then(data => {
                const lectureList = document.getElementById('lectureList');
                lectureList.innerHTML = ''; // 목록 초기화
                data.forEach(lecture => {
                    const li = document.createElement('li');
                    li.textContent = lecture.lctrName; // 강의명 설정
                    li.onclick = () => fetchRecipients(lecture.lctrId); // 강의 클릭 시 수신자 목록 조회
                    lectureList.appendChild(li);
                });
                document.getElementById('recipientModal').style.display = 'block'; // 모달 열기
            });
    }

    function fetchRecipients(lectureId) {
        fetch(`/api/lectures/${lectureId}/recipients`)
            .then(response => response.json())
            .then(data => {
                const recipientList = document.getElementById('recipientList');
                recipientList.innerHTML = ''; // 목록 초기화
                data.forEach(recipient => {
                    const li = document.createElement('li');
                    li.textContent = recipient.userName; // 이름 설정
                    li.setAttribute('data-user-seq', recipient.userSeq); // 사용자 번호 저장
                    li.onclick = selectRecipient; // 선택 시 처리
                    recipientList.appendChild(li);
                });
            });
    }

    function selectRecipient(event) {
        const selectedUserSeq = event.target.getAttribute('data-user-seq');
        const selectedName = event.target.textContent;
        
        // 수신자 목록을 화면에 추가하는 로직
        addRecipientToDisplay(selectedUserSeq, selectedName);
    }

    function addRecipientToDisplay(userSeq, name) {
        const recipientDisplay = document.getElementById('recipientDisplay'); // 수신자 표시 공간
        const li = document.createElement('li');
        li.textContent = name;
        li.setAttribute('data-user-seq', userSeq); // 데이터 속성 추가
        recipientDisplay.appendChild(li); // 화면에 추가
    }


      </script>
</head>
<body>
    <header>
        <%@ include file="../header.jsp" %>
    </header>

    <div class="container">
        <div class="contents">
            <h1>쪽지 작성</h1>
        </div>

        <form method="post" action="/api/notes">
        
        
        <input type="hidden" name="sndpty_seq" value="${sessionScope.user_seq}">
        <input type="hidden" name="sndpty_note_yn" value="N">
        <input type="hidden" name="rcvr_note_yn" value="N">

            <table class="list">
                <tr>
                    <th>받는사람</th>
                    <td><input type="text" name="rcvr_seq" ><button onclick="openRecipientModal(${sessionScope.user_seq})">추가</button></td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="note_ttl" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="note_cn" rows="10" required></textarea></td>
                </tr>
            </table>
            
           <button type="submit" >작성완료</button>
        </form>
    </div>
    
    
    <div id="recipientModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>받는 사람 추가</h2>
        <h3>내 강의 목록</h3>
        <ul id="lectureList"></ul>
        <h3>받는 사람 목록</h3>
        <ul id="recipientList"></ul>
        <button id="sendMessageButton">쪽지 보내기</button>
    </div>
</div>
</body>
</html>
