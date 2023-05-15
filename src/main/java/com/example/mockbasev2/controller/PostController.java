package com.example.mockbasev2.controller;

import com.example.mockbasev2.dto.response.GeneralResponse;
import com.example.mockbasev2.dto.response.PostResponse;
import com.example.mockbasev2.entity.Post;
import com.example.mockbasev2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public GeneralResponse<?> get() {
        return GeneralResponse.ok(postService.getAllPosts());
    }
    @GetMapping("/{id}")
    public GeneralResponse<?> getPost(@PathVariable long id) {
        return GeneralResponse.ok(postService.getPostById(id));
    }
}
