package com.postgre.choongsam.dao;

import java.util.List;
import java.util.Map;

import com.postgre.choongsam.dto.User_Info;

public interface HjhDao {

	List<User_Info> teastdlist(Map<String, Object> params);

	int totaluser(String keyword);

	User_Info detailProfile(String user_id);

}
