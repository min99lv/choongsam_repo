package com.postgre.choongsam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Class_ScheduleAddVideo;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class JshDaoImpl implements JshDao {

	private final SqlSession session;
	
	@Override
	public List<Class_ScheduleAddVideo> studentLecture(String lctr_id, int user_seq) {
		System.out.println("JshDao StudentLecture start...");
		List<Class_ScheduleAddVideo> contentList = null;
		
		Map<String, Object> infor = new HashMap<>();
		infor.put("lctr_id", lctr_id);
		infor.put("user_seq", user_seq);
		
		System.out.println("JshDao StudentLecture infor >> "+infor);
		
		try {
			contentList = session.selectList("com.postgre.choongsam.mapper.Sh_mapper.studentLecture", infor);
			System.out.println("JshDao StudentLecture contentList >> "+contentList);
		} catch (Exception e) {
			System.out.println("JshDao StudentLecture exception >> "+e.getMessage());
		}
		
		return contentList;
	}

}
