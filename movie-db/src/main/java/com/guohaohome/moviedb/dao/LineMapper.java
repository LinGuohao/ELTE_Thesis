package com.guohaohome.moviedb.dao;

import com.guohaohome.moviedb.sqlEntity.Line;

import java.util.List;

public interface LineMapper {
    List<Line> getLines(String ID);
    void insertLine(Line line);
    void deleteLine(String line_id);
}
