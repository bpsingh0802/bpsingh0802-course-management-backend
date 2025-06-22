package com.example.finaltest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.finaltest.PostRepository;
import com.example.finaltest.model.Post;

import java.util.List;

@RestController
@RequestMapping("/api") // All endpoints start with /api
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostRepository repo;

    // Health check
    @GetMapping("/ping")
    public String ping() {
        return "API is working 🚀";
    }

    // GET all job posts
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = repo.findAll();
        System.out.println("Fetching all posts: " + posts); // 🔍 Debug print
        return ResponseEntity.ok(posts);
    }

    // POST a new job post
    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post savedPost = repo.save(post);
        System.out.println("Saved new post: " + savedPost); // 🔍 Debug print
        return ResponseEntity.ok(savedPost);
    }
}
