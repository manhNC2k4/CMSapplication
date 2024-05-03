package com.example.cmsapplication.service;

import com.example.cmsapplication.DTO.CommentResponse;
import com.example.cmsapplication.model.Comment;
import com.example.cmsapplication.repository.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CommentResponse> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);
        return comments.stream().map(this::convertCommentToDTO).collect(Collectors.toList());
    }

    private CommentResponse convertCommentToDTO(Comment comment) {
        CommentResponse dto = new CommentResponse(comment.getContent(), comment.getUser().getUsername());
        return dto;
    }
}
