package com.example.cmsapplication.service;

import com.example.cmsapplication.model.Comment;
import com.example.cmsapplication.repository.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentRepo commentRepo;
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }
    public Comment createComment(Comment comment) {
        return commentRepo.save(comment);
    }
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }
    public Comment getCommentById(Long id) {
       return commentRepo.findById(id).get();
    }
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepo.findByPostId(postId);
    }
}
