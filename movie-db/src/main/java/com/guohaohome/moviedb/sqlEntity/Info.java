package com.guohaohome.moviedb.sqlEntity;

public class Info {
    private String id;
    private String photo;
    private String review;
    private String OST;
    private String clips;
    private String Details;
    private double IMDb;
    private int Tomatoes;

    public Info(String id, String photo, String review, String OST, String clips,
                String details, double IMDb, int tomatoes) {
        this.id = id;
        this.photo = photo;
        this.review = review;
        this.OST = OST;
        this.clips = clips;
        Details = details;
        this.IMDb = IMDb;
        Tomatoes = tomatoes;
    }

    public String getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getOST() {
        return OST;
    }

    public void setOST(String OST) {
        this.OST = OST;
    }

    public String getClips() {
        return clips;
    }

    public void setClips(String clips) {
        this.clips = clips;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public double getIMDb() {
        return IMDb;
    }

    public void setIMDb(double IMDb) {
        this.IMDb = IMDb;
    }

    public int getTomatoes() {
        return Tomatoes;
    }

    public void setTomatoes(int tomatoes) {
        Tomatoes = tomatoes;
    }
}
