package com.postgre.choongsam.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
	
	
	
	
	
}
