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
		
		Map<String, Object> fileInfo = new HashMap<>();
		
		fileInfo.put("fileName", fileName);
		fileInfo.put("fileSuffix", fileSuffix);
		fileInfo.put("file_sz", file_sz);
		fileInfo.put("file_path", file_path);
		
		try {
			result = session.insert("contsFileUpload",fileInfo);
		} catch (Exception e) {
			System.out.println("JshDao contsFileUpload exception >> "+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int fileLectureVideoUpload(Class_ScheduleAddVideo info) {
		System.out.println("JshDao fileLectureVideoUpload start...");
		
		int result = 0;
		
		String conts_id = info.getConts_id();
		String title = info.getVdo_file_nm();
		String conts_url = info.getVdo_url_addr();
		String viewing_period = info.getViewing_period();
		Integer vdo_length = info.getVdo_length();
		int chashi = info.getLctr_no();
		int file_group = info.getFile_group();
		int lctr_no = info.getLctr_no();
		
		Map<String, Object> lectureInfo = new HashMap<>();
		
		lectureInfo.put("conts_id", conts_id);
		lectureInfo.put("title", title);
		lectureInfo.put("conts_url", conts_url);
		lectureInfo.put("viewing_period", viewing_period);
		lectureInfo.put("vdo_length", vdo_length);
		lectureInfo.put("chashi", chashi);
		lectureInfo.put("file_group", file_group);
		lectureInfo.put("lctr_no", lctr_no);
		
		
		try {
			result = session.insert("fileLectureVideoUpload", lectureInfo);
		} catch (Exception e) {
			System.out.println("JshDao lectureVideoUpload exception >> "+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int lectureVideoUpload(Class_ScheduleAddVideo info) {
		System.out.println("JshDao lectureVideoUpload start...");
		
		int result = 0;
		
		String conts_id = info.getConts_id();
		String title = info.getVdo_file_nm();
		String conts_url = info.getVdo_url_addr();
		String viewing_period = info.getViewing_period();
		Integer vdo_length = info.getVdo_length();
		int chashi = info.getLctr_no();
		int lctr_no = info.getLctr_no();
		
		Map<String, Object> lectureInfo = new HashMap<>();
		
		lectureInfo.put("conts_id", conts_id);
		lectureInfo.put("title", title);
		lectureInfo.put("conts_url", conts_url);
		lectureInfo.put("viewing_period", viewing_period);
		lectureInfo.put("vdo_length", vdo_length);
		lectureInfo.put("chashi", chashi);
		lectureInfo.put("lctr_no", lctr_no);
		
		try {
			result = session.insert("lectureVideoUpload", lectureInfo);
		} catch (Exception e) {
			System.out.println("JshDao lectureVideoUpload exception >> "+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int syllabusUpload(Class_ScheduleAddVideo info) {
		System.out.println("JshDao lectureVideoUpload start...");
		int result = 0;
		
		int lctr_no = info.getLctr_no();
		String lctr_id = info.getLctr_id();
		String conts_id = info.getConts_id();
		int user_seq = info.getUser_seq();
		String lctr_otln = info.getLctr_otln();
		
		Map<String, Object> syllabusInfo = new HashMap<>();
		
		syllabusInfo.put("lctr_no", lctr_no);
		syllabusInfo.put("lctr_id", lctr_id);
		syllabusInfo.put("conts_id", conts_id);
		syllabusInfo.put("user_seq", user_seq);
		syllabusInfo.put("lctr_otln", lctr_otln);
		
		try {
			result = session.insert("syllabusUpload", syllabusInfo);
		} catch (Exception e) {
			System.out.println("JshDao syllabusUpload exception >> "+e.getMessage());
		}
		
		
		return result;
	}

	@Override
	public int chpTimeUpload(Class_ScheduleAddVideo info) {
		System.out.println("JshDao chpTimeUpload start...");
		int result = 0;
		
		String conts_id = info.getConts_id();
		Integer chp_time = info.getConts_chptime();
		String cap_ttl = info.getConts_chpttl();
		Integer chp_time2 = info.getConts_chptime2();
		String cap_ttl2 = info.getConts_chpttl2();
		Integer chp_time3 = info.getConts_chptime3();
		String cap_ttl3 = info.getConts_chpttl3();
		
		System.out.println("dao >> "+chp_time);
		System.out.println("dao >> "+chp_time2);
		System.out.println("dao >> "+chp_time3);
		
		Map<String, Object> chpInfo = new HashMap<>();
		chpInfo.put("conts_id", conts_id);
		chpInfo.put("chp_time", chp_time);
		chpInfo.put("cap_ttl", cap_ttl);
		
		try {
			result = session.insert("chpTimeUpload", chpInfo);
			
			if(chp_time2!=null&&chp_time2!=0) {
				result = 0;
				chpInfo.put("chp_time", chp_time2);
				chpInfo.put("cap_ttl", cap_ttl2);
				
				result = session.insert("chpTimeUpload", chpInfo);
			}
			else if(chp_time3!=null&&chp_time3!=0) {
				result=0;
				chpInfo.put("chp_time", chp_time3);
				chpInfo.put("cap_ttl", cap_ttl3);
				
				result = session.insert("chpTimeUpload", chpInfo);
			}
		} catch (Exception e) {
			System.out.println("JshDao chpTimeUpload exception >> "+e.getMessage());
		}
		return result;
	}

}
