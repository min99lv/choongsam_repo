package com.postgre.choongsam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Lecture_Video;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HmsDaoImpl implements HmsDao {
	
	public final SqlSession session;

	//postgre 모든 영상 불러오기
	@Override
	public List<Lecture_Video> findAllVideo() {
		System.out.println("msDao findAllVideo start..");
		List<Lecture_Video> videoList = null;
		try {
			videoList = session.selectList("com.postgre.choongsam.mapper.HMS.findAllVideo");
			System.out.println("msDao findAllVideo videoList->"+videoList);
		} catch (Exception e) {
			System.out.println("msDao findAllVideo error->"+e.getMessage());
		}
		return videoList;
	}

	//api data저장
	@Override
	public void APIdata(String videoId) {
		System.out.println("msDao APIdata start..");
		try {
			int result = session.update("com.postgre.choongsam.mapper.HMS.APIdata",videoId);
		} catch (Exception e) {
			System.out.println("msDao APIdata error->"+e.getMessage());
		}
		
		
	}

	//뷰에 있는 데이터 저장
	@Override
	public void saveWatchTime(Class_Schedule class_schedule) {
		System.out.println("msDao saveWatchTime start...");
		System.out.println("msDao saveWatchTime schedule->"+class_schedule);
		int result = 0 ;
		try {
			result = session.insert("com.postgre.choongsam.mapper.HMS.saveWatchTime",class_schedule);
		} catch (Exception e) {
			System.out.println("msDao saveWatchTime error->"+e.getMessage());
		}
		
	}

	
	
	

}
