package com.postgre.choongsam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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


	
	

}
