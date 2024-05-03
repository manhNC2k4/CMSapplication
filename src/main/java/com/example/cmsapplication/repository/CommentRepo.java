package com.example.cmsapplication.repository;

import com.example.cmsapplication.model.Comment;
import com.example.cmsapplication.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Override
    Optional<Comment> findById(Long aLong);

    @Override
    List<Comment> findAll(Sort sort);

    @Override
    void deleteById(Long aLong);

    @Query("select c from Comment c where c.post.id = :postId")
    List<Comment> findByPostId(@Param("postId") Long postId);

}
