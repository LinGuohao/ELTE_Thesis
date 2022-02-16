package com.guohaohome.moviedb.sqlApiTest;


import com.guohaohome.moviedb.dao.MovieMapper;
import com.guohaohome.moviedb.sqlEntity.Movie;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@FixMethodOrder(MethodSorters.JVM)
public class MovieMapperTester {

    @Autowired
    MovieMapper movieMapper;

    @Test
    public void testGetNameByID() {
        String name = movieMapper.getNameByID("1234567");
        assertEquals("testGetNameByID", name);
    }

    @Test
    public void testGetIDByName() {
        String ID = movieMapper.getIDByName("testGetIDByName");
        assertEquals("12345", ID);
    }

    @Test
    public void testInsertMovie() {
        int num = movieMapper.getAllID().size();
        Movie movie = new Movie("123456789", "testInsertMovie");
        movieMapper.insertMovie(movie);
        int newNum = movieMapper.getAllID().size();
        assertEquals(num+1 , newNum);
    }

    @Test
    public void testGetAllID() {
        int num = movieMapper.getAllID().size();
        Movie movie = new Movie("1234567890", "testGetAllID");
        movieMapper.insertMovie(movie);
        int newNum = movieMapper.getAllID().size();
        assertEquals(num+1,newNum);
        List<String> allId = movieMapper.getAllID();
        assert (allId.contains("1234567"));
        assert (allId.contains("12345"));

    }

}
