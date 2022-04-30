package com.guohaohome.moviedb.sqlApiTest;

import com.guohaohome.moviedb.dao.UserMapper;
import com.guohaohome.moviedb.sqlEntity.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
public class UserMapperTester {
    @Autowired
    UserMapper userMapper;
    @Test
    public void testGetUserByUserName(){
        User user = userMapper.getUserByUserName("testUserName@test.com");
        assertEquals("testPassword",user.getPassword());
        assertEquals("testFullname",user.getFullName());
        assertEquals("testRoles",user.getRoles());
    }

    @Test
    public void testInsertUserAndDeleteUser(){
        User user = new User("testInsertUserAndDeleteUserUserName","testInsertUserAndDeleteUserPassword"
                ,"testInsertUserAndDeleteUserFullName","user");
        userMapper.insertUser(user);
        user = userMapper.getUserByUserName("testInsertUserAndDeleteUserUserName");
        assertEquals("testInsertUserAndDeleteUserPassword",user.getPassword());
        assertEquals("testInsertUserAndDeleteUserFullName",user.getFullName());
        assertEquals("user",user.getRoles());
        userMapper.deleteUserByUserName("testInsertUserAndDeleteUserUserName");
        assertNull(userMapper.getUserByUserName("testInsertUserAndDeleteUserUserName"));

    }
}
