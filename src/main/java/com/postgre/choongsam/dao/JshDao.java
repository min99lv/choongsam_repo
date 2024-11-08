package com.postgre.choongsam.dao;

import java.util.List;

import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Class_ScheduleAddVideo;

public interface JshDao {

	List<Class_ScheduleAddVideo> studentLecture(String lctr_id, int user_seq);

	List<Class_ScheduleAddVideo> getStartDay(String lctr_id);

	int contsFileUpload(Class_ScheduleAddVideo info);

	int lectureVideoUpload(Class_ScheduleAddVideo info);

	int fileLectureVideoUpload(Class_ScheduleAddVideo info);

	int syllabusUpload(Class_ScheduleAddVideo info);

	int chpTimeUpload(Class_ScheduleAddVideo info);

	int searchSche(String chashi, String conts_id, String user_seq);

	int insertSche(String chashi, String conts_id, String lctr_id, String user_seq);

	List<Class_ScheduleAddVideo> searchTeachConts(String lctr_id, int user_seq);

}
