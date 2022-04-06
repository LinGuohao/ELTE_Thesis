package com.guohaohome.moviedb.sqlEntity;

public class Comment {
    private String commentID;
    private String username;
    private String movieID;
    private String content;

    public Comment(String commentID, String username, String movieID, String content) {
        this.commentID = commentID;
        this.username = username;
        this.movieID = movieID;
        this.content = content;
    }


    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
