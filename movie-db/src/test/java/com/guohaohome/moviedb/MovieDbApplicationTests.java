package com.guohaohome.moviedb;

import com.guohaohome.moviedb.controller.Init;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@ActiveProfiles("test")
@DirtiesContext
class MovieDbApplicationTests {
	@Test
	@DirtiesContext
	void contextLoads() {
	}

}
