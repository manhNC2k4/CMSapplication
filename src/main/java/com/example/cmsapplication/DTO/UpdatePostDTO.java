package com.example.cmsapplication.DTO;

import com.example.cmsapplication.model.Post;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePostDTO {
    @JsonProperty
    private String title;
    @JsonProperty
    private String content;
    @JsonProperty
    private Post.PostStatus status;
    @JsonProperty
    private Long user_id;
    @JsonProperty
    private Long id;

    public UpdatePostDTO(String title, String content, Post.PostStatus status, Long user_id, Long id) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.user_id = user_id;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post.PostStatus getStatus() {
        return status;
    }

    public void setStatus(Post.PostStatus status) {
        this.status = status;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
