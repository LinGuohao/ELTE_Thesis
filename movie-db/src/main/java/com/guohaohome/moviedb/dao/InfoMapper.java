package com.guohaohome.moviedb.dao;

import com.guohaohome.moviedb.sqlEntity.Info;
import org.apache.ibatis.annotations.Param;

public interface InfoMapper {
        Info getInfoByID (String ID);
        int updateIMDbByID (@Param("ID") String ID,  @Param("newIMDb") double newIMDb);
        int updateTomatoesByID (@Param("ID") String ID, @Param("newTomatoes") int newTomatoes);
        int insertInfo(Info newInfo);
}