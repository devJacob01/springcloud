package com.koscom.microservices.sample;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value="Hello Service API")
@RestController
public class HelloController {
	private String msgTemplate = "%s님  반갑습니다.";
	private final AtomicLong  vistorConouter = new AtomicLong();
	
	@Autowired
	private SampleUserDao sampleUserDao;
		
	
	
	
	@ApiOperation(value="사용자 정보 가져오기  DB ")
	@RequestMapping(value="/user/list", method=RequestMethod.GET)
	public ResponseEntity <List<SampleUser>> getUserList() { 
		
		List<SampleUser> list = null;
		try {
			log.info("Start db select");
			list = sampleUserDao.selectUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("user counts :"+list.size());
		
		return new ResponseEntity<List<SampleUser>> (list, HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 가져오기  DB ")
	@RequestMapping(value="/db/test", method=RequestMethod.GET)
	public ResponseEntity <String> getUserList2() { 
		
		int test = -1;
		try {
			log.info("Start db select");
			test = sampleUserDao.selectTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("user counts :"+test);
		
		return new ResponseEntity<String> (test+"", HttpStatus.OK);
	}	

}
