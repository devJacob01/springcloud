package com.koscom.microservices.sample;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	@RequestMapping(value="/users", method=RequestMethod.GET)
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
	
	@ApiOperation(value="사용자 정보 가져오기 2 - TypeAlias ")
	@RequestMapping(value="/users2", method=RequestMethod.GET)
	public ResponseEntity <List<SampleUser>> getUserList2() { 
		
		List<SampleUser> list = null;
		try {
			log.info("Start db select");
			list = sampleUserDao.selectUser2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("user counts :"+list.size());
		
		return new ResponseEntity<List<SampleUser>> (list, HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 변경하기 ")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.POST)
	public ResponseEntity <String > setUserUpdate(
			@PathVariable(name="userId",required = true ) String userId, 
			@RequestBody SampleUser sampleUer
		) throws Exception { 
		
		List<SampleUser> list = null;
		log.info("Start db update");
		sampleUer.setUserId(userId);
		int re  = sampleUserDao.updateUser(sampleUer);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 등록하기 ")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.PUT)
	public ResponseEntity <String > setUserInsert(
			@PathVariable(name="userId",required = true ) String userId, 
			@RequestBody SampleUser sampleUer
		) throws Exception { 
		
		List<SampleUser> list = null;
		log.info("Start db insert");
		sampleUer.setUserId(userId);
		int re  = sampleUserDao.insertUser(sampleUer);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 삭하기 ")
	@RequestMapping(value="/users/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity <String > setUserDelete(
			@PathVariable(name="userId",required = true ) String userId
		) throws Exception { 
		
		List<SampleUser> list = null;
		log.info("Start db insert");
		int re  = sampleUserDao.deleteUser(userId);
		log.debug("result :"+ re);
		
		return new ResponseEntity<String> (re+"", HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 가져오기  DB ")
	@RequestMapping(value="/db/test", method=RequestMethod.GET)
	public ResponseEntity <String> getUserListTest() { 
		
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

	
	@ApiOperation(value="다른 서비스 정보 가져오 ")
	@RequestMapping(value="/other/service", method=RequestMethod.GET)
	public ResponseEntity <Object> geOtherService() { 
		
		log.info("Start call other services api");
		
	    final String uri = "http://localhost:2222/accounts/list";
	    RestTemplate restTemplate = new RestTemplate();
	     
	    Object result = restTemplate.getForObject(uri, Object.class);
	     
		
		return new ResponseEntity<Object> (result, HttpStatus.OK);
	}	
}
