<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>공지사항</title>
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
					/* 중앙 정렬 */
					padding: 20px 0;
				}

				td {
					border-bottom: 0.4px solid #e2e8ee;
					border-left:0.4px solid #e2e8ee; ;
					cursor: pointer;
					font-weight: bold;
					text-align: center;
					/* 중앙 정렬 */
					color: #323232;
					font-size: 14px;
					height: 50px;
				}


				.manager_pagination{
					text-align: center;

				}
				h1{
					font-size: 40px;

				}
			</style>
		</head>
		<script type="text/javascript">
		</script>

		<body>
			<header>
				<%@ include file="../header.jsp" %>
			</header>

			<div class="container">
				<div class="contents">
					<h1>공지사항</h1>
				</div>

				<div class="contents">
					<form action="/api/notice" id="frm">
						<div class="manager_Qna_header_search">
							<input type="text" name="keyword" class="keyword" id="keyword" placeholder="제목 또는 내용 입력.">
							<button type="submit" class="manager_Qna_header_search_but">검색</button>
						</div>
					</form>
				</div>


				<c:if test="${total>0}">
					<table class="list">
						<thead>
							<tr>
								<td>번호</td>
								<td>제목</td>
								<td>등록일</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="notice" items="${noticeList}" varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td><a href="">${notice.ntc_mttr_ttl}</a></td>
									<td>${notice.ntc_mttr_dt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</c:if>


				<c:if test="${total == 0}">
					<div>
						<h1>등록된 공지사항이 없습니다</h1>
					</div>
				</c:if>








				<!-- 페이징 네비게이션 ------------------------------------- -->
				<div class="manager_pagination">

					<!-- 이전 페이지 ------------------->
					<c:if test="${page.startPage > page.pageBlock}">
						<a id="page" class="manager_pagination_a_back"
							href="/api/notice?currentPage=${page.startPage - page.pageBlock > 0 ? page.startPage - page.pageBlock : 1}&total=${total}&keyword=${keyword}">
							이전 </a>
					</c:if>


					<!-- 현재 페이지 ------------------->
					<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
						<c:choose>
							<c:when test="${i == page.currentPage}">
								<span class="manager_pagination_current">${i}</span>
							</c:when>
							<c:otherwise>
								<a id="page" class="manager_pagination_a"
									href=/api/notice?currentPage=${i}&total=${total}&keyword=${keyword}>${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<!--  다음 페이지 -------------------->
					<c:if test="${page.endPage < page.totalPage}">
						<a class="manager_pagination_a_next" id="page"
							href="/api/notice?currentPage=${page.startPage+page.pageBlock}&total=${total}&keyword=${keyword}">다음</a>
					</c:if>
				</div>

			</div>



			</div>


		</body>

		</html>