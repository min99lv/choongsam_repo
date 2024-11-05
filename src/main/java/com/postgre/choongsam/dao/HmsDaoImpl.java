package com.postgre.choongsam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Class_Schedule;
import com.postgre.choongsam.dto.Lecture_Video;
import com.postgre.choongsam.dto.Syllabus;

import jakarta.transaction.Transactional;
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


	//class_schedule업데이트
	@Override
	public void saveWatchTime(Class_Schedule class_schedule) {
		System.out.println("msDao saveWatchTime start...");
		System.out.println("msDao saveWatchTime schedule->"+class_schedule);
		int result = 0 ;
		try {
			result = session.update("com.postgre.choongsam.mapper.HMS.saveWatchTime",class_schedule);
			System.out.println("msDao saveWatchTime result->"+result);
		} catch (Exception e) {
			System.out.println("msDao saveWatchTime error->"+e.getMessage());
		}	
	}
	//max값 빼고 없데이트
		@Override
		public void saveWatchTimeNoMaxUpdate(Class_Schedule class_schedule) {
			System.out.println("msDao saveWatchTimeNoMaxUpdate start...");
			System.out.println("msDao saveWatchTimeNoMaxUpdate schedule->"+class_schedule);
			int result = 0 ;
			try {
				result = session.update("com.postgre.choongsam.mapper.HMS.noMaxUpdate",class_schedule);
				System.out.println("msDao saveWatchTimeNoMaxUpdate result->"+result);
			} catch (Exception e) {
				System.out.println("msDao saveWatchTimeNoMaxUpdate error->"+e.getMessage());
			}
			
		}

	
	//syllabus테이블에서 videoId로 lctr_정보 가져오기
	@Override
	public Syllabus findLctrInfo(String videoId) {
		System.out.println("msDao findLctrInfo start...");
		Syllabus info = null;
		try {
			info = session.selectOne("com.postgre.choongsam.mapper.HMS.findLctrInfo", videoId);
		} catch (Exception e) {
			System.out.println("msDao findLctrInfo error->"+e.getMessage());
		}
		return info;
	}

	//max값
	@Override
	public long findCurrentMax(String videoId) {
		System.out.println("msDao findCurrentMax start..");
		int currentMax = 0;
		try {
			currentMax = session.selectOne("com.postgre.choongsam.mapper.HMS.CurrentMax",videoId);
		} catch (Exception e) {
			System.out.println("msDao findCurrentMax error->"+e.getMessage());
		}
		return currentMax;
	}

	//final
	@Override
	public int watchedFinalTime(String videoId) {
		System.out.println("msDao watchedFinalTime start..");
		System.out.println("msDao watchedFinalTime videoId.."+videoId);
		int result = 0;
		try {
			result = session.selectOne("com.postgre.choongsam.mapper.HMS.finalTime",videoId);
		} catch (Exception e) {
			System.out.println("msDao watchedFinalTime error->"+e.getMessage());
		}
		return result;
	}

	//파일 다운로드
	@Override
	public String getFilePath(String filename) {
	    System.out.println("msDao getFilePath start...");
	    String result = null;
	    try {
	        System.out.println("filename: " + filename);  // filename 출력
	        result = session.selectOne("com.postgre.choongsam.mapper.HMS.FileDown", filename);
	        
	        if (result == null) {
	            System.out.println("No file path found for filename: " + filename);
	        } else {
	            System.out.println("File path retrieved: " + result);
	        }
	    } catch (Exception e) {
	        System.out.println("msDao getFilePath error->" + e.getMessage());
	        e.printStackTrace();  // 예외의 전체 스택 트레이스를 출력
	    }
	    
	    return result;
	}

	


	
	
	
	
	
	

}
