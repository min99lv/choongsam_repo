<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의영상</title>
    
</head>
<style>
body {
	display: flex;
	justify-content: flex-start;
	padding: 20px;
	box-sizing: border-box; /* box-sizing 설정 */
}
.container-left, .container-right {
	padding: 10px;
	box-sizing: border-box; /* padding이 포함된 너비 계산 */
}
.container-right {
	margin-left: 20px;
	width: 400px;
	height: 800px;
	border: 2px solid #ccc;
	border-radius: 10px;
	padding: 15px;
	background-color: aliceblue;
	display: flex;
	flex-direction: column;
}
.bookmark {
	padding-top: 100px;
	padding-left: 50px;
}
.suganging {
	padding-top: 100px;
	padding-left: 50px;
	font-size: 20px;
}
.exit-container {
	margin-top: 200px;
	margin-bottom: 50px;
	text-align: right;
}
</style>

<body>
<div class="container-left">
   <div class="youtube" id="player"></div>
</div>

<div class="container-right">
	<a href="/api/lectures/download/sjm">강의자료 다운</a>
   <!-- 북마크 기능 -->
   <div class="bookmark" style="font-size: 20px;">
    <label style="font-weight: 700; font-size: 24px;"><강의 시간 북마크></label><p>
    <a href="#" id="seekToStart">시작 지점</a><p>
    <a href="#" id="seekToHalf">잠오는 지점</a><p>
    <a href="#" id="seekToEnd">다 끝나감</a> 
   </div>
   <!-- 수강진행상황 -->
   <div class="suganging">
    <label>학습시간</label>
    <span id="conts_max">분</span>/<span id="vdo_length">분</span>
    <button id="markAttendance">출석인정</button><p>
   		<div id="status"></div>
   	</div>
   	<div class="exit-container">
   		<form id="exitForm" action="/api/progress/save" method="POST">
   			<input type="hidden" name="videoId" value="">
   			<input type="hidden" name="userId" value="user50">
   			<input type="hidden" name="conts_final" value="">
   			<input type="hidden" name="conts_max" value="">
   			<input type="hidden" name="vdo_length" value="">
            강의실 나가기
            <button type="button" id="exitButton">
                <img src="../image/나가기.png" style="width: 50px; height: auto;">
            </button>
   	</form>
   </div>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    let player;
    let vdo_length = 0; 		//영상길이
    let conts_max = 0; 	        //최대시청시간
    let conts_final = 0; 		//마지막시청시간
    let originalTime; 			//조작한 시간(플레이어 일시정지, 조작 시 원래대로 돌리는 데 사용)
    let isPaused = false;		//일시정지 여부
    let videoId = "${videoId}";
    
 	//videoId가져오기
    function getDynamicVideoId(){
    	const urlParams = new URLSearchParams(window.location.search);
    	console.log('videoId->', videoId);
    	return urlParams.get('videoId')|| videoId; //기본값 설정
    }
 	
    // youtube IFrame API 호출함수
    function onYouTubeIframeAPIReady() {
    	videoId = getDynamicVideoId();
    	
        player = new YT.Player('player', {
            videoId: videoId,
            height: '778',
            width: '1377',
            playerVars: {
                rel: 0,
                modestbranding: 1
            },
            events: {
                'onReady': onPlayerReady,
                'onStateChange': onPlayerStateChange
            }
        });
    }
    
    //마지막위치에서 재생
    function loadFinalTime(videoId) {
    console.log('Loading final time for videoId:', videoId); // videoId가 올바른지 로그로 확인
    if (!videoId) {
        console.error("videoId is missing!"); // videoId가 없을 경우 에러 로그
        return; // videoId가 없으면 함수를 종료
    } 

    fetch(`/api/progress/${videoId}`) // videoId가 올바르게 삽입되었는지 확인
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log(data); // 반환되는 데이터 출력
            const savedTime = data.conts_final;
            if (savedTime && savedTime > 0) {
                player.seekTo(savedTime, true);
                conts_final = savedTime; // 불러온 시간으로 conts_final 업데이트
            }
        })
        .catch(error => console.error("시청 시간을 불러오지 못했습니다:", error));
	}
    

    // 북마크(비디오가 준비된 후 이벤트 리스너 설정)
    function onPlayerReady(event) {
    	vdo_length = player.getDuration();
        document.getElementById('vdo_length').innerText = Math.floor(vdo_length / 60) + "분";
        
        loadFinalTime(videoId); //마지막 시청 위치 불러오기
        event.target.playVideo(); //영상을 재생
        checkPlayerTime();

        document.getElementById('seekToStart').addEventListener('click', () => {
            if (conts_max >= 0) player.seekTo(0, true);
            else alert("개요로 이동하기 위해서는 먼저 시청해야 합니다.");
        });

        document.getElementById('seekToHalf').addEventListener('click', () => {
            const halfTime = vdo_length / 2;
            if (conts_max >= halfTime) player.seekTo(halfTime, true);
            else alert("잠오는 시점으로 이동하기 위해서는 먼저 시청해야 합니다.");
        });

        document.getElementById('seekToEnd').addEventListener('click', () => {
            if (conts_max >= vdo_length - 5) player.seekTo(vdo_length, true);
            else alert("끝으로 이동하기 위해서는 먼저 시청해야 합니다.");
        });
    }
    


  	//재생시간 체크(현재 재생시간을 10ms마다 업뎃해서 conts_final과 watchedTime을 저장)
    function checkPlayerTime() {
        setInterval(() => {
            if (!isPaused) {
            	conts_final = player.getCurrentTime();//현재 재생위치 저장
                if (conts_final > conts_max) {
                	conts_max = conts_final; //최대시간 저장
                    document.getElementById('conts_max').innerText = Math.floor(conts_max / 60) + "분";
                }
            }
        }, 3000); //3초마다 현재시간 업뎃
    }

  	// 플레이어 상태변화 감지
    function onPlayerStateChange(event) {
        isPaused = event.data === YT.PlayerState.PAUSED;
        if (!isPaused) checkSliderMovement();
    }

  	// 슬라이더 움직임체크
    function checkSliderMovement() {
        const currentTime = player.getCurrentTime();
        // 슬라이더 조작을 감지하고, 제한 범위를 벗어나면 원래 위치로 되돌림.
        if (!isPaused && Math.abs(currentTime - conts_final) > 0.5 && currentTime > conts_max) {
            originalTime = conts_final;

            const validatePosition = setTimeout(() => {
                if(player.getCurrentTime() > conts_max){
                    // 최대 시청 시간 이상 조작 시 원래 위치로
                    player.seekTo(originalTime, true); 
                }else{
                    clearInterval(validatePosition);
                }
            }, 200);
        }
    }

    // 출석인정 기능 
    document.getElementById('markAttendance').addEventListener('click', () => {
        if (Math.abs(conts_max - vdo_length) <= 5) {
            document.getElementById('status').innerText = "출석이 인정되었습니다!";
        } else {
            document.getElementById('status').innerText = "출석이 인정되지 않았습니다. 영상 시청을 완료해주세요.";
        }
    });

    
    //유튜브 api 자료 가져오기 함수
    const apiKey = 'AIzaSyDB-l20C9L1cR8XYmyN9Olb-8TZ07ZPbd0';  // 유효한 API 키로 교체 필요
    //const videoId = videoId;  // 조회할 비디오 ID

    $(document).ready(function(){
        $.ajax({
            url: "https://www.googleapis.com/youtube/v3/videos",
            type: "GET",
            data: {
                part: 'snippet,contentDetails,statistics',
                id: videoId,
                key: apiKey
            },
            success: function(data) {
            	console.log(data);
                if (data.items && data.items.length > 0) {
                    const item = data.items[0];
                    const title = item.snippet.title;
                    const description = item.snippet.description;
                    const thumbnailUrl = item.snippet.thumbnails.high.url;
                    const channelId = item.snippet.channelId;
                    
                    // YouTube 영상 정보를 출력
                    const output = `
                        <h3>${title}</h3>
                        <p>${description}</p>
                        <img src="${thumbnailUrl}" alt="${title}">
                        <p>채널 ID: ${channelId}</p>
                    `;
                    $("#player").append(output);
                } else {
                    console.error("해당 비디오 정보를 찾을 수 없습니다.");
                }
            },
            error: function(error) {
                console.error("YouTube API 요청 오류:", error);
            }
        });
    });
    

    
    
    document.getElementById('exitButton').addEventListener('click', () => {
        // 폼의 hidden input 필드에 데이터 설정
        document.querySelector('input[name="conts_final"]').value = Math.floor(conts_final);
        document.querySelector('input[name="conts_max"]').value = Math.floor(conts_max);
        document.querySelector('input[name="vdo_length"]').value = Math.floor(vdo_length);
        document.querySelector('input[name="videoId"]').value = videoId;

        console.log('conts_final:', conts_final);
        
        // 폼 제출
        document.getElementById('exitForm').submit();
    });
</script>
<script src="https://www.youtube.com/iframe_api"></script>
</body>
</html>
