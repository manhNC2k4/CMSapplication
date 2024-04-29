package com.example.cmsapplication.service;

import com.example.cmsapplication.model.Like;
import com.example.cmsapplication.model.Post;
import com.example.cmsapplication.repository.LikeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepo likeRepo;
    public LikeService(LikeRepo likeRepo) {
        this.likeRepo = likeRepo;
    }
    public int countLike(Long postId){
        return likeRepo.countLike(postId);
    }

    public List<Post> findListLike(Long userId){
        return likeRepo.findListLike(userId);
    }

    public Optional<Like> getLike(Long userId, Long postId){
        return likeRepo.findByUserAndPost(userId,postId);
    }

    public Like createLike(Like like){
        return likeRepo.save(like);
    }

    public void deleteLike(Long likeId){
        likeRepo.deleteById(likeId);
    }
}
