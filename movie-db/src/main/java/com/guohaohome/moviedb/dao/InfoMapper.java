package com.guohaohome.moviedb.dao;

import com.guohaohome.moviedb.sqlEntity.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface InfoMapper {
        Info getInfoByID (String ID);
        void updateIMDbByID (@Param("ID") String ID,  @Param("newIMDb") double newIMDb);
        void updateTomatoesByID (@Param("ID") String ID, @Param("newTomatoes") int newTomatoes);
        void insertInfo(Info newInfo);
        void updateInfoByID(Info newInfo , String ID);
        void deleteInfoByID(String ID);
}