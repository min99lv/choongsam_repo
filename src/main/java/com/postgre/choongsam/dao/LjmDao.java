package com.postgre.choongsam.dao;

import com.postgre.choongsam.dto.Login_Info;

public interface LjmDao {

	Login_Info login(String user_id);
	
}
