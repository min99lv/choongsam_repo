<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>강의 등록</title>
<!-- Bootstrap CSS 추가 -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
    /* 페이지 스타일 */
    body {
        background-color: #f9f8f3;
        font-family: Arial, sans-serif;
        color: #333;
        padding: 30px;
    }

    h2 {
        color: #556b2f;
        font-weight: bold;
        text-align: center;
        margin-bottom: 30px;
        font-size: 2em;
        text-shadow: 1px 1px 5px rgba(85, 107, 47, 0.6);
    }

    .form-group label {
        font-weight: bold;
        color: #556b2f;
    }

    .form-group input, .form-group select {
        border-radius: 8px;
        border: 1px solid #b8c095;
        padding: 10px;
        background-color: #f1f1eb;
        color: #333;
        transition: border-color 0.3s;
    }

    .form-group input:focus, .form-group select:focus {
        border-color: #6b8e23;
    }

    /* 버튼 스타일 */
    .btn-primary {
        background: linear-gradient(90deg, #556b2f, #6b8e23);
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 12px 25px;
        font-size: 16px;
        font-weight: bold;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        box-shadow: 0 4px 10px rgba(85, 107, 47, 0.3);
    }

    .btn-primary:hover {
        transform: scale(1.05);
        box-shadow: 0 5px 15px rgba(85, 107, 47, 0.4);
        background: linear-gradient(90deg, #6b8e23, #556b2f);
    }

    /* 시간표 모달 스타일 */
    .modal-header {
        background-color: #556b2f;
        color: #fff;
    }

    th, td {
        padding: 0.75rem;
        text-align: center;
        cursor: pointer;
        font-size: 0.9em;
    }

    th {
        background-color: #e7e8e2;
        color: #556b2f;
    }

    td {
        background-color: #f9f8f3;
        transition: background-color 0.3s;
    }

    td:hover, .selected {
        background-color: #6b8e23;
        color: #fff;
        font-weight: bold;
    }

    /* 선택한 시간 표시 */
    #selectionDisplay {
        background-color: #e1edc8;
        color: #556b2f;
        padding: 12px;
        text-align: center;
        border-radius: 8px;
        font-weight: bold;
        margin-top: 20px;
    }
</style>
<script>
    function homeworkEval() {
        const selectedValue = parseInt(document.getElementById("attendanceEval").value);
        document.getElementById("displayValue").innerText = 100 - selectedValue;
    }

    // 선택한 시간을 저장할 배열
    const selectedTimes = [];

    function toggleTimeSelection(day, hour, cell) {
        const timeSlot = day + "요일" + hour + "시";
        const index = selectedTimes.indexOf(timeSlot);

        if (index === -1) {
            selectedTimes.push(timeSlot);
            cell.classList.add("selected");
        } else {
            selectedTimes.splice(index, 1);
            cell.classList.remove("selected");
        }

        document.getElementById("selectionDisplay").innerText = "선택한 시간: " + selectedTimes.join(", ");
        document.getElementById("selectedTimesInput").value = selectedTimes.join(", ");
    }

    function submitForm() {
        document.getElementById("selectedTimesInput").value = selectedTimes.join(", ");
        document.getElementById("timeSelectionForm").submit();
    }
</script>
</head>
<body>
    <div class="container mt-5">
        <h2>강의 등록</h2>
        <form action="registerCourse" id="timeSelectionForm" method="post">
            <div class="form-group">
                <label>사진</label>
                <p>추후 업뎃 예정</p>
            </div>
            <div class="form-group">
                <label>강의명</label>
                <input type="text" name="lctr_name" required>
            </div>
            <div class="form-group">
                <label>수강 인원</label>
                <input type="number" name="lctr_count">
            </div>
            <div class="form-group">
                <label>대면 여부</label>
                <select name="onoff">
                    <option value="7001">대면</option>
                    <option value="7002">비대면</option>
                </select>
            </div>
            <div class="form-group">
                <label>모집 시작일</label>
                <input type="date" name="rcrt_date">
            </div>
            <div class="form-group">
                <label>개강일</label>
                <input type="date" name="lctr_start_date">
            </div>
            <div class="form-group">
                <label>총 차시</label>
                <input type="number" name="lctr_cntschd">
            </div>
            <div class="form-group">
                <label>평가기준: 출석</label>
                <select id="attendanceEval" name="attendanceEval" onchange="homeworkEval()">
                    <c:forEach var="i" begin="0" end="100" step="10">
                        <option value="${100 - i}">${100 - i}%</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>과제: <span id="displayValue">0</span>%</label>
            </div>
            <div class="form-group">
                <label>수강료</label>
                <input type="number" name="lctr_cost">
            </div>

            <div class="form-group text-center">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#timeSelectionModal">시간표 선택하기</button>
            </div>

            <p id="selectionDisplay">선택한 시간:</p>
            <input type="hidden" id="selectedTimesInput" name="lctr_schd">
            <input type="submit" value="확인" class="btn btn-primary">
        </form>

        <!-- 시간표 모달 -->
        <div class="modal fade" id="timeSelectionModal" tabindex="-1" role="dialog" aria-labelledby="timeSelectionModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="timeSelectionModalLabel">시간표 선택</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <c:set var="days">월,화,수,목,금</c:set>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>시간</th>
                                    <c:forEach var="day" items="${days}">
                                        <th>${day}요일</th>
                                    </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="hour" begin="9" end="21">
                                    <tr>
                                        <th>${hour}시</th>
                                        <c:forEach var="day" items="${days}">
                                            <td onclick="toggleTimeSelection('${day}', ${hour}, this)">${day}요일 ${hour}시</td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- Bootstrap JS, Popper.js 및 jQuery 추가 -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


	</form>

</body>
</html>