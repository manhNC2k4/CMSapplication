package com.example.cmsapplication.controller;

import com.example.cmsapplication.DTO.*;
import com.example.cmsapplication.model.Comment;
import com.example.cmsapplication.model.Like;
import com.example.cmsapplication.model.Post;
import com.example.cmsapplication.model.User;
import com.example.cmsapplication.service.CommentService;
import com.example.cmsapplication.service.LikeService;
import com.example.cmsapplication.service.PostService;
import com.example.cmsapplication.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final LikeService likeService;
    private final CommentService commentService;
    private final PostService postService;

    public UserController(UserService userService, LikeService likeService, CommentService commentService, PostService postService) {
        this.userService = userService;
        this.likeService = likeService;
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/comment/create")
    public ResponseEntity<Object> createComment(@RequestBody NewCommentDTO commentDTO) {
        try {
            User user = userService.getUserById(commentDTO.getUser_id());
            Post post = postService.getPostById(commentDTO.getPost_id());
            Comment comment = new Comment(user, post, commentDTO.getContent());
            commentService.createComment(comment);
            return new ResponseEntity<>("Created comment successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post/create")
    public ResponseEntity<Object> createPost(@RequestBody NewPostDTO postDTO) {
        try {
            User user = userService.getUserById(postDTO.getUser_id());
            Post post = new Post(user, postDTO.getTitle(), postDTO.getContent(), postDTO.getStatus());
            postService.createPost(post);
            return new ResponseEntity<>("Created post successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/like/list/{userId}")
    public ResponseEntity<List<PostResponse>> listLike(@PathVariable Long userId) {
        List<Post> listPost = likeService.findListLike(userId);
        if(listPost == null) {
            return ResponseEntity.notFound().build();
        }
        List<PostResponse> responseList = new ArrayList<>();
        for (Post post : listPost) {
            responseList.add(postService.convertToDTO(post));
        }
        return ResponseEntity.ok(responseList);
    }



    @PostMapping("/like/create")
    public ResponseEntity<Object> createLike(@RequestBody NewLikeDTO likeDTO) {
        try{
            User user = userService.getUserById(likeDTO.getUser_id());
            Post post = postService.getPostById(likeDTO.getPost_id());
            Like createdLike = new Like(post,user);
            likeService.createLike(createdLike);
            return new ResponseEntity<>("Created like successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/like/find/{userId}/{postId}")
//    public ResponseEntity<Like> findLike(@PathVariable Long userId, @PathVariable Long postId) {
//        Optional<Like> like = likeService.getLike(userId,postId);
//        if (like.isPresent()) {
//            return ResponseEntity.ok(like.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable long id) {
        try {
            commentService.deleteById(id);
            return new ResponseEntity<>("Deleted comment successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable long id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        } else {
            postService.deletePost(id);
            return ResponseEntity.noContent().build();
        }
    }
    @PutMapping("/post/update")
    public ResponseEntity<Object> updatePost(@RequestBody UpdatePostDTO postDTO) {
        try {
            Post existingPost = postService.getPostById(postDTO.getId());
            if (existingPost == null) {
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }
            existingPost.setTitle(postDTO.getTitle());
            existingPost.setContent(postDTO.getContent());
            existingPost.setStatus(postDTO.getStatus());
            postService.updatePost(existingPost);
            return new ResponseEntity<>("Updated post successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/like/delete/{id}")
    public ResponseEntity<Like> deleteLike(@PathVariable Long id) {
        try {
            likeService.deleteLike(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }

}
