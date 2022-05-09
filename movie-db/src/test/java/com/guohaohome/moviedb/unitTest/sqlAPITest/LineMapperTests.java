package com.guohaohome.moviedb.unitTest.sqlAPITest;

import com.guohaohome.moviedb.dao.LineMapper;
import com.guohaohome.moviedb.sqlEntity.Line;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@FixMethodOrder(MethodSorters.JVM)
@DirtiesContext
public class LineMapperTests {
    @Autowired
    LineMapper lineMapper;

    @Test
    @DirtiesContext
    public void testGetLines() {
        List<Line> lines = lineMapper.getLines("testMovieId");
        assertEquals(2, lines.size());
        assertEquals("testSentence", lines.get(0).getSentence());
        assertEquals("testAuthor", lines.get(0).getAuthor());
        assertEquals("testLine_id", lines.get(0).getLine_id());
        assertEquals("testSentence2", lines.get(1).getSentence());
        assertEquals("testAuthor2", lines.get(1).getAuthor());
        assertEquals("testLine_id2", lines.get(1).getLine_id());
    }

    @Test
    @DirtiesContext
    public void testInsertLineAndDeleteLine() {
        Line line = new Line("testInsertLine", "testInsertLineSentence", "testInsertLineAuthor"
                , "testInsertLineLine_id");
        lineMapper.insertLine(line);
        List<Line> lines = lineMapper.getLines("testInsertLine");
        assertEquals(1, lines.size());
        assertEquals("testInsertLineSentence", lines.get(0).getSentence());
        assertEquals("testInsertLineAuthor", lines.get(0).getAuthor());
        assertEquals("testInsertLineLine_id", lines.get(0).getLine_id());
        lineMapper.deleteLine("testInsertLineLine_id");
        lines = lineMapper.getLines("testInsertLine");
        assertEquals(0, lines.size());
    }
}
