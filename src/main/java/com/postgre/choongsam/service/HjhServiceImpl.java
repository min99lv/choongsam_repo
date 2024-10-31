package com.postgre.choongsam.service;

import org.springframework.stereotype.Service;

import com.postgre.choongsam.dao.HjhDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HjhServiceImpl implements HjhService {
	private final HjhDao hjhdao;

}
