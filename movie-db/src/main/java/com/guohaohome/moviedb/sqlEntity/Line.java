package com.guohaohome.moviedb.sqlEntity;

public class Line {
    private String id;
    private String sentence;
    private String author;

    public Line(String id, String sentence, String author) {
        this.id = id;
        this.sentence = sentence;
        this.author = author;
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
