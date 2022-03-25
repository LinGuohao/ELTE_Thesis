package com.guohaohome.moviedb.sqlEntity;

public class Line {
    private String id;
    private String sentence;
    private String author;
    private String line_id;


    public Line(String id, String sentence, String author, String line_id) {
        this.id = id;
        this.sentence = sentence;
        this.author = author;
        this.line_id = line_id;
    }

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
