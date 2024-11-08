<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<<<<<<< HEAD
=======
<%@ include file="../myStudyHomeNav.jsp" %>
<%@ include file="../headerGreen.jsp" %>
>>>>>>> develop

<style>

	a{
		text-decoration: none;
	}
	
	#listBody{
<<<<<<< HEAD
		width: 1560px;
=======
		width: 1582px;
>>>>>>> develop
		height: 100%;
		background-color: #F1F1F1;
		float: right;
	}
	
	.infor {
		height: 130px;
<<<<<<< HEAD
		text-align: center;
		background-color: white;
=======
		background-color: white;
		padding: 15px;
	}
	
	#inforText {
		width: 1400px;
		text-align:center;
>>>>>>> develop
	}
	
	#lectureName {
		width: 1560px;
		height: 119px;
		text-align: center;
		font-size: 45px;
	}
	
	.teachName {
		margin-top: 20px;
	}
	
	#teachName {
		font-size: 25px;
		color: #A6A6A6;
		margin-bottom: 20px;
	}
	
	.list {
		width: 1370px;
		height: 173px;
		background-color: white;
		margin-bottom: 11px;
		display: flex;
		padding: 15px;
		margin-top: 15px;
		margin-left: 15px;
		border-radius: 8px;
<<<<<<< HEAD
=======
		box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3); /* 마우스 오버 시 그림자 */
>>>>>>> develop
	}
	
	.thumbnailDiv {
		height: 160px;
		width: 250px;
	}
	
	#thumbnail {
		height: 170px;
		border-radius: 17px;
	}
	
	.vdoInfor {
		width: 566px;
		display: flex;
		margin-left: 90px;
	}
	
	.buttStatus {
		width: 240px;
		height: 86px;
		margin-left: 50px;
		font-size: 25px;
		font-weight: bold;
	}
	
	#chashi {
		font-size: 25px;
		color: #BBBBBB;
		width: 250px;
		height: 43px;
		margin-bottom: 20px;
	}
	
	#gigan {
		width: 485px;
	}
	
	#perTxt {
		font-size: 15px;
		color: #D9D9D9;
		margin-right: 5px;
	}
	
	#viewingPeriod {
		font-size: 15px;
		color: #BBBBBB;
		width: 565px;
		text-align: right;
		margin-left: 110px;
	}
	
	#title {
		font-size: 35px;
	}
	
	#startDiv {
		width:246px;
		height: 81px;
		background-color: #00664F;
		color: white;
	    display: flex;            /* Flexbox 사용 */
	    align-items: center;     /* 수직 중앙 정렬 */
   		justify-content: center;  /* 수평 중앙 정렬 */
		border-radius: 8px;
	}
	
	.chashiGigan{
		display: flex;
	}
	
	.percent {
		font-size: 20px;
		color: #D9D9D9;
		margin-left: 5px;
	}
	
	#meter{
		width: 460px;
		height: 30px;
	}
	
	.state {
	    width: 242px;
	    height: 78px;
	    margin-bottom: 10px;
	    display: flex;            /* Flexbox 사용 */
	    align-items: center;     /* 수직 중앙 정렬 */
	    justify-content: center;  /* 수평 중앙 정렬 */
	    text-align: center;      /* 텍스트 중앙 정렬 */
		border-radius: 8px;
<<<<<<< HEAD
=======
		border: 2px #00664F solid; 
		color: #00664F;
>>>>>>> develop
	}
	
	.chulseokDiv {
	    border: 2px #00664F solid; /* 출석일 때 테두리 색 */
	    color: #00664F;
	    
	}
	
	.gyeolseokDiv {
	    border: 2px #C51616 solid; /* 결석일 때 테두리 색 */
	    color: #C51616;
	}
	
	.misugangDiv {
	    border: 2px #BBBBBB solid; /* 미수강일 때 테두리 색 */
	    color: #BBBBBB;
	}
	
	.jindo {
		display: flex;
		align-items: center;
		margin-top: 25px;
	}
	
<<<<<<< HEAD
=======
	#contsFix {
		color: black;
	}
	
	.uploadButt {
		margin: 20px 165px 20px 0px;
		float: right;
		background-color: #00664F;
		color: white;
		font-size: 20px;
		padding: 20px;
		border-radius: 8px;
		box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3); 
	}
	
