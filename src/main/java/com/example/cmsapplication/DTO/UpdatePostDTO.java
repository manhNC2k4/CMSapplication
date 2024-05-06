package com.example.cmsapplication.DTO;

import com.example.cmsapplication.model.Post;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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

}
