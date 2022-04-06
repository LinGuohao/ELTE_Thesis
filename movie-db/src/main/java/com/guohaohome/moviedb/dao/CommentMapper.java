package com.guohaohome.moviedb.dao;

import com.guohaohome.moviedb.sqlEntity.Comment;

import java.util.List;

public interface CommentMapper {
    List<Comment> getCommentByMovieID(String movieID);
    List<Comment> getCommentByUserName(String username);
    void addComment(Comment comment);
    void deleteComment(String commentID);
    Comment getCommentByCommentID(String CommentID);
}
