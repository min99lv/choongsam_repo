package com.postgre.choongsam.dao;

import java.util.List;

import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Lecture_Video;

public interface HmsDao {
	List<Lecture_Video> findAllVideo();//모든 영상목록 가져오기

	void APIdata(String videoId);//youtubeAPI data저장

	void saveWatchTime(Class_Schedule class_schedule);



}
