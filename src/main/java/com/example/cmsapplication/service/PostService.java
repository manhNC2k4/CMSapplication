package com.example.cmsapplication.service;

import com.example.cmsapplication.DTO.CommentResponse;
import com.example.cmsapplication.DTO.PostResponse;
import com.example.cmsapplication.model.Post;
import com.example.cmsapplication.repository.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final LikeService likeService;
    private final CommentService commentService;
    private PostRepo postRepo;
    public PostService(PostRepo postRepo, LikeService likeService, CommentService commentService) {
        this.postRepo = postRepo;
        this.likeService = likeService;
        this.commentService = commentService;
    }
    public Post createPost(Post post) {
        return postRepo.save(post);
    }
    public List<Post> getAllPost() {
        return postRepo.findAll();
    }
    public List<PostResponse> getPostByStatus(Post.PostStatus status) {
        List<Post> lists = postRepo.findAllByStatus(status);
        return lists.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Post getPostById(Long id) {
        return postRepo.findById(id).get();
    }
    public PostResponse getPostByTitle(String title){
        return convertToDTO(postRepo.findByTitle(title).get());
    }
    public Post updatePost(Post post) {
        return postRepo.save(post);
    }
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }


    public PostResponse convertToDTO(Post post) {
        int like = likeService.countLike(post.getId());
        List<CommentResponse> comments = commentService.getCommentsByPostId(post.getId());
        PostResponse dto =  new PostResponse(post.getId(), post.getTitle(), post.getContent(),
                post.getStatus(),post.getUser().getUsername(), like, comments, post.getCreatedAt(), post.getUpdatedAt());
        return dto;
    }

}
