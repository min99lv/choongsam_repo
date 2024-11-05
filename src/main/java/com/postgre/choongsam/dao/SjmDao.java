package com.postgre.choongsam.dao;

import java.util.List;
import java.util.Map;


import org.springframework.web.multipart.MultipartFile;

import com.postgre.choongsam.dto.Filegroup;
import com.postgre.choongsam.dto.Lecture;

import com.postgre.choongsam.dto.Note;
import com.postgre.choongsam.dto.Notice;

public interface SjmDao {

	List<Notice> selectNoticeList(Map<String, Object> params);

	int countNotice(String keyword);

    Notice noticeDetail(int ntc_mttr_sn);

	List<Note> noteList(Map<String, Object> params);

	int countNote(Map<String, Object> params);

	int noticeCreate(Notice notice,List<Filegroup> uploadFiles);

	int getUserSeq(Map<String, Object> params);

	List<Note> getSentNotes(Map<String, Object> params);

	Note getNote(int note_sn);

	int createNote(Note note);

	List<Lecture> getMyLectures(int user_seq);

	List<Lecture> getSameLeceture(int lectureId);


}
