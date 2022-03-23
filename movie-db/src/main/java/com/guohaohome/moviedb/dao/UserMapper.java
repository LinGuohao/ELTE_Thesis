package com.guohaohome.moviedb.dao;

import com.guohaohome.moviedb.sqlEntity.User;

public interface UserMapper {
    void insertUser(User user);
    User getUserByUserName(String UserName);

}
