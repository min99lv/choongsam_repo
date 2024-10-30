<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<style>
	#listBody{
		width: 1560px;
		height: 100%;
		background-color: #F1F1F1;
		float: right;
	}
	
	.form {
		width: 1360px;
		height: auto;
		background-color: white;
		margin: 0 auto;
	}
	
	#inserts {
		padding: 50px;
	}
	
	.chapter .file{
		background-color: white;
		border: 1px, black, solid;
	}
	
</style>

<script type="text/javascript">
	
	function plusChapter() {
		// 새로운 파일 입력 필드를 만들고 추가합니다.
		var chapterDiv = document.createElement("div");
		chapterDiv.className = "file";
		chapterDiv.innerHTML = `
			<div class="chapterTime">
				<label class="text">챕터시간 입력</label>
				<input type="time" name="chapterTime">
			</div>
			<div class="chapterTitle">
				<label class="text">챕터 제목 입력</label>
				<input type="text" name="chapterTitle">
			</div>`;

		document.getElementById("inserts").appendChild(chapterDiv);
	}
</script>

<body>

	<div id="listBody">
		<form action="/contsUpload" class="form">
			<div id="inserts">
				<div class="oneLine">
					<label id="text">강의 제목 입력</label>
					<input type="text" placeholder="강의 제목을 입력해주세요" name="title">
				</div>
				
				<div class="oneLine">
					<label id="text">차시 입력</label>
					<input type="number" name="chashi">
				</div>
				
				<div class="twoLine">
					<label class="text">출석 인정 기간 설정</label>
					<input type="date" name="minDate">~<input type="date" name="maxDate">
				</div>
				
				<div class="twoLine">
					<label class="text">유튜브 ID</label>
					<input type="text" name="youtubeId">
					<div>
						<button type="button" onclick="videoChk()">영상 확인</button>
					</div>
				</div>
				
				<div class="video">
					
				</div>
				
				<div class="chapter">
					<div class="butt"><button type="button" onclick="plusChapter()">챕터추가</button></div>
					<div class="chapterTime">
						<label class="text">챕터시간 입력</label>
						<input type="time" name="chapterTime">
					</div>
					<div class="chapterTitle">
						<label class="text">챕터 제목 입력</label>
						<input type="text" name="chapterTitle">
					</div>
				</div>
				
				<div class="file">
					<div class="butt"><button type="button" onclick="plusFile()">파일추가</button></div>
					<label class="text">첨부파일</label>
					<input type="file" name="file1">
				</div>
				<div class="file">
					<div class="butt"><button type="button" onclick="plusFile()">파일추가</button></div>
					<label class="text">첨부파일</label>
					<input type="file" name="file2">
				</div>
				<div class="file">
					<div class="butt"><button type="button" onclick="plusFile()">파일추가</button></div>
					<label class="text">첨부파일</label>
					<input type="file" name="file3">
				</div>
				
				<button type="submit">수업 등록</button>
			
			</div>
		</form>
	</div>
	

	
	
</body>
</html>