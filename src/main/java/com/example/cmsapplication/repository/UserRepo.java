package com.example.cmsapplication.repository;

import com.example.cmsapplication.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);
    List<User> findAll(Sort sort);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Override
    void deleteById(Long aLong);


}
