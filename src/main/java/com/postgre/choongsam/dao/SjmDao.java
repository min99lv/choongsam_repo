package com.postgre.choongsam.dao;

import java.util.List;
import java.util.Map;

import com.postgre.choongsam.dto.Note;
import com.postgre.choongsam.dto.Notice;

public interface SjmDao {

	List<Notice> selectNoticeList(Map<String, Object> params);

	int countNotice(String keyword);

    Notice noticeDetail(int ntc_mttr_sn);

	List<Note> noteList(Map<String, Object> params);

	int countNote(Map<String, Object> params);


}
