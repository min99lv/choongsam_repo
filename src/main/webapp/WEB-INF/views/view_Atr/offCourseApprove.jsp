<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Lecture Details</title>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #0a0a0a;
	color: #f2f2f2;
	font-family: Arial, sans-serif;
}

table {
	width: 60%;
	margin: auto;
	border-collapse: collapse;
	background: linear-gradient(145deg, #111111, #333333);
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
	border-radius: 10px;
	overflow: hidden;
}

th, td {
	padding: 15px;
	text-align: left;
	border-bottom: 1px solid #222;
}

th {
	background-color: #222;
	color: #00ffdd;
}

h2 {
	text-align: center;
	color: #ff00ff;
	font-size: 2em;
	text-shadow: 0 0 10px #ff00ff, 0 0 20px #ff00ff, 0 0 30px #ff00ff;
}

.checkIdMsg {
	margin-top: 5px;
	font-size: 14px;
	font-weight: bold;
}

.available {
	color: #00ffdd;
	text-shadow: 0px 0px 5px #00ffdd;
}

.unavailable {
	color: #ff4b4b;
	text-shadow: 0px 0px 5px #ff4b4b;
}

input[type="submit"] {
	background: linear-gradient(90deg, #ff00ff, #00ffdd, #00ff00, #ffdd00, #ff00ff);
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
	margin-top: 20px;
	animation: glow 1.5s ease-in-out infinite alternate;
	transition: all 0.3s ease;
}

input[type="submit"]:hover {
	transform: scale(1.05);
}

input[type="submit"]:disabled {
	background-color: #333;
	color: #777;
	cursor: not-allowed;
}

@
keyframes glow {from { box-shadow:0010px#ff00ff, 0020px#00ffdd, 0030px#00ff00;
	
}

to {
	box-shadow: 0 0 20px #ff00ff, 0 0 30px #00ffdd, 0 0 40px #00ff00;
}

}
.accordion .card-header {
	background-color: #111;
	border-bottom: 1px solid #444;
}

.btn-link {
	color: #00ffdd;
	font-weight: bold;
	text-decoration: none;
}

.btn-link:hover {
	color: #ff00ff;
}

.card-body {
	background-color: #222;
	color: #f2f2f2;
	border: 1px solid #444;
}
</style>

<script type="text/javascript">
    function saveTimeTbl(ahnIndex) {
        var lctr_id  = $('#lctr_id').val();
        var schd =  $('#schd' + ahnIndex).val();
        var lctr_room =  $('#lctr_room' + ahnIndex).val();
        var checkIdMsg = $("#checkIdMsg" + ahnIndex);

        if (lctr_room === "null") {
            checkIdMsg.text("강의실을 지정해 주십시오").removeClass("available").addClass("unavailable");
            checkAllReservations();
            return; 
        }

        $.ajax({
            url: '/overlapCheck',
            type: 'GET',
            dataType: 'json',
            data: {
                'schd': schd,
                'lctr_room': lctr_room
            },
            success: function(data) {
                if (data === true) {
                    checkIdMsg.text("예약 가능합니다").removeClass("unavailable").addClass("available");
                } else {
                    checkIdMsg.text("이미 예약된 시간대입니다").removeClass("available").addClass("unavailable");
                }
                
                checkAllReservations();
            }
        });
    }

    function checkAllReservations() {
        var allAvailable = true;
        $('div[id^="checkIdMsg"]').each(function() {
            if ($(this).text() !== "예약 가능합니다") {
                allAvailable = false;
                return false; 
            }
        });
        
        $('input[type="submit"]').prop('disabled', !allAvailable);
    }

    $(document).ready(function() {
        $('input[type="submit"]').prop('disabled', true);
    });
</script>
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
		<tr>
			<th>시간표</th>
			<td>
				<div class="accordion" id="scheduleAccordion">
					<div class="card">
						<div class="card-header" id="headingOne">
							<h2 class="mb-0">
								<h5>${lecture.lctr_schd}</h5>
								<button class="btn btn-link" type="button"
									data-toggle="collapse" data-target="#collapseOne"
									aria-expanded="true" aria-controls="collapseOne">강의실
									등록</button>
							</h2>
						</div>
						<div id="collapseOne" class="collapse"
							aria-labelledby="headingOne" data-parent="#scheduleAccordion">
							<div class="card-body">
								<form action="approveOfflineCourse">
									<input type="hidden" id="lctr_id" name="lctr_id"
										value="${lctr_id }">
									<table>
										<tr>
											<th>시간</th>
											<th>강의실</th>
										</tr>
										<c:forEach var="schd" items="${schdList}" varStatus="status">
											<input type="hidden" id="schd${status.index}"
												name="schd${status.index}" value="${schd}">
											<tr>
												<td>${schd}</td>
												<td><select id="lctr_room${status.index}"
													name="lctr_room${status.index}"
													onchange="saveTimeTbl(${status.index})">
														<option value="null" selected="selected">지정안됨</option>
														<c:forEach var="classroom" items="${classroomList}">
															<option value="${classroom.room_name}">${classroom.room_name}</option>
														</c:forEach>
												</select>
													<div id="checkIdMsg${status.index}" class="checkIdMsg"></div>
												</td>
												<c:if test="${status.last}">
													<input type="hidden" name="count" value="${status.index}">
												</c:if>
											</tr>
										</c:forEach>
									</table>
							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<input type="submit" value="개설 허가" disabled>
	</form>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
