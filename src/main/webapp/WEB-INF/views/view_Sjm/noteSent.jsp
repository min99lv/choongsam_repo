<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>쪽지함</title>
<style>
body {
	margin: 0;
	padding: 0;
}

.container {
	display: flex;
	flex-direction: column;
	position: relative;
	/* 컨테이너의 배경색을 흰색에 가까운 연한 회색으로 설정합니다. */
	background-color: #fdfdfd;
	/* 컨테이너에 미세한 그림자를 추가하여 입체감을 줍니다. */
	top: 120px;
	/*  컨테이너의 너비 1320px 고정 */
	width: 1320px;
	/* 중앙 정렬 */
	margin: 0 auto;
	/* padding을 width에 포함하여 계산 */
	box-sizing: border-box;
	/* 자동으로 좌우 여백을 설정하여 가운데 정렬 */
	margin: 0 auto;
	height: auto;
	margin-bottom: 200px;
}

.list {
	margin: 50px 0;
}

table {
	padding: 0;
	border-top: 2px solid black;
}

th {
	color: gray;
	font-weight: bold;
	text-align: center;
	/* 중앙 정렬 */
	padding: 20px 0;
	background-color: #F9F9F9;
	color: black;
	border-bottom: 0.4px solid #e2e8ee;
	border-left: 0.4px solid #e2e8ee;
}

td {
	border-bottom: 0.4px solid #e2e8ee;
	border-left: 0.4px solid #e2e8ee;
	cursor: pointer;
	font-weight: bold;
	text-align: center;
	/* 중앙 정렬 */
	color: #323232;
	font-size: px;
	height: 50px;
}

.manager_pagination {
	text-align: center;
}

h1 {
	font-size: 40px;
}

.contents1 {
	display: flex;
	justify-content: space-between;
}

.writeNoticeBtn {
	width: 200px;
	height: 50px;
	background-color: #00664F;
	border: none;
	color: white;
	text-align: center;
	margin-top: 10px; /* 버튼과 검색 바 간격 */
	text-decoration: none; /* 링크 스타일 없애기 */
	display: flex; /* flexbox 사용 */
	justify-content: center; /* 텍스트 가운데 정렬 */
	align-items: center; /* 세로 가운데 정렬 */
}

.pagination_current {
	border-radius: 10px;
	width: 35px; /* 크기를 키움 */
	height: 35px; /* 높이 설정 */
	border: 1px solid black;
	display: inline-flex;
	justify-content: center;
	align-items: center; /* 텍스트 가운데 정렬 */
	font-size: 18px; /* 폰트 크기 조정 */
	background-color: black;
	color: white;
}

.pagination_a {
	border-radius: 10px;
	width: 35px; /* 크기를 키움 */
	height: 35px; /* 높이 설정 */
	border: 1px solid black;
	display: inline-flex;
	justify-content: center;
	align-items: center; /* 텍스트 가운데 정렬 */
	font-size: 18px; /* 폰트 크기 조정 */
	text-decoration: none; /* 링크 스타일 없애기 */
	color: black; /* 링크 색상 설정 */
}

.keyword {
	height: 40px;
	width: 255px;
	border-radius: 10px;
	border: 1px #949494 solid;
	font-size: 20px;
	text-align: center;
}

.search_btn {
	height: 40px;
	border-radius: 10px;
	background-color: #00664F;
	width: 70px;
	font-size: 20px;
	color: white;
	border: none;
}

select {
	height: 40px;
	width: 255px;
	border-radius: 10px;
	border: 1px #949494 solid;
	font-size: 20px;
	text-align: center;
	color: #949494;
}

td a {
	color: black;
	text-decoration: none;
}

td a:hover {
	text-decoration: underline;
}
</style>
</head>


<body>
	<header>
		<%@ include file="../header.jsp"%>
	</header>

	<div class="container">

		<div class="contents">
			<h1>쪽지함</h1>
		</div>

		<div class="contents1">
			<form action="/api/notice" id="frm">
				<div class="manager_Qna_header_search">
					<select>
						<option>전체검색</option>
						<option>제목</option>
						<option>내용</option>
					</select> <input type="text" name="keyword" class="keyword" id="keyword">
					<button type="submit" class="search_btn">검색</button>
				</div>
			</form>

			<a class="writeNoticeBtn" href="/notes/new">쪽지 보내기</a>
		</div>



		<table class="list">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>받은사람</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>







		<!-- 페이징 네비게이션 ------------------------------------- -->
		<div class="manager_pagination">


		</div>

	</div>

	<footer>
		<%@ include file="../footer.jsp"%>

	</footer>

