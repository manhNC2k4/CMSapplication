package com.example.cmsapplication.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('DRAFT', 'PUBLISHED')")
    private PostStatus status = PostStatus.PUBLISHED;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public Post(User user, String title, String content, PostStatus status) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public Post() {

    }


    // Enum for post status
    public enum PostStatus {
        DRAFT,
        PUBLISHED
    }
}
