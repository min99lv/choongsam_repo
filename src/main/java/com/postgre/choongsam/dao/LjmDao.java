package com.postgre.choongsam.dao;

import com.postgre.choongsam.dto.Login_Info;
import com.postgre.choongsam.dto.User_Info;

public interface LjmDao {

	Login_Info login(String user_id);

	int signup(Login_Info login_Info, User_Info user_info);

	int confirmId(String user_id);

	String findId(User_Info user_info);
	
}
