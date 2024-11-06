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
            <div class="main_contents"><a href="/asks/">문의사항</a></div>
            <div class="main_contents"><a href="/sh_lecture_student?lctr_id=0010&user_seq=10012">학생강의실</a></div>
            <div class="main_contents"><a href="/sh_lecture_teacher?lctr_id=0010&user_seq=10011">교수강의실</a></div>
            
        </div>

        <footer>
            <%@ include file="footer.jsp" %>
        </footer>
    </body>

    </html>