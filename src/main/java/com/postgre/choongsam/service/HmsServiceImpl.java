package com.postgre.choongsam.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HmsServiceImpl implements HmsService{
	
	@Value("${youtube.api.key}")
	private String apiKey;
	
	@Value("${youtube.api.base-url}")
	private String baseUrl;
	
	public String getVideoDetails(String videoId) {
		String url = String.format("%s/videos?id=%s&key=%s&part=snippet", baseUrl, videoId,apiKey);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, String.class);
	}

}
