package com.example.cmsapplication.DTO;

import com.example.cmsapplication.model.Post;

import java.util.Date;
import java.util.List;

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

    public PostResponse(Long id, String title, String content, Post.PostStatus status, String author, int likes, List<CommentResponse> comments, Date createAt, Date updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.author = author;
        this.likes = likes;
        this.comments = comments;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
