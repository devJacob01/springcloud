package com.koscom.microservices.sample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SampleUserDao {

	/**
	 * 사용자 전체 정보 가져오기 
	 * @return
	 * @throws Exception
	 */
	List<SampleUser> selectUser() throws Exception;		
	
	int selectTest() throws Exception;		
}
			