>>>>>>> develop
	
	
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
<<<<<<< HEAD
	function chkClassSche() {
	    $.ajax({
	        type: "POST",
	        url: "/classScheChk",
	        data: { 
	            "chashi": $('#chashiHidden').val(),
	            "conts_id": $('#contsIdHidden').val(),
	            "user_seq": $('#userSeqHidden').val(),
	            "lctr_id": $('#lctrIdHidden').val()
	        },
	        success: function(result) {
	            if (result == 0) { 
	                console.log('입력 실패');
	            } else {
	                console.log('입력 성공');
	            }
	        },
	        error: function() {
	            alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
	            return 0;
	        }
	    });
	};
=======
>>>>>>> develop
	
	// 페이지 로드 후 상태에 따라 클래스 추가하는 함수
	document.addEventListener("DOMContentLoaded", function() {
	    var stateTxt = document.getElementById('stateTxt');
	    
	    // 상태에 따라 적절한 클래스 추가
	    if (stateTxt.innerText === '출석') {
	        stateTxt.classList.add('chulseokDiv'); 
	    } else if (stateTxt.innerText === '결석') {
	        stateTxt.classList.add('gyeolseokDiv'); 
	    } else {
	        stateTxt.classList.add('misugangDiv');
	    }
	});
</script>

<<<<<<< HEAD
=======


>>>>>>> develop
<body>
	<div id="listBody">
		<div class="infor">
<<<<<<< HEAD
			<span id="lectureName">
=======
			<div id="inforText">
				<span id="lectureName">
>>>>>>> develop
				${lectName }
			</span>
			<div class="teachName">
				<span id="teachName">
					${teacherName } 강사님
				</span>
			</div>
<<<<<<< HEAD
		</div>
		<div class="notice">
			
		</div>
	
	
		<a href="/contsUploadForm?lctr_id=${lctr_id }&user_seq=${user_seq}">강의등록</a>
=======
			</div>
		</div>
	
		
		<div class="uploadButt">
			<a href="/contsUploadForm?lctr_id=${lctr_id }&user_seq=${user_seq}" id="contsInsert">${lectName } 강의 등록</a>
		</div>
>>>>>>> develop
		
		<c:forEach var="conts" items="${contentList}">
			<div class="list">
				<div class="thumbnailDiv">
					<img id="thumbnail" alt="유튜브 썸네일" src="https://img.youtube.com/vi/${conts.vdo_url_addr }/maxresdefault.jpg">
				</div>
				
				<div class="vdoInfor">
					<div class="infor2">
						<div class="time">
						<div class="chashiGigan">
						    <div id="chashi">${conts.lctr_no}주차</div>
						    <input type="hidden" value="${conts.lctr_no}" id="chashiHidden">
						    <input type="hidden" value="${conts.conts_id}" id="contsIdHidden">
						    <input type="hidden" value="${conts.user_seq}" id="userSeqHidden">
						    <input type="hidden" value="${conts.lctr_id}" id="lctrIdHidden">
						    <div id="gigan">
						        <c:choose>
						            <c:when test="${conts.lctr_no == 1}">
						               <span id="viewingPeriod"> ${conts.lctr_start_date} ~ ${conts.viewing_period}</span>
						            </c:when>
						            <c:otherwise>
						               <span id="viewingPeriod"> ${conts.befor_period} ~ ${conts.viewing_period}</span>
						            </c:otherwise>
						        </c:choose>
						    </div>
					    </div>
					</div>
					
					<span id="title">${conts.vdo_file_nm }</span>
					</div>
					
					<div class="buttStatus">
<<<<<<< HEAD
						<a href="****************************************">
=======
						<a href="/contsUpdateForm?conts_id=${conts.conts_id}&lctr_id=${lctr_id }" id="contsFix">
>>>>>>> develop
							<div class="state">
			                    강의 수정
			                </div>
			            </a>
<<<<<<< HEAD
						<a href="/video-details?lctr_id=${conts.lctr_id }&user_seq=${conts.user_seq }&lctr_no=${conts.lctr_no }" onclick="chkClassSche()">
=======
						<a href="/video-details?videoId=${conts.conts_id }&user_seq=${conts.user_seq }">
>>>>>>> develop
						<!-- <button onclick="chkClassSche()"> -->
							<div id="startDiv">
								<span id="startTxt"><div id="start">학습 시작</div></span>
							</div>
						</a>
						<!-- </button> -->
					</div>
					
				</div>
			</div>
		</c:forEach>
	</div>
	

	
	
</body>
</html>