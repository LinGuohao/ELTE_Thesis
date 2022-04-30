package com.guohaohome.moviedb.sqlApiTest;

import com.guohaohome.moviedb.dao.UserLikeMapper;
import com.guohaohome.moviedb.sqlEntity.UserLike;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
public class UserLikeMapperTester {
    @Autowired
    UserLikeMapper userLikeMapper;

}
