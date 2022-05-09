package com.guohaohome.moviedb.unitTest.sqlAPITest;

import com.guohaohome.moviedb.dao.UserLikeMapper;
import com.guohaohome.moviedb.sqlEntity.UserLike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@DirtiesContext
public class UserLikeMapperTests {
    @Autowired
    UserLikeMapper userLikeMapper;
    @DirtiesContext
    @Test
    public void testGetUserLikes(){
        List<UserLike> userLikeList = userLikeMapper.getUserLikes("testUserName");
        assertEquals(2,userLikeList.size());
        assertEquals("testId",userLikeList.get(0).getId());
        assertEquals("testId2",userLikeList.get(1).getId());
    }
    @DirtiesContext
    @Test
    public void testInsertUserLikeAndDeleteUserLike(){
        UserLike userLike = new UserLike("testInsertUserLikeAndDeleteUserLikeUsername"
                ,"testInsertUserLikeAndDeleteUserLikeID");
        userLikeMapper.insertUserLike(userLike);
        List<UserLike> userLikeList = userLikeMapper.getUserLikes("testInsertUserLikeAndDeleteUserLikeUsername");
        assertEquals(1,userLikeList.size());
        assertEquals("testInsertUserLikeAndDeleteUserLikeUsername",userLikeList.get(0).getUsername());
        assertEquals("testInsertUserLikeAndDeleteUserLikeID",userLikeList.get(0).getId());
    }
    @DirtiesContext
    @Test
    public void testDeleteUserLikeByID(){
        UserLike userLike = new UserLike("testDeleteUserLikeByIDUsername","testDeleteUserLikeByIDID");
        userLikeMapper.insertUserLike(userLike);
        userLike = new UserLike("testDeleteUserLikeByIDUsername2","testDeleteUserLikeByIDID");
        userLikeMapper.insertUserLike(userLike);
        assertEquals(1,userLikeMapper.getUserLikes("testDeleteUserLikeByIDUsername").size());
        assertEquals(1,userLikeMapper.getUserLikes("testDeleteUserLikeByIDUsername2").size());
        userLikeMapper.deleteUserLikeByID("testDeleteUserLikeByIDID");
        assertEquals(0,userLikeMapper.getUserLikes("testDeleteUserLikeByIDUsername").size());
        assertEquals(0,userLikeMapper.getUserLikes("testDeleteUserLikeByIDUsername2").size());
    }

}
