package com.example.cmsapplication.repository;

import com.example.cmsapplication.model.Like;
import com.example.cmsapplication.model.Post;
import com.example.cmsapplication.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long> {
    @Override
    Optional<Like> findById(Long aLong);

    @Override
    <S extends Like> Optional<S> findOne(Example<S> example);

    @Query("select count(a) from Like a where a.post.id = :postId")
    int countLike(@Param("postId") Long postId);

    @Query("select a.post from Like a where a.user.id = :userId")
    List<Post> findListLike(@Param("userId") Long userId);

    @Query("select a from Like a where a.post.id = :postId and a.user.id = :userId")
    Optional<Like> findByUserAndPost(@Param("userId") Long userId,@Param("postId") Long postId);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Like> S save(S entity);
}
