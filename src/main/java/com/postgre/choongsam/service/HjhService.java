package com.postgre.choongsam.service;

import java.util.List;
import java.util.Map;

import com.postgre.choongsam.dto.Course_Registration;
import com.postgre.choongsam.dto.Lecture;
import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.dto.User_Info;


public interface HjhService {

	List<User_Info> userList(Map<String, Object> params);

	int totaluser(String keyword);

	User_Info detailProfile(String user_id);

	int userProfile1(User_Info info);

	List<User_Info> profileupdate(String user_id);

	User_Info detailProfileuser(String userId);

	int deleteStd1(String user_id, String password);

	Login_Info getLoginInfo(String user_id);

	int updateCountAdmin(User_Info info);

	int changePW(String password,String userId);

	Login_Info changepassword(String userId);

	List<Lecture> lectureList(int userSeq);

	List<Course_Registration> sugangStu(int userSeq);




}
