package com.example.cmsapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
    private String content;
    private String username;
    private Long id;

}
