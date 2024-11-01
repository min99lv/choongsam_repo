<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의영상</title>
</head>

<body>
<div class="container-left">
   <!-- YouTube Player -->
   <div class="youtube" id="player"></div>
</div>

<div class="container-right">
   <!-- 북마크 기능 -->
   <div class="bookmark">
    <a href="#" id="seekToStart">영상 시작</a><p>
    <a href="#" id="seekToHalf">점점 잠오는 시점</a><p>
    <a href="#" id="seekToEnd">다 끝나가니 기다리시오</a> 
   </div>

   <!-- 수강진행상황 -->
   <div class="suganging">
    <label>최대 학습시간</label>
    <span id="max-watched-time">0분</span>/<span id="total-time">0분</span>
    <button id="markAttendance">출석인정</button><p>
    <div id="status"></div>
   </div>
</div>

<script type="text/javascript">
    let player;
    let totalTime = 0; 			//영상길이
    let maxWatchedTime = 0; 	//최대시청시간
    let lastKnownTime = 0; 		//마지막시청시간
    let originalTime; 			//조작한 시간(플레이어 일시정지, 조작 시 원래대로 돌리는 데 사용)
    let isPaused = false;		//일시정지 여부

    // youtube IFrame API 호출함수
    function onYouTubeIframeAPIReady() {
        player = new YT.Player('player', {
            height: '778',
            width: '1377',
            videoId: '4HGg3PGxXTI',
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

    // 북마크(비디오가 준비된 후 이벤트 리스너 설정)
    function onPlayerReady(event) {
        totalTime = player.getDuration();
        document.getElementById('total-time').innerText = Math.floor(totalTime / 60) + "분";
        event.target.playVideo();
        checkPlayerTime();

        document.getElementById('seekToStart').addEventListener('click', () => {
            if (maxWatchedTime >= 0) player.seekTo(0, true);
            else alert("개요로 이동하기 위해서는 먼저 시청해야 합니다.");
        });

        document.getElementById('seekToHalf').addEventListener('click', () => {
            const halfTime = totalTime / 2;
            if (maxWatchedTime >= halfTime) player.seekTo(halfTime, true);
            else alert("잠오는 시점으로 이동하기 위해서는 먼저 시청해야 합니다.");
        });

        document.getElementById('seekToEnd').addEventListener('click', () => {
            if (maxWatchedTime >= totalTime - 5) player.seekTo(totalTime, true);
            else alert("끝으로 이동하기 위해서는 먼저 시청해야 합니다.");
        });
    }

  	//재생시간 체크(현재 재생시간을 10ms마다 업뎃해서 lastKnownTime과 watchedTime을 저장)
    function checkPlayerTime() {
        setInterval(() => {
            if (!isPaused) {
                lastKnownTime = player.getCurrentTime();//현재 재생위치 저장
                if (lastKnownTime > maxWatchedTime) {
                    maxWatchedTime = lastKnownTime; //최대시간 저장
                    document.getElementById('max-watched-time').innerText = Math.floor(maxWatchedTime / 60) + "분";
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
        if (!isPaused && Math.abs(currentTime - lastKnownTime) > 0.5 && currentTime > maxWatchedTime) {
            originalTime = lastKnownTime;

            const validatePosition = setTimeout(() => {
                if(player.getCurrentTime() > maxWatchedTime){
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
        if (Math.abs(maxWatchedTime - totalTime) <= 5) {
            document.getElementById('status').innerText = "출석이 인정되었습니다!";
        } else {
            document.getElementById('status').innerText = "출석이 인정되지 않았습니다. 영상 시청을 완료해주세요.";
        }
    });
</script>
<script src="https://www.youtube.com/iframe_api"></script>
</body>
</html>
