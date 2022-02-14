package com.guohaohome.moviedb;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@ActiveProfiles("test")
class MovieDbApplicationTests {

	@Test
	void contextLoads() {
	}

}
