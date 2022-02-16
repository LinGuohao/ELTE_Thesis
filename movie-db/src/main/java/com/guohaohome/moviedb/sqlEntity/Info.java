package com.guohaohome.moviedb.sqlEntity;

public class Info {
    private String id;
    private String name;
    private double IMDb;
    private int Tomatoes;

    public Info(String id, String name, double IMDb, int tomatoes) {
        this.id = id;
        this.name = name;
        this.IMDb = IMDb;
        Tomatoes = tomatoes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
