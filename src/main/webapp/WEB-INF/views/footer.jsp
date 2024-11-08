<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
       .footer_container {
	display: grid;
	grid-template-columns: minmax(auto, 1280px);
	justify-content: center;
	background-color: #00664F;
	width: 100%;
	height: auto;
	position: relative;
}

.footer_img {
	width: 100%;
	max-width: 108px;
	height: auto;
	margin: 44px 0 0;
}

.footer_text_hed {
	display: flex;
	gap: 40px;
	align-items: center;
	margin: 30px 0 0;
}

.footer_text_body {
	display: flex;
	gap: 40px;
	align-items: center;
	margin: 15px 0 0;
}

.footer_text_footer {
	display: flex;
	gap: 40px;
	align-items: center;
	margin: 15px 0 0;
}

.footer_text_end {
	display: flex;
	justify-content: space-between;
	align-items: baseline;
	margin: 20px 0 74px;
}

.footer_text_hed_text1 {
	color: #e2e2e2;
}

.footer_text_hed_text2 {
	color: #323232;
}

.footer_text_hed_text3 {
	font-size: 12px;
}

.footer_text_hed_a1 {
	color: #ff4714;
	font-weight: bold;
}

.footer_text_hed_a2 {
	color: #323232;
	font-weight: bold;
}

.footer_end_img {
	margin: 0 0 0 15px;
}

    </style>
</head>
<body>
	<div class="footer_container">
		<div class="footer_main">
			<img alt="logo이미지" src="" class="footer_img">
		</div>
		<div class="footer_text_hed">
			<a href="#" class="footer_text_hed_a1">개인정보 처리방침</a>
			<div class="footer_text_hed_text1">│</div>
			<a href="#" class="footer_text_hed_a2">서비스 이용약관</a>
			<div class="footer_text_hed_text1">│</div>
			<a href="#" class="footer_text_hed_a2">이용약관</a>
		</div>
		<div class="footer_text_body">
			<div class="footer_text_hed_text2">상호명:JM카</div>
			<div class="footer_text_hed_text1">│</div>
			<div class="footer_text_hed_text2">대표자:장민혁</div>
			<div class="footer_text_hed_text1">│</div>
			<div class="footer_text_hed_text2">개인정보보호책임자:손정민</div>
			<div class="footer_text_hed_text1">│</div>
			<div class="footer_text_hed_text2">사업자등록번호:739-32-324230</div>
		</div>
		<div class="footer_text_footer">
			<div class="footer_text_hed_text2">통신판매업 신고번호:제2018-서울마포구-0672호</div>
			<div class="footer_text_hed_text1">│</div>
			<div class="footer_text_hed_text2">사업장소재지:서울특별시 마포구 신촌로 176, 3층 301호</div>
		</div>
		<div class="footer_text_end">
			<div class="footer_text_hed_text3">Copyright © 제이엠카 All Rights Reserved</div>
			<div class="footer_text_end_img">
			</div>
		</div>
	</div>
</body>
</html>