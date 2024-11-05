package com.postgre.choongsam.service;

import java.util.List;
import java.util.Map;

import com.postgre.choongsam.dto.User_Info;


public interface HjhService {

	List<User_Info> userList(Map<String, Object> params);

	int totaluser(String keyword);

	User_Info detailProfile(String user_id);

}
