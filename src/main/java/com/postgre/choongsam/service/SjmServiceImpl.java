package com.postgre.choongsam.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.SjmDao;
import com.postgre.choongsam.dto.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SjmServiceImpl implements SjmService {
	private final SjmDao sd;

	// NOTE - 공지사항 목록
	@Override
	public List<Notice> selectNoticeList(Map<String, Object> params) {
	
		List<Notice> noticeList = sd.selectNoticeList(params);
		
	
		return noticeList;
	}

	// NOTE - 공지사항 갯수 (페이징)
	@Override
	public int countNotice(String keyword) {
		
		int total = sd.countNotice(keyword);
		
		return total;
	}
}
