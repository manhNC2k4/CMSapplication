package com.example.cmsapplication.controller;

import com.example.cmsapplication.model.Post;
import com.example.cmsapplication.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPost();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(posts);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(post);
        }
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<Post> getPostByTitle(@PathVariable String title) {
        Post post = postService.getPostByTitle(title);
        if (post == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(post);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        if (createdPost == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(createdPost);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        Post updatedPost = postService.updatePost(post);
        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedPost);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable long id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        } else {
            postService.deletePost(id);
            return ResponseEntity.ok(post);
        }
    }
}
