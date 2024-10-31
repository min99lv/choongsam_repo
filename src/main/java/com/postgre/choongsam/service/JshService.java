package com.postgre.choongsam.service;

import java.util.List;

import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Class_ScheduleAddVideo;

public interface JshService {

	List<Class_ScheduleAddVideo> studentLecture(String lctr_id, int user_seq);

	List<Class_ScheduleAddVideo> getStartDay(String lctr_id);

	void contsUpload(Class_ScheduleAddVideo info);

}
