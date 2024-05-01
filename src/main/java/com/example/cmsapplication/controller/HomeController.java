package com.example.cmsapplication.controller;

import com.example.cmsapplication.model.Comment;
import com.example.cmsapplication.model.Post;
import com.example.cmsapplication.service.CommentService;
import com.example.cmsapplication.service.LikeService;
import com.example.cmsapplication.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final CommentService commentService;
    private final PostService postService;
    private final LikeService likeService;

    public HomeController(CommentService commentService, PostService postService, LikeService likeService) {
        this.commentService = commentService;
        this.postService = postService;
        this.likeService = likeService;
    }

    @GetMapping("/list-posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPost();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(posts);
        }
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(post);
        }
    }
    @GetMapping("/post/title/{title}")
    public ResponseEntity<Post> getPostByTitle(@PathVariable String title) {
        Post post = postService.getPostByTitle(title);
        if (post == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(post);
        }
    }

    @GetMapping("/post/like/{id}")
    public ResponseEntity<Integer> countLike(@PathVariable Long id) {
        int likeCount = likeService.countLike(id);
        if (likeCount >= 0) {
            return ResponseEntity.ok(likeCount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/post/comment/{id}")
    public ResponseEntity<List<Comment>> listComment(@PathVariable Long id) {
        List<Comment> lists = commentService.getCommentsByPostId(id);
        if (lists.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(lists);
        }
     }
}
