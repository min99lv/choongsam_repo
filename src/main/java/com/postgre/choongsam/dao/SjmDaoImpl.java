package com.postgre.choongsam.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Note;
import com.postgre.choongsam.dto.Notice;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SjmDaoImpl implements SjmDao{
	private final SqlSession session;
	
	
	public List<Notice> selectNoticeList(Map<String, Object> params) {
	    List<Notice> noticeList = session.selectList("com.postgre.choongsam.mapper.sjm.selectNoticeList",params);
	    
	    System.out.println("SjmDaoImpl selectNoticeList noticeList" + noticeList);
	    
	    return noticeList;
	}

	@Override
	public int countNotice(String keyword) {
		System.out.println("다오 도착 ");
		int total = session.selectOne("com.postgre.choongsam.mapper.sjm.countNotice", keyword);
		
		System.out.println("-------->"+total);
		
		return total;
	}

	@Override
	public Notice noticeDetail(int ntc_mttr_sn) {

		Notice notice = session.selectOne("com.postgre.choongsam.mapper.sjm.noticeDetail", ntc_mttr_sn);

		System.out.println("SjmDaoImpl.noticeDetail() notice >>" + notice);
		return notice;
	}

	@Override
	public List<Note> noteList(Map<String, Object> params) {
		
		List<Note> noteList = session.selectList("com.postgre.choongsam.mapper.sjm.noteList", params);
		 
		System.out.println("쪽지 리스트 다오임 " + noteList);
		return noteList;
	}


	// NOTE - 쪽지 갯수(페이징)
	@Override
	public int countNote(Map<String, Object> params) {
		
		System.out.println("쪽지갯수 다오");
		
		int total = session.selectOne("com.postgre.choongsam.mapper.sjm.countNote", params);
		
		System.out.println(total + "이거임");
		
		return 0;
	}
	
	
	
	
	
}
