package com.postgre.choongsam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.postgre.choongsam.dto.Login_Info;
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

	@Override
	public int deleteStd1(String user_id, String password) {
	    int deleteStd1 = 0;
	    Map<String, Object> params = new HashMap<>();
	    params.put("user_id", user_id);
	    params.put("password", password);  // 비밀번호는 이미 검증되었으므로 그대로 전달

	    try {
	        deleteStd1 = session.update("deleteStd1", params);
	    } catch (Exception e) {
	        e.printStackTrace();  // 예외 발생 시 출력하여 문제를 추적
	    }

	    return deleteStd1;
	}

	@Override
	public Login_Info getLoginInfo(String userId) {
	    Login_Info getLoginInfo = null;
	    try {
	        // userId를 파라미터로 전달하여 쿼리 실행
	        getLoginInfo = session.selectOne("getLoginInfo", userId);
	    } catch (Exception e) {
	        e.printStackTrace();  // 예외 발생 시 스택 트레이스를 출력하여 원인 파악
	    }
	    return getLoginInfo;
	}

	@Override
	public int updateCountAdmin(User_Info info) {
		System.out.println("info"+info);
		int updateCountAdmin = 0;
		try {
			updateCountAdmin = session.update("updateCountAdmin",info);
		} catch (Exception e) {
			System.out.println("error"+e.getMessage());
			System.out.println("userProfile1"+info);
		}
		return updateCountAdmin;
	}



}
