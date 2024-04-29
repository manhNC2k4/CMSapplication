package com.example.cmsapplication.service;

import com.example.cmsapplication.model.Post;
import com.example.cmsapplication.repository.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepo postRepo;
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }
    public Post createPost(Post post) {
        return postRepo.save(post);
    }
    public List<Post> getAllPost() {
        return postRepo.findAll();
    }
    public Post getPostById(Long id) {
        return postRepo.findById(id).get();
    }
    public Post getPostByTitle(String title){
        return postRepo.findByTitle(title).get();
    }
    public Post updatePost(Post post) {
        return postRepo.save(post);
    }
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }
}