<script type="text/javascript">



document.addEventListener('DOMContentLoaded', function() {
 // 페이지 로드 시 기본적으로 받은쪽지 목록을 가져온다
 fetchNotes('/api/notes/sent?currentPage=1');  // 기본적으로 첫 페이지 호출

 // 검색어 입력 및 페이지 변경 시 실행
 document.querySelector('.search_btn').addEventListener('click', function(event) {
     event.preventDefault();
     const keyword = document.querySelector('.keyword').value;  // 클래스 'keyword'로부터 값 가져오기
     console.log(keyword);  // 확인용
     fetchNotes(`/api/notes/sent?currentPage=1&keyword=${keyword}`);  // 검색어와 함께 첫 페이지 요청
 });
});
 
 function fetchNotes(apiUrl) {
	    fetch(apiUrl)
	        .then(response => {
	            if (!response.ok) {
	                throw new Error('네트워크 오류 발생');
	            }
	            return response.json();
	        })
	        .then(data => {
	            populateNoteTable(data.notes);
	            updatePagination(data.paging, data.total, data.keyword);
	        })
	        .catch(error => console.error('에러:', error));
	}
 
//Define onPageClick function in the global scope
 function onPageClick(url) {
  const keyword = document.querySelector('.keyword').value;  // 현재 입력된 검색어 가져오기
  fetchNotes(decodedUrl); // fetchNotes 함수 호출
 }

 // 받은 쪽지 테이블에 데이터를 채워주는 함수
 function populateNoteTable(notes) {
     const tableBody = document.querySelector('.list tbody');
     tableBody.innerHTML = ''; // 기존 데이터 초기화

     if (notes.length === 0) {
         const emptyRow = `<tr><td colspan="3" style="text-align: center;">등록된 쪽지가 없습니다.</td></tr>`;
         tableBody.innerHTML = emptyRow;
         return;
     }

     let rows = '';
     notes.forEach(note => {
         rows += `
             <tr>
                 <td>`+note.note_sn+`</td>
                 <td><a href="/note/` + note.note_sn + `">` + note.note_ttl + `</a> </td> <!-- 제목에 a태그 추가 -->
                 <td>`+note.receiver_name+`</td>
             </tr>
         `;
     });
     tableBody.innerHTML = rows; // 모든 행을 한 번에 추가
 }

 function updatePagination(paging, total, keyword) {
	    const paginationContainer = document.querySelector('.manager_pagination');
	    let paginationHtml = '';

	    // keyword가 undefined일 경우 빈 문자열로 처리
	    const searchKeyword = (keyword === undefined || keyword === null) ? '' : keyword;
	    
	    // 이전 페이지
	    if (paging.startPage > paging.pageBlock) {
	        paginationHtml += `<a class="pagination_a_back" href="javascript:void(0);" onclick="onPageClick('${paging.startPage - paging.pageBlock}')">이전</a>`;
	    }

	    // 페이지 번호
	    for (let i = paging.startPage; i <= paging.endPage; i++) {
	        if (i === paging.currentPage) {
	            paginationHtml += `<span class="pagination_current">` + i + `</span>`;
	        } else {
	            const url = "/api/notes/sent?currentPage=" + i + "&keyword=" + encodeURIComponent(searchKeyword);
	            paginationHtml += `<a class="pagination_a" href="javascript:void(0);" onclick="onPageClick('${url}')">` + i + `</a>`;
	        }
	    }

	    // 다음 페이지
	    if (paging.endPage < paging.totalPage) {
	        paginationHtml += `<a class="pagination_a_next" href="javascript:void(0);" onclick="onPageClick('${paging.startPage + paging.pageBlock}')">다음</a>`;
	    }

	    paginationContainer.innerHTML = paginationHtml;
	}

</script>



</body>

</html>