package com.example.cmsapplication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewCommentDTO {
    @JsonProperty
    private String content;
    @JsonProperty
    private Long user_id;
    @JsonProperty
    private Long post_id;


}
