package com.postgre.choongsam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.JshDao;
import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Class_ScheduleAddVideo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JshServiceImpl implements JshService {
	
	private final JshDao Dao;
	
	@Override
	public List<Class_ScheduleAddVideo> studentLecture(String lctr_id, int user_seq) {
		System.out.println("JshService StudentLecture start...");
		List<Class_ScheduleAddVideo> contentList = Dao.studentLecture(lctr_id, user_seq);
		System.out.println("JshService StudentLecture contentList >> "+contentList);
		
		return contentList;
	}

	@Override
	public List<Class_ScheduleAddVideo> getStartDay(String lctr_id) {
		System.out.println("JshService getStartDay start...");
		
		List<Class_ScheduleAddVideo> startDay = Dao.getStartDay(lctr_id);
		
		return startDay;
	}

	@Override
	public void contsUpload(Class_ScheduleAddVideo info) {
		System.out.println("JshService contsUpload start...");
		int result=0;
		
		String fileName = info.getFile_nm();
		String fileSuffix= info.getFile_extn_nm();
		long file_sz = info.getFile_sz();
		String file_path = info.getFile_path_nm();
		
		if(fileName!=null&&fileSuffix!=null&&file_path!=null) {
			result = Dao.contsFileUpload(info);
			System.out.println("JshService contsUpload >> "+result);
		}
		
		
	}

}
