package com.postgre.choongsam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.JshDao;
import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Class_ScheduleAddVideo;

import jakarta.transaction.Transactional;
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

	@Transactional
	@Override
	public void contsUpload(Class_ScheduleAddVideo info) {
		System.out.println("JshService contsUpload start...");
		int result=0;
		
		String fileName = info.getFile_nm();
		String fileSuffix= info.getFile_extn_nm();
		long file_sz = info.getFile_sz();
		String file_path = info.getFile_path_nm();
		
		
		//첨부파일이 있는 경우 먼저 등록
		if(fileName!=null&&fileSuffix!=null&&file_path!=null) {
			result = Dao.contsFileUpload(info);
			
			if(result==0) {
				System.out.println("JshService contsUpload 첨부파일 등록 실패...");
			}
			else {
				System.out.println("JshService contsUpload 첨부파일 등록 성공");
			}
			
			result = 0;
			result = Dao.fileLectureVideoUpload(info);
			if(result==0) {
				System.out.println("JshService fileLectureVideoUpload 강의영상 등록 실패...");
			}
			else {
				System.out.println("JshService fileLectureVideoUpload 강의영상 등록 성공");
			}
		}
		else {
			//첨부파일이 없는 경우 업로드
			result = 0;
			result = Dao.lectureVideoUpload(info);
			if(result==0) {
				System.out.println("JshService lectureVideoUpload 강의영상 등록 실패...");
			}
			else {
				System.out.println("JshService lectureVideoUpload 강의영상 등록 성공");
			}
		}
		
		//강의계획서상세(차시) 업로드
		result = 0;
		result = Dao.syllabusUpload(info);
		if(result==0) {
			System.out.println("JshService syllabusUpload 강의계획서 등록 실패...");
		}
		else {
			System.out.println("JshService syllabusUpload 강의계획서 등록 성공");
		}
		
		
		Integer chp_time1 = info.getConts_chptime();
		
		//영상챕터가 있다면 업로드
		if(chp_time1!=null&&chp_time1!=0) {
			result = 0;
			result = Dao.chpTimeUpload(info);
			if(result==0) {
				System.out.println("JshService chpTimeUpload 강의챕터 등록 실패...");
			}
			else {
				System.out.println("JshService chpTimeUpload 강의챕터 등록 성공");
			}
		}
		
		
		
	}

}
