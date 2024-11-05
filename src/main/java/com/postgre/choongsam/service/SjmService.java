package com.postgre.choongsam.service;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.Not;

import com.postgre.choongsam.dto.Notice;


public interface SjmService {

	List<Notice> selectNoticeList(Map<String, Object> params);

	int countNotice(String keyword);

    Notice noticeDetail(int ntc_mttr_sn);

	List<Not> noteList(Map<String, Object> params);

	int countNote(Map<String, Object> params);

}
