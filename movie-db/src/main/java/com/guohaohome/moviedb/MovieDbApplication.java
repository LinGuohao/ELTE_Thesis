package com.guohaohome.moviedb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/guohaohome/moviedb/dao")
public class MovieDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDbApplication.class, args);
	}

}
