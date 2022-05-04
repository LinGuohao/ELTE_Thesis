package com.guohaohome.moviedb.unitTest.sqlApiTest;


import com.guohaohome.moviedb.dao.CommentMapper;
import com.guohaohome.moviedb.sqlEntity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com/guohaohome/moviedb/dao")
@DirtiesContext
public class CommentMapperTester {
    @Autowired
    CommentMapper commentMapper;

    @Test
    public void testGetCommentByMovieID() {
        List<Comment> commentList = commentMapper.getCommentByMovieID("12345");
        assertEquals(2, commentList.size());
        assertEquals("testCommentID", commentList.get(0).getCommentID());
        assertEquals("testUserName", commentList.get(0).getUsername());
        assertEquals("testContent", commentList.get(0).getContent());
        assertEquals("testCommentID2", commentList.get(1).getCommentID());
        assertEquals("testUserName2", commentList.get(1).getUsername());
        assertEquals("testContent2", commentList.get(1).getContent());
    }

    @Test
    public void testGetCommentByUserName() {
        List<Comment> commentList = commentMapper.getCommentByUserName("testUserName2");
        assertEquals(2, commentList.size());
        assertEquals("testCommentID2", commentList.get(0).getCommentID());
        assertEquals("12345", commentList.get(0).getMovieID());
        assertEquals("testContent2", commentList.get(0).getContent());
        assertEquals("testCommentID3", commentList.get(1).getCommentID());
        assertEquals("123456", commentList.get(1).getMovieID());
        assertEquals("testContent3", commentList.get(1).getContent());

    }

    @Test
    public void testGetCommentByCommentID() {
        Comment comment = commentMapper.getCommentByCommentID("testCommentID");
        assertEquals("testUserName", comment.getUsername());
        assertEquals("12345", comment.getMovieID());
        assertEquals("testContent", comment.getContent());
    }

    @Test
    public void testAddCommentAndDeleteComment() {
        Comment comment = new Comment("testAddCommentAndDeleteCommentCommentID"
                , "testAddCommentAndDeleteCommentUsername", "testAddCommentAndDeleteCommentMovieID"
                , "testAddCommentAndDeleteCommentContent");
        commentMapper.addComment(comment);
        comment = commentMapper.getCommentByCommentID("testAddCommentAndDeleteCommentCommentID");
        assertEquals("testAddCommentAndDeleteCommentUsername", comment.getUsername());
        assertEquals("testAddCommentAndDeleteCommentMovieID", comment.getMovieID());
        assertEquals("testAddCommentAndDeleteCommentContent", comment.getContent());
        commentMapper.deleteComment("testAddCommentAndDeleteCommentCommentID");
        assertNull(commentMapper.getCommentByCommentID("testAddCommentAndDeleteCommentCommentID"));
    }

    @Test
    @DirtiesContext
    public void testDeleteCommentByMovieId() {
        Comment comment = new Comment("testDeleteCommentByMovieIdCommentID", "testDeleteCommentByMovieIdUsername"
                , "testDeleteCommentByMovieIdMovieID", "testDeleteCommentByContent");
        commentMapper.addComment(comment);
        comment = new Comment("testDeleteCommentByMovieIdCommentID2", "testDeleteCommentByMovieIdUsername2"
                , "testDeleteCommentByMovieIdMovieID", "testDeleteCommentByContent2");
        commentMapper.addComment(comment);
        List<Comment> comments = commentMapper.getCommentByMovieID("testDeleteCommentByMovieIdMovieID");
        assertEquals(2, comments.size());
        commentMapper.deleteCommentByMovieId("testDeleteCommentByMovieIdCommentID");
        assertEquals(0, commentMapper.getCommentByMovieID("testDeleteCommentByMovieIdCommentID").size());
    }
}
