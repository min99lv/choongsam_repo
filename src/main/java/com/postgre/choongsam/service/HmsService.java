package com.postgre.choongsam.service;

import java.util.List;

import com.postgre.choongsam.dto.Lecture_Video;

public interface HmsService {

	String getVideoDetails(String videoId); //영상화면

	List<Lecture_Video> findAllVideo(); //모든 비디오 목록

}
