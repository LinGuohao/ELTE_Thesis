package com.guohaohome.moviedb.unitTest.sqlAPITest;


import com.guohaohome.moviedb.dao.MovieMapper;
import com.guohaohome.moviedb.sqlEntity.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@DirtiesContext
public class MovieMapperTester {

    @Autowired
    MovieMapper movieMapper;

    @Test
    public void testGetNameByID() {
        String name = movieMapper.getNameByID("testMovieId");
        assertEquals("testMovieName", name);
    }

    @Test
    public void testGetIDByName() {
        String ID = movieMapper.getIDByName("testMovieName");
        assertEquals("testMovieId", ID);
    }

    @Test
    public void testInsertMovieAndDeleteMovieByID() {
        Movie movie = new Movie("testInsertMovieAndDeleteMovieByIDID"
                , "testInsertMovieAndDeleteMovieByIDName");
        movieMapper.insertMovie(movie);
        assertNotNull(movieMapper.getNameByID("testInsertMovieAndDeleteMovieByIDID"));
        movieMapper.deleteMovieByID("testInsertMovieAndDeleteMovieByIDID");
        assertNull(movieMapper.getNameByID("testInsertMovieAndDeleteMovieByIDID"));
    }

    @Test
    public void testGetAllID() {
        List<String> movieId = movieMapper.getAllID();
        assertEquals(2, movieId.size());
        assertEquals("testMovieId", movieId.get(0));
        assertEquals("testMovieId2", movieId.get(1));
    }

    @Test
    public void testUpdateNameByID() {
        Movie movie = new Movie("testUpdateNameByIDID", "testUpdateNameByIDName");
        movieMapper.insertMovie(movie);
        String movieName = movieMapper.getNameByID("testUpdateNameByIDID");
        assertEquals("testUpdateNameByIDName", movieName);
        movieMapper.updateNameByID("testUpdateNameByIDID", "testUpdateNameByIDName2");
        movieName = movieMapper.getNameByID("testUpdateNameByIDID");
        assertEquals("testUpdateNameByIDName2", movieName);
        movieMapper.deleteMovieByID("testUpdateNameByIDID");

    }
}
