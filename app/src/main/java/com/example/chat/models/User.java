package com.example.chat.models;

public class User {
    private String id;
    private String email;
    private String username;
    private String github;
    private String imageProfile;
    private String imageCover;
    private long timestamp;
    private boolean online;
    private long lastConnection;

    public User() {

    }

    public User(String id, String email, String username, String github, String imageProfile, String imageCover, long timestamp, boolean online, long lastConnection) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.github = github;
        this.imageProfile = imageProfile;
        this.imageCover = imageCover;
        this.timestamp = timestamp;
        this.online = online;
        this.lastConnection = lastConnection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(long lastConnection) {
        this.lastConnection = lastConnection;
    }
}
