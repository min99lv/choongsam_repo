package com.postgre.choongsam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.User_Info;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HjhDaoImpl implements HjhDao {
	private final SqlSession session;

	@Override
	public List<User_Info> teastdlist(Map<String, Object> params) {
		List<User_Info> listteastd = null;
		try {
			listteastd = session.selectList("teastdlist",params);
		} catch (Exception e) {
		System.out.println("다오왔냐");
		}
		return listteastd;
	}

	@Override
	public int totaluser(String keyword) {
		int totalusercount = 0;
		try {
			totalusercount = session.selectOne("totalusercount",keyword);
		} catch (Exception e) {
			System.out.println("error"+e.getMessage());
		}
		return totalusercount;
	}

	@Override
	public User_Info detailProfile(String user_id) {
	    User_Info detailProfile = null;
	    Map<String, Object> params = new HashMap<>();
	    params.put("USER_ID", user_id); // 키를 수정했습니다.
	    detailProfile = session.selectOne("detailProfile", params);
	    
	    return detailProfile;
	}

	@Override
	public int userProfile1(User_Info info) {
		System.out.println("info"+info);
		int userProfile1 = 0;
		try {
			userProfile1 = session.update("userProfile1",info);
		} catch (Exception e) {
			System.out.println("error"+e.getMessage());
			System.out.println("userProfile1"+info);
		}
		return userProfile1;
	}

	@Override
	public List<User_Info> profileupdate(String user_id) {
		System.out.println("user_id"+user_id);
		List<User_Info> profileupdate = null;
		try {
			profileupdate = session.selectOne("profileupdate",user_id);
		} catch (Exception e) {
			System.out.println("error"+e.getMessage());
		}
		return profileupdate;
	}

	@Override
	public User_Info detailProfileuser(String userId) {
	    User_Info detailProfileuser = null;
	    System.out.println("userId: " + userId); // userId 확인
	    
	    try {
	        // SQL 쿼리 실행
	        detailProfileuser = session.selectOne("detailProfileuser", userId);
	        
	        // 결과 확인
	        if (detailProfileuser != null) {
	            System.out.println("User Info retrieved: " + detailProfileuser);
	        } else {
	            System.out.println("No user info found for userId: " + userId);
	        }
	    } catch (Exception e) {
	        System.out.println("Error occurred: " + e.getMessage());
	    }
	    
	    return detailProfileuser;
	}



}
