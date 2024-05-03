package com.example.cmsapplication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewLikeDTO {
    @JsonProperty
    private Long user_id;
    @JsonProperty
    private Long post_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public NewLikeDTO(Long user_id, Long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }
}
