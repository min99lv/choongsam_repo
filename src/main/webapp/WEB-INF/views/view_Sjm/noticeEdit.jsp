<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<style>
body {
	margin: 0;
	padding: 0;
}

.container {
	display: flex;
	flex-direction: column;
	position: relative;
	background-color: white;
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



/* 파일 첨부 관련 스타일 */
.file_list {
    display: flex;
    flex-direction: column;
    gap: 10px;
}

.file_input {
    display: flex;
    align-items: center;
}

.file_input input[type="text"] {
    width:200px;
    padding: 8px;
    margin-right: 10px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
   
}

.file_input label {
    font-size: 14px;
    color: #00664F;
    cursor: pointer;
}

.file_input input[type="file"] {
    display: none; /* 파일 선택 버튼 숨기기 */
}

.btns {
    background-color: #e74c3c;
    color: white;
    border: none;
    padding: 8px 12px;
    cursor: pointer;
    font-size: 14px;
    border-radius: 4px;
    margin-left: 10px;
}

.fn_add_btn {
    background-color: #00664F;
}

button[type="submit"] {
    width: 100px;
    height: 50px;
    background-color: #00664F;
    border: none;
    color: white;
    font-size: 18px;
    cursor: pointer;
    display: block;
    margin: 20px auto;
}

.btns del_btn{
	width: 100px;
	margin: 0px;


}
</style>



</head>
<body>
	<header>
		<%@ include file="../header.jsp"%>
	</header>

	<div class="container">
		<div class="contents">
			<h1>공지사항 작성</h1>
		</div>

		<form id="noticeForm" method="post" action="/api/notice/new"
			enctype="multipart/form-data">
			<input type="hidden" id="ntc_mttr_sn"  name="ntc_mttr_sn" value="${notice.ntc_mttr_sn}">
			<table class="list">
				<tr>
					<th>제목</th>
					<td><input type="text" name="ntc_mttr_ttl" required value="${notice.ntc_mttr_ttl}"></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<div class="file_list">
							<div>
								<div class="file_input">
									<input type="text" readonly /> <label> 첨부파일 <input
										type="file" name="files" onchange="selectFile(this);" />
									</label>
								<button type="button" onclick="removeFile(this);"
									class="btns del_btn">
									<span>삭제</span>
								</button>
								<button type="button" onclick="addFile();"
									class="btns fn_add_btn">
									<span>파일 추가</span>
								</button>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="ntc_mttr_cn" rows="10" required>${notice.ntc_mttr_cn}</textarea></td>
				</tr>
			</table>

			<button type="submit">작성완료</button>
		</form>
	</div>

	<script type="text/javascript">
     // 파일 선택
     function selectFile(element) {

         const file = element.files[0];
         const filename = element.closest('.file_input').firstElementChild;

         // 1. 파일 선택 창에서 취소 버튼이 클릭된 경우
         if ( !file ) {
             filename.value = '';
             return false;
         }

         // 2. 파일 크기가 10MB를 초과하는 경우
         const fileSize = Math.floor(file.size / 1024 / 1024);
         if (fileSize > 10) {
             alert('10MB 이하의 파일로 업로드해 주세요.');
             filename.value = '';
             element.value = '';
             return false;
         }

         // 3. 파일명 지정
         filename.value = file.name;
     }


     // 파일 추가
     function addFile() {
         const fileDiv = document.createElement('div');
         fileDiv.innerHTML =`
             <div class="file_input">
                 <input type="text" readonly />
                 <label> 첨부파일
                     <input type="file" name="files" onchange="selectFile(this);" />
                 </label>
             </div>
             <button type="button" onclick="removeFile(this);" class="btns del_btn"><span>삭제</span></button>
         `;
         document.querySelector('.file_list').appendChild(fileDiv);
     }


     // 파일 삭제
     function removeFile(element) {
         const fileAddBtn = element.nextElementSibling;
         if (fileAddBtn) {
             const inputs = element.previousElementSibling.querySelectorAll('input');
             inputs.forEach(input => input.value = '')
             return false;
         }
         element.parentElement.remove();
     }

    </script>
</body>
</html>
