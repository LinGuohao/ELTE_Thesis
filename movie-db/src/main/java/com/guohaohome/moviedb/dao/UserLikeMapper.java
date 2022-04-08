package com.guohaohome.moviedb.dao;

import com.guohaohome.moviedb.sqlEntity.UserLike;

import java.util.List;

public interface UserLikeMapper {
    List<UserLike> getUserLikes(String username);
    void insertUserLike(UserLike userLike);
    void deleteUserLike(UserLike userLike);
    void deleteUserLikeByID(String ID);
}
