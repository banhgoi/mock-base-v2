package com.example.mockbasev2.service.impl;

import com.example.mockbasev2.common.constant.HttpStatusConstant;
import com.example.mockbasev2.dto.PostDTO;
import com.example.mockbasev2.dto.response.PostResponse;
import com.example.mockbasev2.entity.Post;
import com.example.mockbasev2.exception.CustomAPIException;
import com.example.mockbasev2.repository.PostRepository;
import com.example.mockbasev2.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDto) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    private PostDTO mapToDTO(Post post){
        PostDTO postDTO = mapper.map(post, PostDTO.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDTO;
    }
    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomAPIException(HttpStatusConstant.NULL_POINTER_OR_BAD_REQUEST_CODE, HttpStatusConstant.POST_NOT_FOUND_MESSAGE));
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setDescription(post.getDescription());
        return postDTO;
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, long id) {
        return null;
    }

    @Override
    public void deletePostById(long id) {

    }

    @Override
    public List<PostDTO> getPostsByCategory(Long categoryId) {
        return null;
    }
}
