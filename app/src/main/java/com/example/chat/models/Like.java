package com.example.chat.models;

public class Like {
    private String id;
    private String idPost;
    private String idUser;
    private Long timestamp;

    public Like() {

    }

    public Like(String id, String idPost, String idUser, Long timestamp) {
        this.id = id;
        this.idPost = idPost;
        this.idUser = idUser;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
