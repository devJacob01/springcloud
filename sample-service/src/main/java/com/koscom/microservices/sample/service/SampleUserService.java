package com.koscom.microservices.sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koscom.microservices.sample.model.SampleUser;

@Service
public interface SampleUserService {
	/**
	 * 사용자 정보 가져오
	 * @return
	 */
	public List<SampleUser> selectUser();	
}
