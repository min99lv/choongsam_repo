<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="headerStd.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>수강신청 목록</title>
    <style>
        /* 간단한 스타일 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        /* 모달 스타일 */
        .modal {
            display: none; /* 기본적으로 숨겨짐 */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }

		.modal-content {
		    background-color: #fefefe;
		    padding: 20px;
		    border: 1px solid #888;
		    width: 30%;
		    position: absolute; /* 절대 위치 */
		    top: 50%; /* 화면의 세로 중앙 */
		    left: 50%; /* 화면의 가로 중앙 */
		    transform: translate(-50%, -50%); /* 정확한 중앙 정렬 */
		    text-align: center; /* 텍스트 중앙 정렬 */
		}
		
		button[type="submit"] {
		    margin-top: 20px; /* 버튼과 다른 요소들 간의 간격 */
		    padding: 10px 20px;
		    background-color: #236147; /* 버튼 배경색 */
		    color: white; /* 글자 색 */
		    border: none; /* 테두리 없애기 */
		    cursor: pointer;
		    float: right; /* 오른쪽으로 버튼 정렬 */
		}

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        /* 타이머 텍스트 스타일 */
        .timer {
            font-size: 16px;
            color: red;
        }

    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .pagination a, .pagination .current {
        padding: 1px 5px;
        margin: 0 5px;
        background-color: white;
        color: black;
        text-decoration: none;
        border-radius: 5px;
        align-items: center; 
        justify-content: center;
    }

    .pagination .current {
        background-color: #45a049;
        pointer-events: none;
    }

    .pagination a:hover {
        background-color: #45a049;
    }
        
        
    </style>
</head>
<main>
<body>


<h2>수강신청 목록</h2>


<!-- 수강신청 리스트 테이블 -->
<table>
    <thead>
        <tr>
            <th>번호</th>
            <th>대면/온라인</th>
            <th>강의명</th>
            <th>학생이름</th>
            <th>신청일</th>
            <th>수강상태</th>
            <th>결제일</th>
            <th>결제상태 변경</th> <!-- 결제상태 변경 버튼 추가 -->
        </tr>
    </thead>
    <tbody>
        <!-- 수강신청 리스트를 출력 -->
        <c:forEach var="course" items="${sugangStu}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>
                 <c:choose>
                     <c:when test="${course.ONOFF == '7001'}">대면</c:when>
                     <c:when test="${course.ONOFF == '7002'}">온라인</c:when>
                     <c:otherwise>알 수 없음</c:otherwise>
                 </c:choose>
                 </td>
                <td>${course.LCTR_NAME}</td>
                <td>${course.std_name}</td>
                <td>${course.aply_date}</td>
                 <td>
                    <c:choose>
                        <c:when test="${course.reg_state == '2001'}">수강신청중</c:when>
                        <c:when test="${course.reg_state == '2002'}">수강신청완료</c:when>
                        <c:when test="${course.reg_state == '2003'}">수강중</c:when>
                        <c:when test="${course.reg_state == '2004'}">수강완료</c:when>
                        <c:otherwise>알 수 없음</c:otherwise>
                    </c:choose>
                </td>
                <td>${course.pay_date}</td>

				<td>
				  
				    <c:choose>
				       
				        <c:when test="${course.pay_state == 'Y'}">
				            <span>수납완료</span>
				        </c:when>
				       
				        <c:when test="${(course.pay_state == 'N' or course.pay_state == null) and course.reg_state == '2001'}">
				            <button class="openModalButton" data-lctr-id="${course.lctr_id}" data-user-seq="${course.user_seq}">수납하기</button>
				        </c:when>
				        
				        <c:otherwise>
				            <span>기간만료</span>
				        </c:otherwise>
				    </c:choose>
				</td>

            </tr>
        </c:forEach>
    </tbody>
</table>
    <div class="pagination">
        <c:if test="${page.startPage > page.pageBlock}">
            <a href="/view_Hjh/suganglistStd?currentPage=${page.startPage - page.pageBlock}&keyword=${keyword}">이전</a>
        </c:if>
        <c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
            <c:choose>
                <c:when test="${i == page.currentPage}">
                    <span class="current">${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="/view_Hjh/suganglistStd?currentPage=${i}&keyword=${keyword}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${page.endPage < page.totalPage}">
            <a href="/view_Hjh/suganglistStd?currentPage=${page.startPage + page.pageBlock}&keyword=${keyword}">다음</a>
        </c:if>
    </div> 

<!-- 모달 -->
<div id="paymentModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>수납완료 처리</h2>
        <p>QR결제</p>
        <img src="${pageContext.request.contextPath}/chFile/user/QR.png" alt="QR Code" width="200" />
        <p class="timer" id="timer">남은 시간: 60초</p> <!-- 타이머 표시 -->
        <form id="paymentForm" action="/view_Hjh/updatePayState" method="POST">
            <input type="hidden" name="lctr_id" id="lctrId" />
            <input type="hidden" name="user_seq" id="userSeq" />
            <button type="submit">수납하기</button>
        </form>
    </div>
</div>

<script>
    // 모달 관련 스크립트
    var modal = document.getElementById("paymentModal");
    var btns = document.querySelectorAll(".openModalButton");
    var span = document.getElementsByClassName("close")[0];
    var timerElement = document.getElementById("timer"); // 타이머 엘리먼트
    var countdown; // 카운트다운 변수

    // 각 버튼 클릭 시 모달 열기
    btns.forEach(function(btn) {
        btn.onclick = function() {
            // 버튼에 저장된 data 값 가져오기
            var lctrId = this.getAttribute("data-lctr-id");
            var userSeq = this.getAttribute("data-user-seq");

            // 모달 내 input에 값 설정
            document.getElementById("lctrId").value = lctrId;
            document.getElementById("userSeq").value = userSeq;

            // 모달 표시
            modal.style.display = "block";

            // 타이머 시작 (60초)
            var timeRemaining = 60;
            timerElement.textContent = "남은 시간: " + timeRemaining + "초";
            
            countdown = setInterval(function() {
                timeRemaining--;
                timerElement.textContent = "남은 시간: " + timeRemaining + "초";

                // 1분이 경과하면 모달 자동 닫기
                if (timeRemaining <= 0) {
                    clearInterval(countdown); // 타이머 멈추기
                    modal.style.display = "none"; // 모달 닫기
                }
            }, 1000); // 1초마다 업데이트
        };
    });

    // 모달 닫기
    span.onclick = function() {
        modal.style.display = "none";
        clearInterval(countdown); // 타이머 멈추기
    }

    // 모달 바깥 부분 클릭 시 닫기
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
            clearInterval(countdown); // 타이머 멈추기
        }
    }
</script>

</body>
</main>
</html>
