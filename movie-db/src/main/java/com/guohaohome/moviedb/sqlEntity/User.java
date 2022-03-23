package com.guohaohome.moviedb.sqlEntity;

public class User {
    private String userName;
    private String password;
    private String fullName;
    private String roles;

    public User(String userName, String password, String fullName, String roles) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
