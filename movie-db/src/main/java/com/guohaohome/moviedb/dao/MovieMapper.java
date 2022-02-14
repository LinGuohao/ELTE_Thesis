package com.guohaohome.moviedb.dao;

import com.guohaohome.moviedb.sqlEntity.Movie;

public interface MovieMapper {
    int insertMovie (Movie newMovie);
    int getNameByID (String ID);
    int getIDByName (String name);
}
