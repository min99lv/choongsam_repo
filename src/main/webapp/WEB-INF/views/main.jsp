<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>


    <style>
   


    </style>

		
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>

	

    <body>
        
        <header>
	       <%@ include file="header.jsp" %>
	   </header>

        <div class="main_container">

            <div class="main_contents"><a href="/notes/received">받은쪽지</a></div>
            <div class="main_contents"><a href="/notes/sent">보낸쪽지</a></div>
            <div class="main_contents"><a href="/notes/trash">휴지통</a></div>
            <div class="main_contents"><a href="/asks/new">문의사항작성</a></div>
            <div class="main_contents"><a href="/asks/my">내문의사항</a></div>
            
                       <div class="main_contents"><a href="/sh_lecture_student?lctr_id=0001&user_seq=10012">학생강의실</a></div>  이것은 비즈니스
            <div class="main_contents"><a href="/sh_lecture_student?lctr_id=9&user_seq=10012">학생강의실</a></div> 이것은 테스트
            <!-- <div class="main_contents"><a href="/sh_lecture_teacher?lctr_id=0001&user_seq=10050">교수강의실</a></div> -->
            <div class="main_contents"><a href="/sh_lecture_teacher?lctr_id=0001&user_seq=10050">교수강의실</a></div> 이것은 비즈니스
            <div class="main_contents"><a href="/sh_lecture_teacher?lctr_id=9&user_seq=10041">교수강의실</a></div> 이것은 테스트
            
            <p>
            USER_SEQ : ${user_seq } <p>
	   		USER_ID : ${user } <p>
	   		USER_STATUS : ${usertype }

            
        </div>
        <a href="/courseApproveList">안태랑1</a>
        <a href="/registerCourseForm">안태랑2</a>
        <a href="/courseApplyList">안태랑3</a>

        <footer>
            <%@ include file="footer.jsp" %>
        </footer>
    </body>

    </html>