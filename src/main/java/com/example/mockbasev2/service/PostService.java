package com.example.mockbasev2.service;

import com.example.mockbasev2.dto.PostDTO;
import com.example.mockbasev2.dto.response.PostResponse;
import com.example.mockbasev2.entity.Post;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDto);

    List<Post> getAllPosts();

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDto, long id);

    void deletePostById(long id);

    List<PostDTO> getPostsByCategory(Long categoryId);
}