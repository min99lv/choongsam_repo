package com.postgre.choongsam.service;

import java.util.List;
import java.util.Map;


import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Lecture;
import com.postgre.choongsam.dto.Note;
import com.postgre.choongsam.dto.Notice;

import jakarta.servlet.http.HttpServletRequest;


public interface SjmService {

	List<Notice> selectNoticeList(Map<String, Object> params);

	int countNotice(String keyword);

    Notice noticeDetail(int ntc_mttr_sn);

	int noticeCreate(Notice notice, MultipartFile[] files,HttpServletRequest request);
	
	
// ##################
// ##################
// ##################
// ##################
// 쪽지 ------------------------------------------------------------------
// ##################
// ##################
// ##################
// ##################

	int countNote(Map<String, Object> params);
	
	List<Note> getNotesReceived(Map<String, Object> params);

	List<Note> getNotesSend(Map<String, Object> params);

	Note getNote(int note_sn);

	int createNote(Note note);

	List<Lecture> getMyLectures(int user_seq);

	List<Note> getSameLeceture(String lectureId);

}
