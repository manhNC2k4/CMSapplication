package com.example.cmsapplication.repository;

import com.example.cmsapplication.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    @Override
    List<Post> findAll(Sort sort);

    List<Post> findAllByStatus(Post.PostStatus status);

    @Override
    Optional<Post> findById(Long aLong);
    Optional<Post> findByTitle(String title);

    @Override
    void deleteById(Long aLong);


}
