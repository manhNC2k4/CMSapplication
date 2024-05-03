package com.example.cmsapplication.DTO;

public class CommentResponse {
    private String content;
    private String username;
    private Long id;

    public CommentResponse(String content, String username, Long id) {
        this.content = content;
        this.username = username;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
