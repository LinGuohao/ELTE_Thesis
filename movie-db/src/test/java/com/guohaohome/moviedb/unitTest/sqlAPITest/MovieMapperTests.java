package com.guohaohome.moviedb.unitTest.sqlAPITest;


import com.guohaohome.moviedb.dao.MovieMapper;
import com.guohaohome.moviedb.sqlEntity.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@DirtiesContext
public class MovieMapperTests {

    @Autowired
    MovieMapper movieMapper;

    @Test
    @DirtiesContext
    public void testGetNameByID() {
        String name = movieMapper.getNameByID("testMovieId");
        assertEquals("testMovieName", name);
    }

    @Test
    @DirtiesContext
    public void testGetIDByName() {
        String ID = movieMapper.getIDByName("testMovieName");
        assertEquals("testMovieId", ID);
    }

    @Test
    @DirtiesContext
    public void testInsertMovieAndDeleteMovieByID() {
        Movie movie = new Movie("testInsertMovieAndDeleteMovieByIDID"
                , "testInsertMovieAndDeleteMovieByIDName");
        movieMapper.insertMovie(movie);
        assertNotNull(movieMapper.getNameByID("testInsertMovieAndDeleteMovieByIDID"));
        movieMapper.deleteMovieByID("testInsertMovieAndDeleteMovieByIDID");
        assertNull(movieMapper.getNameByID("testInsertMovieAndDeleteMovieByIDID"));
    }

    @Test
    @DirtiesContext
    public void testGetAllID() {
        List<String> movieId = movieMapper.getAllID();
        assertEquals(2, movieId.size());
        assertEquals("testMovieId", movieId.get(0));
        assertEquals("testMovieId2", movieId.get(1));
    }

    @Test
    @DirtiesContext
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
