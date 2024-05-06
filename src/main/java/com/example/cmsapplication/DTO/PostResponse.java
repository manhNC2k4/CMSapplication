package com.example.cmsapplication.DTO;

import com.example.cmsapplication.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Post.PostStatus status;
    private String author;
    private int likes;
    private List<CommentResponse> comments;
    private Date createAt;
    private Date updateAt;

}
