package com.postgre.choongsam.service;

import com.postgre.choongsam.dto.Login_Info;

public interface LjmService {

	Login_Info login(String user_id, String password);
	
}
