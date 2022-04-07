package com.guohaohome.moviedb.dao;

import com.guohaohome.moviedb.sqlEntity.Movie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface MovieMapper {
    void insertMovie (Movie newMovie);
    void deleteMovieByID(String ID);
    String getNameByID (String ID);
    String getIDByName (String name);
    void updateNameByID(String ID , String newName);
    List<String> getAllID();
}
