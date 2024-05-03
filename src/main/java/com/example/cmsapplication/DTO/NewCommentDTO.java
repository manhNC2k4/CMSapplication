package com.example.cmsapplication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCommentDTO {
    @JsonProperty
    private String content;
    @JsonProperty
    private Long user_id;
    @JsonProperty
    private Long post_id;

    public NewCommentDTO(String content, Long user_id, Long post_id) {
        this.content = content;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long used_id) {
        this.user_id = used_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }
}
