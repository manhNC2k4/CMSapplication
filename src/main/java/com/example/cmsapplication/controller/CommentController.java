package com.example.cmsapplication.controller;

import com.example.cmsapplication.model.Comment;
import com.example.cmsapplication.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable long id) {
        Comment comment = commentService.getCommentById(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        } else {
            commentService.deleteById(id);
            return ResponseEntity.ok(comment);
        }
    }
}
