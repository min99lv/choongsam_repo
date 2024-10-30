<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의영상</title>

</head>
<body>
   <div>
       <label for="slider">현재 수강진행상황</label>
       <span id="current-time">0분</span>/<span id="total-time">0분</span>
   </div>
   
   <button id="markAttendance">출석인정</button>
   <div id="status"></div>

 	<div>
 		<!-- 북마크 기능 -->
       <button id="seekToStart">영상 시작</button>
       <button id="seekToHalf">점점 잠오는 시점</button>
       <button id="seekToEnd">다 끝나가니 기다리시오</button>
   </div>
   
   
   <!-- YouTube Player -->
   <div id="player"></div>

   <script src="script.js"></script>
   <script type="text/javascript">
       let totalTime = 0;  //영상 총 길이
       let currentTime = 0;//현재 영상에서 재생 중인 시간(시청시간 UI에 표시하는 데 사용)
       let player;			
       let lastKnownTime =0;//마지막으로 사용자에게 보여진 재생시간(사용자가 비디오 조작했는지 확인하는 데 사용)
       let originalTime; //사용자가 조작한 시간(플레이어가 일지정지, 조작했을 때 원래대로 돌리는 데 사용)
       let watchedTime = 0; //사용자가 실제 시청한 시간
       let maxWatchedTime =0; //사용자가 시청한 최대시간


       // YouTube IFrame API를 호출함수
      function onYouTubeIframeAPIReady() {
            player = new YT.Player('player', {
                height: '390',
                width: '640',
                videoId: '5NbeB10h1wM', // 여기에 비디오 ID 입력
                events: {
                    'onReady': onPlayerReady,
                    'onStateChange': onPlayerStateChange
                }
            });
        }

       // 북마크
       function onPlayerReady(event) {
           // 비디오가 준비된 후 이벤트 리스너 설정
           document.getElementById('seekToStart').addEventListener('click', () => {
        	   if(maxWatchedTime >=0){
        		   player.seekTo(0, true); // 개요로 이동
        	   }else{
        		   alert("개요로 이동하기 위해서는 먼저 시청해야 합니다.");
        	   }
               
           });        
           document.getElementById('seekToHalf').addEventListener('click', () => {
               const halfTime = player.getDuration() / 2;
               if(maxWatchedTime >= halfTime){
            	   player.seekTo(halfTime, true); // 잠오는 시점으로 이동
               }else{
            	   alert("잠오는 시점으로 이동하기 위해서는 먼저 시청해야 합니다.");
               }               
           });           
           document.getElementById('seekToEnd').addEventListener('click', () => {
               const totalTime = player.getDuration();
               if(maxWatchedTime >= (totalTime-5)){
            	   player.seekTo(totalTime, true); // 끝으로 이동
               }else{
            	   alert("끝으로 이동하기 위해서는 먼저 시청해야 합니다.");
               }
              
           });         
           totalTime = player.getDuration();// 유튜브 api메소드 이용해 영상의 총 길이를 가져옴  
           
           //현재 수강진행상황
           document.getElementById('total-time').innerText = Math.floor(totalTime/60) + "분";           
           setInterval(updateCurrentTime, 1000);  
           
           // 주기적으로 재생 위치 체크 시작
           event.target.playVideo();
           checkPlayerTime(); 
       }
       
       
       //플레이어 상태변화 감지
       //-> 일시정지상태에서도 같이 사용하고 싶으면 if문안의 if문을 없애니까 됌.
       //->근데 일시정지가 안됌 : 최초 lastTime기록할 것
       function onPlayerStateChange(event) {
           if (event.data === YT.PlayerState.PAUSED) {       
                if(Math.abs(player.getCurrentTime() - lastKnownTime) > 0.1 && Math.abs(player.getCurrentTime() > maxWatchedTime)){
                    //내가 움직인 위치가 마지막위치랑 0.1초 차이가 나고 더 뒤로 눌렀을 경우에, + 내가 최초로 시청한 마지막 시간보다 누른곳이 클 때
                    originalTime = lastKnownTime; // 임의로 돌린시간 기록
                   setTimeout(() => {
                       player.seekTo(originalTime, true);
                       player.playVideo();
                   }, 1000);
                }
           }
       }


       	//재생시간 체크(현재 재생 시간을 10ms마다 업데이트 해서 lastKnownTime과 watchedTime을 저장)
       function checkPlayerTime() {
           setInterval(() => {
               lastKnownTime = player.getCurrentTime(); // 현재 재생 위치 저장
               watchedTime = lastKnownTime; //사용자가 시청한 시간 업데이트
               
            // 최장 시청 시간 업데이트(이미 본 구간에서는 슬라이더를 자유롭게 이동하기 위해)
               if (watchedTime > maxWatchedTime) {
                   maxWatchedTime = watchedTime; // 최대 시청 시간 갱신
                   document.getElementById('max-watched-time').innerText = Math.floor(maxWatchedTime / 60) + "분"; // UI 업데이트
               }          
           }, 100); // 0.1초마다 현재 시간 업데이트
       }
       
       	//현재 재생 시간을 화면에 업데이트하고, 슬라이더 값을 설정함.
       	// -> 이거 없애면 슬라이더는 계속 0:00으로 돌아감
       function updateCurrentTime() {
           currentTime = player.getCurrentTime();
           document.getElementById('current-time').innerText = Math.floor(currentTime / 60) + "분";
           document.getElementById('slider').value = (currentTime / totalTime) * 100; // 슬라이더 업데이트 
       }
       	
      
       // 출석인정 기능 추가
       	   document.getElementById('markAttendance').addEventListener('click', () => {
           const timeDiff = Math.abs(maxWatchedTime - totalTime);
           if (timeDiff <= 5) { // 5초 이내일 때 출석 인정
               document.getElementById('status').innerText = "출석이 인정되었습니다!";
           } else {
               document.getElementById('status').innerText = "출석이 인정되지 않았습니다. 영상 시청을 완료해주세요.";
           }
       });    
       
       	// 실시간 시청 정보 전송 AJAX 함수
           function sendViewingData(watchedTime, totalTime) {
               $.ajax({
                   url: '/', // 서버의 컨트롤러 URL을 입력하세요
                   method: 'POST',
                   contentType: 'application/json',
                   data: JSON.stringify({
                       watchedTime: watchedTime,
                       totalTime: totalTime
                   }),
                   success: function(response) {
                       console.log("서버 응답:", response);
                   },
                   error: function(xhr, status, error) {
                       console.error("AJAX 요청 오류:", status, error);
                   }
               });
           }
       
       
   </script>
   <script src="https://www.youtube.com/iframe_api"></script>
</body>
</html>
