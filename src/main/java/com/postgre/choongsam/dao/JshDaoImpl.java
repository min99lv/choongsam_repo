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

	@Override
	public List<Class_ScheduleAddVideo> getStartDay(String lctr_id) {
		System.out.println("JshDao getStartDay start...");
		System.out.println("JshDao getStartDay lctr_id >> "+lctr_id);
		
		List<Class_ScheduleAddVideo> startday = null;

		try {
			startday = session.selectList("getStartDay",lctr_id);
			System.out.println("JshDao getStartDay startday 아니 왜 null인데 너가 나와>> " + startday);
		} catch (Exception e) {
			System.out.println("JshDao getStartDay exception>> "+e.getMessage());
		}
		
		return startday;
	}

	@Override
	public int contsFileUpload(Class_ScheduleAddVideo info) {
		System.out.println("JshDao contsFileUpload start...");
		
		int result = 0;
		
		String fileName = info.getFile_nm();
		String fileSuffix= info.getFile_extn_nm();
		long file_sz = info.getFile_sz();
		String file_path = info.getFile_path_nm();
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

}
