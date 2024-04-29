package com.example.cmsapplication.controller;

import com.example.cmsapplication.model.Comment;
import com.example.cmsapplication.model.Like;
import com.example.cmsapplication.model.Post;
import com.example.cmsapplication.service.CommentService;
import com.example.cmsapplication.service.LikeService;
import com.example.cmsapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final LikeService likeService;
    private final CommentService commentService;

    public UserController(UserService userService, LikeService likeService, CommentService commentService) {
        this.userService = userService;
        this.likeService = likeService;
        this.commentService = commentService;
    }

    @GetMapping("/like/list/{userId}")
    public ResponseEntity<List<Post>> listLike(@PathVariable Long userId) {
        List<Post> listPost = likeService.findListLike(userId);
        if (listPost != null) {
            return ResponseEntity.ok(listPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        if (createdComment == null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(createdComment);
        }
    }

    @PostMapping("/like/create")
    public ResponseEntity<Like> createLike(@RequestBody Like like) {
        Like createdLike = likeService.createLike(like);
        if (createdLike != null) {
            return ResponseEntity.ok(createdLike);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/like/find/{userId}/{postId}")
    public ResponseEntity<Like> findLike(@PathVariable Long userId, @PathVariable Long postId) {
        Optional<Like> like = likeService.getLike(userId,postId);
        if (like.isPresent()) {
            return ResponseEntity.ok(like.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/like/delete/{id}")
    public ResponseEntity<Like> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return ResponseEntity.ok().build();
    }

}
