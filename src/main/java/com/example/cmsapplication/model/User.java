package com.example.cmsapplication.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private boolean isAdmin  = false;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = false, nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    public User() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}
