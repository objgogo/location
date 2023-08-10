package com.api.location;

import com.api.location.entity.MemberEntity;
import com.api.location.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocationApplicationTests {

	@Autowired
	private LocationService locationService;

	@Test
	void contextLoads() {
	}

	@Test
	void insertMember(){
		locationService.insertMember("테스터");
	}

}
